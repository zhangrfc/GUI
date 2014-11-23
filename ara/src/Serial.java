import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Vector;


/**
 * @author Dancing with Ara EECS481 - Group 4 Ruifu Zhang, Raj Vir, Oliver Tang, Batu Inal
 */
//dev/tty.*

public class Serial implements SerialPortEventListener {
  
    public static class Global {
        
         //   public int[] data = new int[100];
            public static int data = -99;
            
        }
  

    SerialPort serialPort = null;

    private static final String PORT_NAMES[] = { 
        "/dev/tty.usbmodem", // Mac OS X
        "/dev/tty.usbserial-A8008Ifx",
        "/dev/tty.usbserial-A8008HtU",
//        "/dev/usbdev", // Linux
//        "/dev/tty", // Linux
//        "/dev/serial", // Linux
        "COM3", // Windows
    };
    
    private String appName;
    private BufferedReader input;
    private OutputStream output;
    
    private static final int TIME_OUT = 1000; // Port open timeout
    private static final int DATA_RATE = 9600; // Arduino serial port

    public boolean initialize() {
        try {
            CommPortIdentifier portId = null;
            Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

            // Enumerate system ports and try connecting to Arduino over each
            //
            System.out.println( "Trying:");
            while (portId == null && portEnum.hasMoreElements()) {
                // Iterate through your host computer's serial port IDs
                //
                CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
                System.out.println( "   port" + currPortId.getName() );
                for (String portName : PORT_NAMES) {
                    if ( currPortId.getName().equals(portName) 
                      || currPortId.getName().startsWith(portName)) {

                        // Try to connect to the Arduino on this port
                        //
                        // Open serial port
                        serialPort = (SerialPort)currPortId.open(appName, TIME_OUT);
                        portId = currPortId;
                        System.out.println( "Connected on port" + currPortId.getName() );
                        break;
                    }
                }
            }
        
            if (portId == null || serialPort == null) {
                System.out.println("Oops... Could not connect to Arduino");
                return false;
            }
        
            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                            SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE);

            // add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);

            // Give the Arduino some time
            try { Thread.sleep(2000); } catch (InterruptedException ie) {}
            
            return true;
        }
        catch ( Exception e ) { 
            e.printStackTrace();
        }
        return false;
    }
    
    private void sendData(String data) {
        try {
            System.out.println("Sending data: '" + data +"'");
            
            // open the streams and send the "y" character
            output = serialPort.getOutputStream();
            output.write( data.getBytes() );
        } 
        catch (Exception e) {
            System.err.println(e.toString());
            System.exit(0);
        }
    }

    //
    // This should be called when you stop using the port
    //
    public synchronized void close() {
        if ( serialPort != null ) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    //
    // Handle serial port event
    //
    public synchronized void serialEvent(SerialPortEvent oEvent) {
        //System.out.println("Event received: " + oEvent.toString());
      
      //int i = 0;
        try {
            switch (oEvent.getEventType() ) {
                case SerialPortEvent.DATA_AVAILABLE: 
                    if ( input == null ) {
                        input = new BufferedReader(
                            new InputStreamReader(
                                    serialPort.getInputStream()));
                    }
                    String inputLine = input.readLine();
                    System.out.println(inputLine);
                   
                    Global.data = Integer.parseInt(inputLine);
                   // Globals.data[i & 100] = Integer.parseInt(inputLine);
                   // ++i;
                    break;

                default:
                    break;
            }
        } 
        catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public Serial() {
        appName = getClass().getName();
    }
    
    
    public static void main(String[] args) throws Exception {
        Serial test = new Serial();
       
        if ( test.initialize() ) {
          //WRITE THE MAIN HERE
            while(true){
              //no value received from sensors
     /*         if(Global.data == -99){
                System.out.println("No value received from sensors! Possibly no contact yet or Arduino connection fault!!");
                
              }
              //if nothing is currently being touched
              else if(Global.data == 0){
                System.out.println("Nothing is currently being touched!");
                
              }
              //if more than one button is currently being pressed
              else if(Global.data == -1){
                System.out.println("More than one button is currently being pressed!");
                
              }
              //correct input data is received, lets create the game
              else{
                int strength = (Global.data % 10) ;  // a value between 0 and 2, 0-> weak, 1-> medium, 2-> strong
                int analog_pin = (Global.data - strength) / 10; // a value between 0 and 7, defines which sensor is pressed
                
                
                
              }
              
              
              */
              
            }
            
        }

        // Wait 5 seconds then shutdown
        try { Thread.sleep(2000); } catch (InterruptedException ie) {}
    }
}