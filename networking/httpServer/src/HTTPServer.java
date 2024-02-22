/* IMPLEMENT HTTP SERVER WITH SOCKET API
https://medium.com/@GeoffreyDV/learn-more-about-http-by-coding-a-simple-web-server-in-java-edfadbc3f2c0
Question:
Use the socket API to implement a simple HTTP server, and you can see "hello world" in your browser when accessing the URL.

Solution Pseudocode:
1. create a serverSocket object to listen to incoming request from client (browser in this case)
   1.1 create a Socket object for accepting the client socket to establish connection socket
2. Reading the request of the client
3. Sending back a response to client
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
    public static void main(String[] args) {
        try{
            // create a serverSocket object to listen for incoming requests
            ServerSocket server = new ServerSocket(4000); // create a ServerSocket object that will listen to the specified port (4000 in this case)
            System.err.println("Waiting for the client...");
            Socket connection4000 = server.accept(); // when server accepts connection from client, it will return Socket object that represents the connection between server to the specific port (client)
            System.err.println("Client connected");

            // reading the request of the client
            BufferedReader in = new BufferedReader(new InputStreamReader(connection4000.getInputStream())); // create a reader stream to get request from client
            String headersLineFromClient;  // prepare to store the header request from the client

            while ((headersLineFromClient = in.readLine()) != null){ // start to read everything the client is sending over to the serverSocket line by line
                System.out.println(headersLineFromClient);
                if (headersLineFromClient.isEmpty()){ // when reached empty line (indicating the end of headers), the loop will end and proceed
                    break;
                }
            }

            // sending back a response from server to client to fully connect and display
            PrintWriter pw = new PrintWriter(connection4000.getOutputStream()); // create a writer stream to send the response back to server

            String contentToDisplay = "Hello World!";

            pw.println("HTTP/1.1 200 OK");  // part of the protocol: response to inform client that the request is received on server side and the connection can be established then only webpage will appear
//            pw.println("Content-length: " + response.length());  // optional
//            pw.println("Content-type: text/html; charset=utf-8"); // optional to set the format of the contents
//            pw.println("Date: 2024-02-22");
            pw.println();  // this skip line is a must (part of the protocol) to separate headers and the body in an HTTP Response, so that client knows what to show
            // contents that we want the client to display
            pw.println("<b>" + contentToDisplay + "</b>");  // <b> is to bold the words print out in client
            pw.flush(); // must flush the content and the response code
            pw.close();
            System.err.println("Connection is closed.");


        }catch (Exception e){
            System.out.println(e);
        }
    }
}