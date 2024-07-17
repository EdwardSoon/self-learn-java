// Adapted from countWordsIO
import java.io.*;
import java.util.*;

public class countWordBufferedIO {
    public static void main(String[] args) throws IOException {
        // create reading stream from file to java
        FileReader fr = new FileReader("/Users/edward.soonrw/Desktop/Java/countWordsFileBuffered/src/engWords.txt"); // use fileReader for creating stream to connect to file
        BufferedReader bfr = new BufferedReader(fr);  // create a BufferedReader Stream on top of FileReader Stream to operate efficiently
        String line;
        HashMap<String, Integer> countWordsMap = new HashMap<>();  // to store the strings and count their occurrence

        // read from file and count occurrence
        while ((line = bfr.readLine()) != null){ // read line by line
            String[] words = line.split(" "); // no need split by next line, as we are reading line by line
            for (String word : words){
                if(countWordsMap.containsKey(word)){
                    countWordsMap.put(word, countWordsMap.get(word) + 1);
                }
                else{
                    countWordsMap.put(word, 1);
                }
            }
        }
        System.out.print(countWordsMap);
        bfr.close();
        fr.close();

        // create writing stream from java to file
        FileWriter fw = new FileWriter("/Users/edward.soonrw/Desktop/Java/countWordsFileBuffered/src/engWordsCounts.csv");
        BufferedWriter bfw = new BufferedWriter(fw); // create a BufferedWriter stream on top of FileWriter Stream

        // sort descending order byValue
        List<Map.Entry<String,Integer>> countWordsMapList = new ArrayList<>(countWordsMap.entrySet());
        Comparator<Map.Entry<String, Integer>> byValueDesc = Map.Entry.comparingByValue(Comparator.reverseOrder());
        countWordsMapList.sort(byValueDesc);

        // write to file
        //   write header
        String header = "word,occurrence";
        bfw.write(header);
        //   write body: (split word and occurrence counts)
        for (Map.Entry<String, Integer> entry : countWordsMapList){
            String word = entry.getKey();
            int occurrence = entry.getValue();
            bfw.newLine(); // create newLine()
            bfw.write(word + "," + occurrence); // it auto flush in BufferedWriter
        }
        bfw.close();
        fw.close();
    }
}