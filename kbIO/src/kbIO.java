//import java.io.FileOutputStream;
import java.io.*;

public class kbIO {
    public static void main(String[] args) throws IOException {  // entire Main method will throws the Exception if happens

//        FileInputStream fis = new FileInputStream("/Users/edward.soonrw/Desktop/Java/fileIO/src/testFIS.txt");
        // first way: read the file contents
//        int data = fis.read() ;               // read first byte
//        while (data != -1){
//            char dataC = (char) data;
//            System.out.print(dataC);
//            data = fis.read();                // read next byte of data
//        }
//        System.out.print(data);  // why with this, it only prints -1 instead of the whole file??

        // OR second way: read the file contents
//        int data;
//        while ((data = fis.read()) != -1){      // it reads next byte and check whether it is -1
//            System.out.print((char)data);
//        }
//
//        fis.close();  // close connection // not needed for this Java version

        // write contents to a file
//        FileOutputStream fos = new FileOutputStream("/Users/edward.soonrw/Desktop/Java/fileIO/src/testFOS.txt");
////        String d = "hi nihao";
////        int x = c;
////        for (int i = 0; i<d.length(); i++){
////            fos.write(d.toCharArray()[i]);
////        }
//        fos.write(72); // input ASCII values in decimal or character
//        fos.close();


        // to copy from one file to one file statically
//        FileInputStream fis = new FileInputStream("/Users/edward.soonrw/Desktop/Java/fileIO/src/testFIS.txt");
//        FileOutputStream output = new FileOutputStream("/Users/edward.soonrw/Desktop/Java/fileIO/src/copied.txt"); // output as "copied.txt"
//        int wordsByte;
//        while ((wordsByte = fis.read())!= -1){  // read one byte at a time
//            output.write(wordsByte);            // write one byte at a time
//        }
//
//        fis.close();
//        output.close();

        // to copy from one file to one file dynamically 1 (by taking input in console)
//        FileInputStream fis = new FileInputStream(args[0]);
//        FileOutputStream output = new FileOutputStream(args[1]);
//        int wordsByte;
//        while ((wordsByte = fis.read())!= -1){  // read one byte at a time
//            output.write(wordsByte);            // write one byte at a time
//        }
//
//        fis.close();
//        output.close();

        // to copy from one file to one file dynamically 2 (by using Scanner)
//        Scanner sc = new Scanner(System.in);        // System.in returns InputStream object which is the parameter for Scanner constructor
//        System.out.print("Enter source file: ");
//        String source = sc.nextLine();
//        System.out.print("Enter dest file: ");
//        String dest = sc.nextLine();
//
//        FileInputStream fis = new FileInputStream(source);
//        FileOutputStream fos = new FileOutputStream(dest);
//        int wordsByte;
//        while ((wordsByte = fis.read()) != -1){
//            fos.write(wordsByte);       // copy from input read
//        }

        // to read input from keyboard -- way 1: character stream -- works for ASCII
//        InputStreamReader kb = new InputStreamReader(System.in);        // characterStream
//        int y ;
//        while ((y = kb.read())!= '\n'){         // this will convert '\n' into unicode which is an integer
//            System.out.print((char)y);
//        }

        // to read input from keyboard -- way 2: scanner of the token
//        Scanner kb2 = new Scanner(System.in);
//        System.out.println(kb2.nextLine());

        // to read input from keyboard -- way 3 bytes -- byte stream -- works for ASCII
        DataInputStream kb3 = new DataInputStream(System.in);
        int byteData;
//        while ((byteData = kb3.read())!= '\n'){
//            System.out.print((char)byteData);
//        }

        FileOutputStream fos = new FileOutputStream("/Users/edward.soonrw/Desktop/Java/kbIO/src/kb10Input.txt");
        while((byteData = kb3.read()) != -1 && byteData != '\n'){
            System.out.print((char)byteData);
            fos.write(byteData);
        }

    }
}