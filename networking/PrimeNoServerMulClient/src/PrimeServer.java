import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
1. build a server
2. accept all incoming connection
3. once accept a connection, create a new thread and process the request
3. read data from the client
4. process the data with the isPrime function
5. send back the result of the isPrime function
 */
public class PrimeServer {

    // determine if a given number is prime number: a prime can only be divided by itself and 1
    public static boolean isPrime(int n){
        //base case
        if (n <= 1 ){
            return false;
        }
        for (int i = 2; i<n; i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(4100);
            System.out.println("Waiting for client");
            while (true){ // infinite loop
                Socket connection = server.accept();
                System.out.println("Connected to client");
                new Thread(() -> {
                    try {
                        DataInputStream dataIn = new DataInputStream(connection.getInputStream());
                        int data = dataIn.readInt(); // read as int as it is already converted to int in client side
                        System.out.println("Received data: " + data);

                        DataOutputStream dataOut = new DataOutputStream(connection.getOutputStream());
                        dataOut.writeBoolean(isPrime(data)); // write as boolean as respond
                        System.out.println("Responded!");

                        dataOut.flush();

                    }catch (Exception e){
                        System.err.println(e);
                    }
                }).start();
            }
        }catch (Exception e){
            System.err.println(e);
        }
    }
}