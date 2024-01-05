import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class CurrencyFormatter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double payment = sc.nextDouble();
        sc.close();

        String us = NumberFormat.getCurrencyInstance(Locale.US).format(payment);
        String china = NumberFormat.getCurrencyInstance(Locale.CHINA).format(payment);
        String india = NumberFormat.getCurrencyInstance(new Locale("en","IN")).format(payment); // create a new instance with Locale Class, because there is no India field/constant predefined in Locale Class 
        //breaking down the steps:
        NumberFormat japan_nf = NumberFormat.getCurrencyInstance(Locale.JAPAN); // NumberFormat is an abstract class, thus we create object using getCurrencyInstance Method. getCurrencyInstance takes one parameter, a constant/field from Locale Class, so that the currency is being set to the Constant.
        String japan =  japan_nf.format(payment);  // created japan_nf instance was in Japan currency when instantiated. With this object, we can use .format() method to change an double type amount that we want Japanese Yen.
 

        System.out.println("US: " + us);
        System.out.println("China: " + china);
        System.out.println("India: " + india);
        System.out.println("Japan: " + japan);

    }
}
