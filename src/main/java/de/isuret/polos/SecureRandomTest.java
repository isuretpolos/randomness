package de.isuret.polos;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SecureRandomTest {

    private static final int ARRAY_COUNT = 100;
    private static final int MAX_ROUNDS = 10000000;

    public static void main(String[] args) throws Exception {

        testBoth();
        System.out.println("-----------------");
        testRandom();
        System.out.println("-----------------");
        testSecureRandom();
    }

    private static void testBoth() throws NoSuchAlgorithmException {

        SecureRandom random = SecureRandom.getInstanceStrong();

        Boolean finished = false;

        while (!finished) {
            for (int y = 0; y < 12; y++) {
                for (int x = 0; x < 30; x++) {
                    Integer result = random.nextInt(1000);
                    if (result < 10) {
                        System.err.print(result);
                        System.err.print(" ");
                        finished = true;
                    } else {
                        System.out.print(result);
                        System.out.print(" ");
                    }
                }
                System.out.println(" ");
            }
        }

        System.out.println("=============================================");

        finished = false;

        while (!finished) {
            for (int y = 0; y < 12; y++) {
                for (int x = 0; x < 30; x++) {
                    Integer result = ThreadLocalRandom.current().nextInt(1000);
                    if (result < 10) {
                        System.err.print(result);
                        System.err.print(" ");
                        finished = true;
                    } else {
                        System.out.print(result);
                        System.out.print(" ");
                    }
                }
                System.out.println(" ");
            }
        }
    }

    private static void testSecureRandom() {

        int[] ndigits = new int[ARRAY_COUNT];
        double x;
        int n;

        SecureRandom myRandom = new SecureRandom();

        // Initialize the array
        for (int i = 0; i < ARRAY_COUNT; i++) {
            ndigits[i] = 0;
        }

        // Test the random number generator a whole lot
        for (long i = 0; i < MAX_ROUNDS; i++) {
            // generate a new random number between 0 and 9
            x = myRandom.nextDouble() * ARRAY_COUNT;
            n = (int) x;
            //count the digits in the random number
            ndigits[n]++;
        }

        // Print the results
        for (int i = 0; i < ARRAY_COUNT; i++) {
            System.out.println(i + ": " + ndigits[i]);
        }

    }

    private static void testRandom() {

        int[] ndigits = new int[ARRAY_COUNT];
        double x;
        int n;

        Random myRandom = new Random();

        // Initialize the array
        for (int i = 0; i < ARRAY_COUNT; i++) {
            ndigits[i] = 0;
        }

        // Test the random number generator a whole lot
        for (long i = 0; i < MAX_ROUNDS; i++) {
            // generate a new random number between 0 and 9
            x = myRandom.nextDouble() * ARRAY_COUNT;
            n = (int) x;
            //count the digits in the random number
            ndigits[n]++;
        }

        // Print the results
        for (int i = 0; i < ARRAY_COUNT; i++) {
            System.out.println(i + ": " + ndigits[i]);
        }
    }
}

