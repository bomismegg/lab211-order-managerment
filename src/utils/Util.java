package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Minh Tri
 */
public class Util {

    private static final String IGNORE_CASE_PATTERN = "(?i)";
    public static final String SEP = ", ";
    public static final String DATE_FORMAT = "MM/dd/yyyy";

    static Scanner sc = new Scanner(System.in);

    private Util() {
    }

    public static int inputInteger(String message, int minValue, int maxValue) {
        int val = minValue - 1;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(message + ": ");
            try {
                val = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
//                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (val < minValue || maxValue < val);
        return val;
    }

    public static String inputString(String message, boolean allowEmpty) {
        Scanner sc = new Scanner(System.in);
        String str = "";
        do {
            System.out.print(message + ": ");
            str = sc.nextLine();
        } while (!allowEmpty && str.isBlank());
        return str.trim();
    }

    public static boolean inputBoolean(String message) {
        Scanner sc = new Scanner(System.in);
        String res = "";
        do {
            System.out.print(message + ": ");
            res = sc.nextLine();
        } while (!res.trim().toLowerCase().startsWith("t") && !res.trim().toLowerCase().startsWith("f"));
        return res.trim().toLowerCase().startsWith("t");
    }

    public static boolean validateStringPattern(String str, String regex, boolean ignoreCase) {
        if (str != null && regex != null) {
            if (ignoreCase) {
                regex = Util.IGNORE_CASE_PATTERN + regex;
            }
            return str.matches(regex);
        }
        return false;
    }

    public static boolean validateString(String str, String regex, boolean ignoreCase) {
        if (str != null && regex != null) {
            if (ignoreCase) {
                regex = Util.IGNORE_CASE_PATTERN + regex;
            }
            return str.matches(regex);
        }
        return false;
    }

    public static String inputStringWithSize(String message, int min, int max) {
        Scanner sc = new Scanner(System.in);
        String str = "";
        do {
            System.out.print(message + ": ");
            str = sc.nextLine();
        } while (str.length() > min && str.length() < max);
        return str.trim();
    }

    public static double inputDouble(String msg) {
        double ret = 0;
        boolean flag;
        do {
            try {
                System.out.print(msg);
                flag = false;
                ret = Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(e);
                flag = true;
            }
        } while (flag);
        return ret;
    }

    public static Date toDate(String strDate) throws ParseException {
        if (strDate == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(Util.DATE_FORMAT);
        df.setLenient(false);
        return df.parse(strDate);
    }

    public static String toString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(Util.DATE_FORMAT);
        return df.format(date);
    }

    public static Date inputDate(String message, boolean allowEmpty) {
        Scanner sc = new Scanner(System.in);
        Date date = null;
        do {
            System.out.print(message + "(" + Util.DATE_FORMAT + "): ");
            try {
                date = toDate(sc.nextLine());
            } catch (ParseException ex) {

            }
        } while (date == null & !allowEmpty);
        return date;
    }
}
