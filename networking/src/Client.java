/* CLIENT CONNECTING TO TCP SERVER
Question:
Implement a TCP server and a TCP client. The client connects to the TCP server.
The client sends a number to the server.
The server replies the client whether the number is a prime or it's an invalid number.

Solution pseudocode:
1. create (client) socket object
2. create an outputStream to write the integer to the server
 2.1 test if it is successfully writing to server
 2.2 then start changing to input the data from keyboard
3. create an inputStream to receive the reply from the server.
 */


import java.net.Socket;
import java.io.*;
import java.util.Scanner;

public class Client {

    public static void main (String[] args){
        try {
            // create client socket to connect to server socket
            Socket client = new Socket("localhost", 4000); // or Socket client = new Socket("127.0.0.1", 5000);
            System.out.println("Client is connected");

            // create an output stream to write to server
            PrintWriter pw = new PrintWriter(client.getOutputStream()); // client outputStream to be used for instantiate printWriter stream
            BufferedWriter bw = new BufferedWriter(pw); // create a bufferedStream on top of the printWriter Stream

            Scanner sc = new Scanner(System.in); // create a scanner object to read the input from keyboard
            System.out.print("Enter an integer: ");
            int num = sc.nextInt();  // store the read data into num variable
            bw.write(num);
            bw.flush();  // must put flush() to flush the buffered data

            // create an inputStream to receive the response from server
            InputStreamReader isr = new InputStreamReader(client.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            System.out.print(br.readLine());

        }catch (Exception e){
            System.out.println(e);
        }

    }
}
