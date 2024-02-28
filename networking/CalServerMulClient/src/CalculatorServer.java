/*
Multithreading: Building a basic calculator server taking multiple client's request concurrently

1. create a server
2. accept the requests in infinite loop (keep on accepting any requests from client)
3. once connected, read data using read Stream
4. based on the input from client, calculate using operations
5. write back to client and show on client side
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5100);
            while(true){

                Socket connection = server.accept();  // accept any incoming requests
                System.out.println("Connected to port 5100");
                new Thread(() -> {
                    try{
                        DataInputStream in = new DataInputStream(connection.getInputStream());  // build an dataInputStream to read the primitive input from client
                        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                        double num1 = Double.parseDouble(in.readUTF());  // parse to double from string (since it is a string from args of client)
                        double num2 = Double.parseDouble(in.readUTF());
                        double result;
                        String operation = in.readUTF();
                        double num3 = Double.parseDouble(in.readUTF());
                        double num4 = Double.parseDouble(in.readUTF());
                        double result2;
                        String operation2 = in.readUTF();
                        switch (operation) {
                            case "+" -> out.writeDouble(result = num1 + num2);
                            case "-" -> out.writeDouble(result = num1 - num2);
                            case "t" -> out.writeDouble(result = num1 * num2);
                            case "d" -> out.writeDouble(result = num1 / num2);
                            default -> out.writeDouble(result = Integer.MAX_VALUE);  // error
                        }
                        switch (operation2) {
                            case "+" -> out.writeDouble(result2 = num3 + num4);
                            case "-" -> out.writeDouble(result2 = num3 - num4);
                            case "t" -> out.writeDouble(result2 = num3 * num4);
                            case "d" -> out.writeDouble(result2 = num3 / num4);
                            default -> out.writeDouble(result2 = Integer.MIN_VALUE);  // error
                        }
                        System.out.println(result + " next: " + result2); // add this to check the concurrency.
                        out.flush();

                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}