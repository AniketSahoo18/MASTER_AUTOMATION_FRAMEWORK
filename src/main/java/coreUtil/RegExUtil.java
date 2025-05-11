package coreUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtil {

    public static final String INT_FLOAT_VALUE_REGEX = "^-?\\d+(\\.\\d+)?$";
    public static final String PERCENTAGE_VALUE_REGEX = ".+%";


    public static boolean partiallyMatchesRegex(String string, String regex) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    public static boolean completelyMatchesRegex(String string, String regex) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static String formattingValuebyRegex(String valueForFormatting, String regex) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(valueForFormatting);
        if(matcher.find()) {
            return matcher.group().trim();
        }
        else {
            System.out.println("Matcher find False");
            return "";
        }
    }
}
