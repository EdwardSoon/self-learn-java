import java.util.concurrent.ExecutionException;

public class test {
    public static void main(String[] args) {
        try{
            double parseNum1 = Double.parseDouble(args[0]);
            double parseNum2 = Double.parseDouble(args[1]);
            String operation = args[2];
        }
        catch (Exception e){
            System.err.println(e);
        }

    }
}
