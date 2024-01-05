/*
PROBLEM:
Given a string s, find the length of the longest 
substring without repeating characters. */

import java.util.ArrayList;
import java.util.HashMap;

public class SubString {

    static String s = "aaaabaabcdaaaabcdefaaaa"; 
    static int n = s.length();
    public static void main(String[] args) throws Exception {
        char[] charArr = s.toCharArray();
        String subStr = "";
        String longestStr = "";
        int maxLen = 0;
        // HashMap<Integer,Character> charMap = new HashMap<>();
        ArrayList<Character> charList = new ArrayList<>();
        // System.out.println("list: " + charArr);

        for(int j=0; j<n; j++){
            for (int i=j; i<n; i++){ // i=j since we wouldnt start from beginning already
                if (charList.contains(charArr[i])){
                    charList.clear();
                    subStr = "";
                    break;
                }
                else{
                charList.add(charArr[i]);
                subStr += charArr[i];
                    if(subStr.length() > maxLen){
                        maxLen = subStr.length();
                        longestStr = subStr;
                        System.out.println("Current longest substring is: " + subStr);
                    }
                }

            }
        }       
         System.out.println("The longest substring: \"" + longestStr + "\" with len: " + maxLen);
        

    }
}
