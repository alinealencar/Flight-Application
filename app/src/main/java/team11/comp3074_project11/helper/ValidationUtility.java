package team11.comp3074_project11.helper;

import android.widget.Spinner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**Validates if the inputted value exists or not
     *
     * @param data     a string value that the user inputted in edit text
     * @return          true if the data is missing, false if the data exists
     */
    public static boolean isMissing(String data){
        if(data.equals("") || data.equals(null)){
            return true;
        }
        else{
            return false;
        }
    }

    /**Validates if the inputted value is all alphabet
     *
     * @param data  a string value that the user inputted in edit text
     * @return      true if the data is all alphabet, false if non-alphabet letter exists
     */
    public static boolean isAlphabet (String data){
        char[] chars = data.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    /**Validates if the inputted value is valid email
     *
     *       @param email a string value that user inputted in edit text
     *       @return true if the email is valid email pattern, false if invalid email pattern
     */
    public static boolean isEmail(String email){
        Pattern pattern;
        Matcher matcher;
        final String  emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        pattern = Pattern.compile(emailPattern);

        matcher = pattern.matcher(email);
        return matcher.matches();

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

        List<Airport> airports = SearchUtility.getAirports(db, db.getReadableDatabase());
        List<String> airportNames = new ArrayList<>();
        for(Airport a: airports)
            airportNames.add(a.getAirportName().toLowerCase());

        for(String str: airportNames){
            if(str.trim().toLowerCase().contains(airport.toLowerCase()))
                return true;
        }

        return false;
    }

    /**
     * Validates whether the date passed is after the current date.
     *
     * @param dayStr        String that represents a day
     * @param monthStr      String that represents a month
     * @param yearStr       String that represents a year
     * @return              returns true the inputted date is after the current date, false otherwises
     */
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


//    public static boolean signUpInputValidation(String firstName, String lastName, String email, String password, String creditcardNo){
//        if(HelperUtility.isMissing(firstName) || HelperUtility.isMissing(lastName) || HelperUtility.isMissing(email) || HelperUtility.isMissing(password) || HelperUtility.isMissing(creditcardNo)){
//            return false;
//        }else if(!HelperUtility.isAlphabet(firstName) || !HelperUtility.isAlphabet()){
//            return false
//        }
//    }



}
