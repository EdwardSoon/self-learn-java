import java.io.*;
import java.util.*;

public class Palindrome {
//A palindrome is a word, phrase, number, or other sequence of characters which reads the same backward or forward.

    public static void main(String[] args) {
        
        Scanner sc=new Scanner(System.in);
        String A=sc.next();
        /* Enter your code here. Print output to STDOUT. */
        String x = "";
        if (A.length() < 50){
            for(int i=A.length()-1; i>=0; i--){
                x += A.charAt(i);  
            }
            if (x.equals(A)){
                System.out.println("Yes");
            }
            else {
                System.out.println("No");
            }
        }
    }
}



