import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 4100);
            System.out.println("Connected to server");

            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            out.writeInt(Integer.parseInt(args[0])); // since we are receiving as int at server side, so write as int here so that it can receive properly
            System.out.println("Send to server: " + args[0]);
            out.flush();

            DataInputStream in = new DataInputStream(client.getInputStream());
            boolean result = in.readBoolean(); // read must also read as boolean, will not work to read as UTF (String)
            System.out.println("Is it prime number? " + result);

        }catch (Exception e){
            System.err.println(e);
        }


    }
}
