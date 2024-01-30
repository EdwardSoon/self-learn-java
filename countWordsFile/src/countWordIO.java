/*
INPUT: To count the occurrence of words from a file
1. create a stream to connect to the file
2. read the data from the file
3. keep on .read() after everything store inside tempC char array by using i to store
4. convert the char[] to String
5. String split with space or next line with regex with .split() method
6. Store them into hashmap and count every occurrence.

OUTPUT: To sort the number of occurrence in descending order then to print to a csv file
1. create a stream to create and connect to the new csv file
2. sort the values of words occurrence into descending order
3. write result into CSV file characters by characters (in the format of "words" , "counts")
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class countWordIO {
    public static void main(String[] args) throws IOException {
        // create reading stream from java to file
        FileReader fr = new FileReader("/Users/edward.soonrw/Desktop/Java/countWordsFile/src/engWords.txt"); // use fileReader for creating stream to connect to file
        int unicode;
        char[] chars = new char[100];    // to store all characters into this chars -- limiting to 100 characters of words
        int indexChar = 0;
        HashMap<String, Integer> countWordsMap = new HashMap<>();  // to store the strings and count their occurrence

        // read from file
        while ((unicode = fr.read()) != -1){
            chars[indexChar] = (char) unicode;
            indexChar ++;
        }
        // count occurrence
        String words = new String(chars, 0, indexChar);     // convert the char[] into a string and remove all the extra created unused memory
        String[] wordsArr = words.split(" |[\n]");  // split based on space or next line
        for (String word : wordsArr){
            if (countWordsMap.containsKey(word)){
                countWordsMap.put(word, countWordsMap.get(word) + 1);
            }
            else {
                countWordsMap.put(word, 1);
            }
        }
        System.out.print(countWordsMap);
        fr.close();  // close the reader

        // create writing stream from java to file
        FileWriter fw = new FileWriter("/Users/edward.soonrw/Desktop/Java/countWordsFile/src/engWordsCounts.csv");
        // sort descending order in a DS that could maintain an order
        List<Map.Entry<String, Integer>> countWordsMapList = new ArrayList<>(countWordsMap.entrySet()); // convert HashMap(non-order) into a list(ordered array) of map (key-value pairs) so that we can retrieve the key-value in an array and respectively
        Comparator<Map.Entry<String,Integer>> byValue = Map.Entry.comparingByValue(Comparator.reverseOrder()); // create a Comparator object for sorting the list of the Map.Entry objects in it by Value to descending order
        countWordsMapList.sort(byValue); // sort the List based on the Comparator object.

        // write to file
        // write header
        char[] header = "words, occurrence".toCharArray();
        for (char c : header){
            fw.write(c);
        }
        fw.write('\n');   // next line
        // write body
        for (Map.Entry<String, Integer> entry : countWordsMapList){      // each entry of key-value pairs (Map.Entry) in the set
            char[] wordResult = entry.getKey().toCharArray();       // extracts the key from Map's Entry
            int wordCountsResult = entry.getValue() + 48;  // extracts the value from Map's Entry, and convert into ASCII number in decimal
            for (char c : wordResult) {
                fw.write(c);
            }
            fw.write(',');  // comma to separate
            fw.write(wordCountsResult);
            fw.write('\n');  // next line
        }
        fw.flush();  // flush all the data in stream to the file: flushing is necessary here as FileWriter doesn't automatically flush
        fw.close();
    }
}