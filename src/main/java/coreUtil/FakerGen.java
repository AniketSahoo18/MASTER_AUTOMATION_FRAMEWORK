package coreUtil;

import com.github.javafaker.Faker;

public class FakerGen {

    private FakerGen(){}

    private static final Faker faker = new Faker();

    static int getNumber(int startValue, int endValue) {

        return faker.number().numberBetween(startValue, endValue);
    }

    static String getFirstName() {

        return faker.name().firstName();
    }

    static String getLastName() {

        return faker.name().lastName();
    }
}
