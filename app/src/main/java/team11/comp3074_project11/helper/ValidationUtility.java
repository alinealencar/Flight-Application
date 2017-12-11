package team11.comp3074_project11.helper;

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
}
