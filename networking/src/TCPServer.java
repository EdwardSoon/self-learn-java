/* USING TCP SERVER
Question:
Implement a TCP server and a TCP client. The client connects to the TCP server.
The client sends a number to the server.
The server replies the client whether the number is a prime or it's an invalid number.

Solution pseudocode:
1. create server socket object with try-catch block
2. create client socket object to accept the client tries to connect the socket object
3. create an inputStream to read the data sent from client
4. write a method for determine if a number is prime or if it is not
5. create an outputStream to write data to client
 */

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static String isPrime(int n){
        // base case
        if (n <= 1){
            return "Not a prime number";
        }
        else {
            for (int i = 2; i < n; i++){
                if (n % i == 0){    // once divisible then it is surely not a prime number
                    return "Not a prime number";
                }
            }
            return "It is a prime number";
        }
    }
    
    public static void main(String[] args) {
        try{
            // create server socket
            ServerSocket ss = new ServerSocket(4000);
            System.out.println("Waiting for the client");
            Socket communicationChannel = ss.accept(); // .accept() from the client socket and return the connected socket object for communication -- once accept, it is connected
            System.out.println("Connected");

            // create inputStream to read data from client to server
            InputStreamReader isr = new InputStreamReader(communicationChannel.getInputStream()); // create the inputStream with server inputStream
            BufferedReader br = new BufferedReader(isr); // create a bufferedStream on top of the inputStraem to make it read more efficiently line by line
            int data = br.read();
            System.out.print("The number from Client: " + data);

            // create outputStream to write the response back to client
            PrintWriter pw = new PrintWriter(communicationChannel.getOutputStream());  // create the outputStream with server outputStream
            BufferedWriter bw = new BufferedWriter(pw);  // create bufferStream on top of the writer

            bw.write(isPrime(data)); // write the returned String with taking the input from client
            bw.flush();  // flush the data to client socket


        }catch (Exception e){
            System.out.println(e);
        }


    }
}