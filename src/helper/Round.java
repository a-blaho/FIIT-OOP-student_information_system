package helper;

/**
 * Helper class to round a number
 */
public class Round {
    /**
     * Rounds double number to two decimals
     * @param input
     * @return double
     */
    public static double round(double input) {
        input *= 100;
        int number = (int) input;
        input = (double) number / 100;
        return input;
    }
}
