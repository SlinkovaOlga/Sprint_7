package services;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.Courier;

public class CourierGenerator {
    static int targetLength = 7;

    public static String randomLogin() {
        return RandomStringUtils.randomAlphabetic(targetLength);
    }
    public static String randomPassword() {
        return RandomStringUtils.randomAlphanumeric(targetLength);
    }
    public static String randomFirstName() {
        return RandomStringUtils.randomAlphabetic(targetLength);
    }
    public static Courier randomCourier() {
        return new Courier(randomLogin(), randomPassword(), randomFirstName());
    }
}