// https://www.geeksforgeeks.org/java-program-for-factorial-of-a-number/

public class Factorial {

    // n! = n * (n-1) * (n-2) ...
    // T(n) = O(N); S(n) = O(N) 
    public static int factorial(int n){
        if (n == 0){
            return 1;
        }
        else if (n < 0) {
            System.out.print("Negative number cannot do factorial, error:");
            return 999;
        }
        System.out.println("n: " + n);
        return n * factorial(n-1);          // cost = N (it is not O(N!))
    }
    
    // T(n) = O(N) ; S(n) = O(1)
    public static int factorial_Loop(int n){
        int result = 1; 
        if (n==0){
            return result;
        }
        else if (n<0){
            System.out.print("Negative number cannot do factorial, error:");
            return 999;            
        }
        for (int i=n; i>1; i--){
            System.out.println("n: " + i);
            result *= i;
        }
        return result;
    }
    public static void main(String[] args) throws Exception {
        // System.out.println(factorial(8));
        System.out.println(factorial_Loop(5));
    }
}
