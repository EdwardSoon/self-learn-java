import java.util.Calendar;

public class GetDay {

      public static String findDay(int month, int day, int year) {
        
        Calendar c = Calendar.getInstance(); // create an object for Calendar abstract class (thus cannot create an object but need to instantiate through another way)
        c.set(year, month -1 , day); // month starts from index = 0
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); // DAY_OF_WEEK is the field created in Calendar class, thus access in this way. .get() is a method to get anything field we needed for the specific instance we created.
        String[] day_array = {"SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"}; 
        
        return day_array[dayOfWeek-1];  
    }
    public static void main(String[] args) throws Exception {
        System.out.println(GetDay.findDay(1,25,2023));
    }
}
