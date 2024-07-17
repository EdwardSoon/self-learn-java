import java.util.Scanner;

public class ConvertString {

    static int length = 1;

    public static int getlength(int x){
            while(x/10 != 0) {
                x = x/10;
                length++;       
            } 
            return length;
        }

    public static String convert(int x){
            char[] x_char = new char[getlength(x)];
            for(int i = x_char.length-1; i>=0; i--){
                x_char[i] = (char)(x % 10 + 48);//ASCII VALUE;
                System.out.println(x_char[i]); // it shows it is converting from the last digit
                x = x / 10;
            }
            return new String(x_char);
        }
    public static void main(String[] args) throws Exception {
        try {
        // input
        Scanner sc = new Scanner(System.in);
        int b = sc.nextInt();
        String s = convert(b);
        sc.close();
        System.out.println(s);

        if (b == Integer.parseInt(s)) {
            System.out.println("Good job");
           } else {
            System.out.println("Wrong answer.");
           }
          } catch (Exception e) {
           System.out.println("Unsuccessful Termination!!");
          }
         }
        





    
}
