/*
Multithreading: Building a basic calculator server taking multiple client's request concurrently

1. create a socket client that sends the connection to the server
2. once connected, write data based on the keyboard input to the server
3. read the result that calculated by client server

 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 5100);
            System.out.println("Connected to server");
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            String num1 = args[0]; // no need to convert to double actually it is original string
            String num2 = args[1];
            String operation = args[2];
            String num3 = args[3];
            String num4 = args[4];
            String operation2 = args[5];
//            Scanner sc = new Scanner(System.in);
//            double num1 = sc.nextDouble();
//            double num2 = sc.nextDouble();
//            String operation = sc.next();
            out.writeUTF(num1); // when write to server also can be string, only when reach server side needs to change to double as it is going ot perform calculations
            out.writeUTF(num2);
            out.writeUTF(operation);  // write as string (because server is writing as string)
            out.writeUTF(num3);
            out.writeUTF(num4);
            out.writeUTF(operation2);
            out.flush();

            DataInputStream in = new DataInputStream(client.getInputStream());
            double result = in.readDouble();
            System.out.println("Result: " + result);
            double result2 = in.readDouble();
            System.out.println("Result2: " + result2);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
