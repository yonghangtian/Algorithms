public class IntegerPowerOfNumber {
    public static void main(String[] args) {
        IntegerPowerOfNumber power = new IntegerPowerOfNumber();

        Double base = 5.0;
        int exponent = 2;
        Double result = power.powerOfNumber(base, exponent);
        System.out.println(result);
    }

    /**
     *
     * @param base, can be positive, zero or negative;
     * @param exponent, can be positive, zero or negative;
     * @return double type of result
     */
    private double powerOfNumber(Double base, int exponent) {
        if (exponent == 0) {
            return 1.0;
        }

        if (base == 0 && exponent < 0){
            System.out.println("Invalid input: zero cannot be divided.");
            return -1.0;
        }

        int absExp = Math.abs(exponent);
        double result = this.powerOfUnsignedExponentIteration(base, absExp);

        if (exponent < 0){
            return 1/result;
        }
        return result;
    }

    /**
     *
     * @param base, can be positive, zero or negative;
     * @param exponent, should be positive;
     * @return double type of result
     */
    private double powerOfUnsignedExponent(Double base, int exponent) {
        double result = base;

        for (int i = 1; i < exponent; i++) {
            result *= base;
        }

        return result;
    }

    /**
     *
     * @param base, can be positive, zero or negative;
     * @param exponent, should be positive;
     * @return double type of result
     */
    private double powerOfUnsignedExponentRecursive(Double base, int exponent) {
        if (exponent == 0) {
            return 1.0;
        }

        if (exponent == 1) {
            return base;
        }

        double result = 1.0;

        result = this.powerOfUnsignedExponentRecursive(base, exponent >> 1);

        result *= result;

        if ( (exponent & 1) == 1) {
            result *= base;
        }

        return result;
    }

    /**
     *
     * @param base, can be positive, zero or negative;
     * @param exponent, should be positive;
     * @return double type of result
     */
    private double powerOfUnsignedExponentIteration(Double base, int exponent) {
        if (exponent == 0) {
            return 1.0;
        }

        double result = 1.0;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result *= base;
            }

            base *= base;

            exponent = exponent >> 1;
        }

        return result;
    }
}
