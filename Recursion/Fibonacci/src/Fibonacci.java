// https://www.geeksforgeeks.org/java-fibonacci-series/

import java.util.HashMap;

public class Fibonacci {

    // Recursive function
    // T(n) = O(2^N) ; S(n) = O(N)
    public static int fibonacci(int n){
        if(n>=0 && n <= 1){
            return n;
        }
        return fibonacci(n-1) + fibonacci(n-2) ;    // T(cost) = 2^N because in each function, TWO FUNCTIONS are called recursively N times

    }

    // Memoization with Recursion: store the expensive function calls and return the cached results when the input happens again
        //method 1: HashMap
        // T(n) = O(N) -- single recursive calls for each value of n (as value of N that has computed will be cached and reused)
        // S(n) = O(N) -- due to hashmap size adding and function calls N times
    private static HashMap<Integer, Integer> memoMap = new HashMap<>(); // define a HashMap outside the method
    public static int fibonacciMemoMap(int n){
        // dont initiate the HashMap inside, if not everytime it calls a function the Hashmap will be reinitiated which does not do the memoization anymore as the previous result cached will be gone
        if(memoMap.containsKey(n)){
            return memoMap.get(n);
        }
        else if(n>=0 && n<=1) {
            return n;
        }
        else{
            int result = fibonacciMemoMap(n-1) + fibonacciMemoMap(n-2);
            memoMap.put(n,result);
            return result;
        }
    }

        //method 2: brute force: need to initiate an array
            // T(n) = O(N) 
            // S(n) = O(N) -- N size of array + N times of function calls
    public static int fibonacciMemoBF(int n, int[] memo){
        if(memo[n] != 0){ // check if the values has computed, if yes then retrieve and end the recursive call
            return memo[n];
        }
        else if(n >= 0 && n<=1){
            return n;
        } 
        else {
            memo[n] = fibonacciMemoBF(n-1, memo) + fibonacciMemoBF(n-2, memo);
            return memo[n];
        }
    }



    // Brute force: For Loop method
    // T(n) = O(N) ; S(n) = O(1)
    public static int fibonacciLoop(int n){
        // define the first fibonacci from the first two seeds
        int fir = 0 ,sec = 1, result = fir + sec;
        
        if (n == 1){ // second seed
            return sec;
        }
        if (n == 0){ // first seed
            return fir;
        }
        for (int i = 2; i<n; i++){  // starts from second seed 
            fir = sec;          // first number now becomes the second number to traverse
            sec = result;       // second number now becomes the result of the previous loop
            result = fir + sec; // first + sec to get the current result
        }
        return result;
    }


    // Array
    // T(n) = O(N) execute N times; S(n) = O(N) takes N spaces for array
    public static int fibonacciArr(int n){
        int[] f = new int[n + 2]; // declare the two seeds first
        
        f[0] = 0;
        f[1] = 1;

        for (int i=2; i<=n; i++){
            f[i] = f[i-1] + f[i-2];
        }

        return f[n];
    }

    public static void main(String[] args) throws Exception {
        // System.out.println(fibonacci(10));

        // System.out.println(fibonacciLoop(10));

        // System.out.println(fibonacciArr(10));

        // int n = 9;
        // int[] memo = new int[n+1];  // The size of the memo array is n + 1 to accommodate values from 0 to n.
        // System.out.println(fibonacciBF(n, memo));

        System.out.println(fibonacciMemoMap(10));


    }
}
