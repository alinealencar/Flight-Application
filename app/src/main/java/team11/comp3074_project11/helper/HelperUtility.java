package team11.comp3074_project11.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by aline on 2017-12-14.
 */

public class HelperUtility {
    static public String doubleToHours(Double d){
        int hours;
        int minutes;

        hours = (int) Math.floor(d);
        minutes = (int) ((d - hours)*60);
        
        return String.format("%02d", hours) + ":" + String.format("%02d", minutes);
    }

    static public String sumHours(Double initialTime, Double duration){
        String time1 = doubleToHours(initialTime);
        String time2 = doubleToHours(duration);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date1 = null, date2 = null;
        try {
            date1 = timeFormat.parse(time1);
            date2 = timeFormat.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long sum = date1.getTime() + date2.getTime();

        String time3 = timeFormat.format(new Date(sum));

        return time3;
    }

    static public String stringToStars(String data){
        int numOfCharacters = data.length();
        String result = "";

        for(int i = 0; i < data.length(); i++)
            result += "*";

        return result;
    }


}
