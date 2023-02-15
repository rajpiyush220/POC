package com.touchblankspot.common;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

/**
 *
 */
public class RandomGenerator {

    /**
     * @return
     */
    public static UUID generateRandomUUID() {
        return UUID.randomUUID();
    }

    /**
     * @param length
     * @param useLetters
     * @param useNumbers
     * @return
     */
    public static String generateRandomString(Integer length, boolean useLetters, boolean useNumbers) {
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    /**
     * @param count
     * @return
     */
    public static String printRandomString(int count){
        return RandomStringUtils.randomPrint(count);
    }
}
