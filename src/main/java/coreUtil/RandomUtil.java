package coreUtil;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RandomUtil {

    private RandomUtil(){}

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String TIMESTAMP = "yyyyMMDDHHMMSS";
    private static final String ALPHACHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String LOWERCASECHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASECHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CUSTOMCHARS = "0123456789!@#$%^&*";

    public static int getId() {

        return FakerGen.getNumber(100,1000);
    }

    public static String getFirstName() {

        return FakerGen.getFirstName().toLowerCase();
    }

    public static String getLastName() {

        return FakerGen.getLastName().toLowerCase();
    }

    public static int getRandomInt(int minValue, int maxValue) {

        return RANDOM.nextInt(maxValue - minValue + 1) + minValue;
    }

    public static <E> E getRandomItemFromList(List<E> list) {

        return list.get(RANDOM.nextInt(list.size()));
    }

    public static String generateRandomString(int length, String string) {

        return RANDOM.ints(length, 0, string.length()).mapToObj(string::charAt).map(String::valueOf).collect(Collectors.joining());
    }

    public static String generateRandomAlphanumericWithTimestamp(int stringSize) {

        return generateTimeStampWithInput(generateRandomString(stringSize, ALPHACHARS));
    }

    public static String generateTimeStampWithInput(String string) {

        return string + "_" + generateTimeStamp();
    }

    public static String generateTimeStamp() { return new SimpleDateFormat(TIMESTAMP).format(new Date());}
}
