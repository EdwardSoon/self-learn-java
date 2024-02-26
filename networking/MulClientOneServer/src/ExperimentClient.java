/*
this is to test server can connect to how many clients concurrently
We found that:
* server (defaulted to be Single-threaded) can only connect to one client at a given time
* then start to process any request from the client
* once the request finished processing, then only it will disconnect the current client and connect to another one that is waiting on the line.
 */
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ExperimentClient {
    public static void main(String[] args){
        try {
            while (true){
                Socket c1 = new Socket("localhost", 4100);
                Socket c2 = new Socket("localhost", 4100);

                PrintWriter pw = new PrintWriter(c1.getOutputStream());
                PrintWriter pw2 = new PrintWriter(c2.getOutputStream());
                Scanner sc = new Scanner(System.in);
                String input = "";

                System.out.print("c1 to server: ");
                input = sc.nextLine();
                pw.println(input);
                pw.flush();
                System.out.print("c2 to server: ");
                input = sc.nextLine();
                pw2.println(input);
                pw2.flush();
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
