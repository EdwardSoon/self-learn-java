/*
this is to test server can connect to how many clients concurrently
We found that:
* server (defaulted to be Single-threaded) can only connect to one client at a given time
* then start to process any request from the client
* once the request finished processing, then only it will disconnect the current client and connect to another one that is waiting on the line.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ExperimentServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(4100);
            System.err.println("Waiting for connection..");
            while(true){
                Socket port4100 = ss.accept();
                System.err.println("Connected" + port4100);
                InputStreamReader isr = new InputStreamReader(port4100.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String fromClient = br.readLine();
                System.out.println(fromClient);
            }







        }catch (Exception e){
            System.out.println(e);
        }
    }
}