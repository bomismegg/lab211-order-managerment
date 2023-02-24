package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Minh Tri
 */
public class Validation {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public static boolean validateDate(Date date) {
        DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        try {
            String inputDate = sdf.format(date);
            Date parsedDate = sdf.parse(inputDate);
            return parsedDate.equals(date);
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean checkOrderID(String Id) {
        if (Id.matches("[D]+\\d{3}")) {
            return true;
        }
        return false;
    }

    public static boolean checkProductID(String Id) {
        if (Id.matches("[P]+\\d{3}")) {
            return true;
        }
        return false;
    }

    public static boolean checkOrderQuantity(int quantity) {
        if (quantity > 0) {
            return true;
        }
        return false;
    }

    public static boolean checkCustomerID(String ID) {
        if ((ID.matches("[C]+\\d{3}"))) {
            return true;
        }
        return false;
    }

    public static boolean checkCustomerName(String name) {
        if (name.equals(null)) {
            return false;
        }
        return true;
    }

    public static boolean checkCustomerAddress(String Address) {
        if (Address.equals(null)) {
            return false;
        }
        return true;
    }

    public static boolean checkCustomerPhone(String Phone) {
        if (Phone.equals(null) && !(Phone.length() >= 10) && !(Phone.length() <= 12)) {
            return false;
        }
        return true;
    }
    
    public static boolean checkStatus(String status){
        if(status.trim().toLowerCase().startsWith("t")||status.trim().toLowerCase().startsWith("f")){
            return true;
        }
        return false;
    }
}
