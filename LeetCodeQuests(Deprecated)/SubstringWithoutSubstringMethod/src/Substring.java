import java.util.*;


public class Substring {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String S = in.next();
        int start = in.nextInt();
        int end = in.nextInt();
        in.close();

        // System.out.println(S.substring(start, end)); // we can do this directly, but without using Substring, we can do the below
        String new_s = "";
        for(int i=start; i<end; i++){
            new_s += S.charAt(i);
        }
        System.out.println(new_s);
    }  
}