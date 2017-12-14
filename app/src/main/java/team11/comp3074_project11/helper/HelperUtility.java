package team11.comp3074_project11.helper;

/**
 * Created by aline on 2017-12-14.
 */

public class HelperUtility {
    static public String doubleToHours(Double d){
        int hours = 0;
        int minutes = 0;

        hours = (int) Math.floor(d);
        minutes = (int) ((d - hours)*60);
        
        return Integer.toString(hours) + "h" + Integer.toString(minutes) + "m";

    }

}
