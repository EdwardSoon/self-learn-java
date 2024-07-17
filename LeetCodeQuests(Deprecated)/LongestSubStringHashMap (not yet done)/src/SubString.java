/*
PROBLEM:
Given a string s, find the length of the longest 
substring without repeating characters. */

/*
 * 1. .containsKey( to detect the unqiue )
 */

import java.util.Arrays;
import java.util.HashMap;

public class SubString {

    static String s = "abcadde";
    public static void main(String[] args) throws Exception {
        char[] charArr = s.toCharArray();
        int n = charArr.length;
        String subStr = "";
        HashMap<Character,Integer> charMap = new HashMap<>();

        // for (int i=0; i<n; i++){
        //     charArr[i] 
        // }


        for(int i=0; i<n; i++){
            subStr = "";
            for(int j=i; j<n; j++){
                subStr += charArr[j];
                System.out.println(subStr);
            }
        }



    //   int n = s.length();
    //     int maxLength = 0;
    //     int[] charIndex = new int[128];
    //     Arrays.fill(charIndex, -1);
    //     int left = 0;
    
    //     for (int right = 0; right < n; right++) {
    //         if (charIndex[s.charAt(right)] >= left) {
    //             left = charIndex[s.charAt(right)] + 1;
    //         }
    //         System.out.println(charIndex['a']);
    //         charIndex[s.charAt(right)] = right;
    //         maxLength = Math.max(maxLength, right - left + 1);
    //     }


    }
}
