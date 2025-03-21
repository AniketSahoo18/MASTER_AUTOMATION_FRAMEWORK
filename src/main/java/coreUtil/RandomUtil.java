package coreUtil;

public class RandomUtil {

    private RandomUtil(){}

    public static int getId() {

        return FakerGen.getNumber(100,1000);
    }

    public static String getFirstName() {

        return FakerGen.getFirstName().toLowerCase();
    }

    public static String getLastName() {

        return FakerGen.getLastName().toLowerCase();
    }
}
