package team11.comp3074_project11.helper;

import android.widget.Spinner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import team11.comp3074_project11.dataModel.Airport;
import team11.comp3074_project11.database.FlightAppDatabaseHelper;

/**
 * Created by aline on 2017-12-07.
 */

public class ValidationUtility {
    /** Validates whether a credit card is a 16-character long numeric string.
     *
     * @param   ccNumber  a String that represents a credit card number
     * @return            a boolean meaning whether the credit card satisfies all the conditions
     */
    public static boolean isCreditCardValid(String ccNumber){
        //Remove any special characters that are in between the numbers
        ccNumber = ccNumber.trim().replace(" ","").replace("-", "").replace("/","").replace(".","");
        return ccNumber != null && ccNumber.matches("[-+]?\\d*\\.?\\d+") && ccNumber.length() == 16;
    }

    /**
     * Validates whether the user selected the same origin and destination in the form.
     *
     * @param origin        a string that represents the origin of the flight in the search
     * @param destination   a string that represents the destination of the flight in the search
     * @return              a boolean meaning whether the user select an origin different from the destination
     */
    public static boolean isValidOriginDestination(String origin, String destination){
        return !origin.equals(destination);
    }

    /**
     * Validates whether the string passed as airport is a valid airport in the database.
     *
     * @param db            a FlightAppDatabaseHelper object. The database where all airports' names are stored
     * @param airport       the string passed as airport name
     * @return              a boolean meaning whether the passed airport name is a valid airport
     */
    public static boolean isValidAirport(FlightAppDatabaseHelper db, String airport){
        if(airport.trim().equals(""))
            return false;

        List<Airport> airports = SearchUtility.getAirports(db);
        List<String> airportNames = new ArrayList<>();
        for(Airport a: airports)
            airportNames.add(a.getAirportName().toLowerCase());

        for(String str: airportNames){
            if(str.trim().toLowerCase().contains(airport.toLowerCase()))
                return true;
        }

        return false;
    }

    public static boolean isValidDate(String dayStr, String monthStr, String yearStr){
        //No selected day, month and/or year
        if(dayStr.trim().equals("") || monthStr.trim().equals("") || yearStr.trim().equals(""))
            return false;

        //Selected date
        String dateStr = monthStr + "/" + dayStr + "/" + yearStr;
        DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
        try {
            Date date = df.parse(dateStr);
            //Current date
            Date curDate = new Date();

            //Compare of the selected date is later than the current date
            if(date.before(curDate))
                return false;
        } catch (ParseException e) {
            return false;
        }

        return true;


    }
}
