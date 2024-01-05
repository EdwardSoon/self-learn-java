public class App {
    public static String getSmallestAndLargest(String s, int k) {
        String smallest = "";
        String largest = "";
        String x,y;
        // Complete the function
        for (int i=0; i<=s.length()-k; i++){
            x = s.substring(i,i+k);
            if(smallest == "" || x.compareTo(smallest) < 0){
                smallest = x;
            }
            else {
                continue;
            }
        }
        for (int j=0; j<=s.length()-k; j++){
            y = s.substring(j, j+k);
            if (y.compareTo(largest) > 0){
                largest = y;
            }
            else{
                continue;
            }
        }
        
        
        return smallest + "\n" + largest;
    }


    public static void main(String[] args) throws Exception {
        String x = "aa";
        String y = "zz";
        System.out.println(App.getSmallestAndLargest("abAcsdfdfazsdasZZ", 1));
    }
}
