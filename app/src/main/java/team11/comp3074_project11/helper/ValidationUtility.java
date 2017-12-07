package team11.comp3074_project11.helper;

/**
 * Created by aline on 2017-12-07.
 */

public class ValidationUtility {
    /** Validates whether a credit card is a 16-character long numeric string.
     * @param   ccNumber  a String that represents a credit card number
     * @return            a boolean meaning whether the credit card satisfies all the conditions
     */
    public static boolean isCreditCardValid(String ccNumber){
        //Remove any special characters that are in between the numbers
        ccNumber = ccNumber.trim().replace(" ","").replace("-", "").replace("/","").replace(".","");
        return ccNumber != null && ccNumber.matches("[-+]?\\d*\\.?\\d+") && ccNumber.length() == 16;
    }
}
