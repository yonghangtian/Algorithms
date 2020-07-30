import java.util.Scanner;

/**
 * @author tian
 * @since June 26 2020
 */
public class BigDigitalQuestions {

    public static char minus = '-';

    public static void main(String[] args) {
        BigDigitalQuestions bd = new BigDigitalQuestions();
        Scanner sc = new Scanner(System.in);

        // print all integer from 0 to max n digits
        //int n = 2;
        //bd.printMaxDigits(n);
        // print the sum of two big digits
        String a1 = sc.nextLine();
        String a2 = sc.nextLine();
        bd.printSumOfTwoBigDigits(a1, a2);
    }

    /**
     *
     * @param a1, should be positive value
     * @param a2, can be positive or negative
     * @return a1+a2
     */
    private void printSumOfTwoBigDigits(String a1, String a2) {

        char[] num1 = a1.toCharArray();
        char[] num2 = a2.toCharArray();

        if (num2[0] == minus) {
            num2[0] = '0';
            char[] result = this.printDiffOfTwoBigDigitsCore(num1, num2);
            if (result[0] == minus){
                System.out.print(minus);
                result[0] = '0';
                printBigDigit(result);
            }else{
                printBigDigit(result);
            }
        }else {
            char[] result = printSumOfTwoBigDigitsCore(num1, num2);
            printBigDigit(result);
        }

    }

    private char[] printSumOfTwoBigDigitsCore(char[] num1, char[] num2) {
        int num = 0;
        int carryOver = 0;

        //for inner loop
        int j = 0;
        int len = num1.length >= num2.length ? num1.length + 1 : num2.length + 1;
        char[] result = new char[len];

        for (int i = len - 1; i >= 0; i--) {

            if (j < num1.length && j < num2.length){
                num = (num1[num1.length - j - 1] - '0') + (num2[num2.length - j - 1] - '0');
                j++;
            } else if (j < num1.length){
                num = num1[num1.length - j - 1] - '0';
                j++;
            } else if (j < num2.length){
                num = num2[num2.length - j - 1] - '0';
                j++;
            } else {
                num = 0;
            }

            num += carryOver;

            if (num >= 10) {
                if (i == 0){
                    System.out.println("Index out of Array, the num is " + num);
                    return result;
                }else {
                    carryOver = num/10;
                    num %= num;
                }
            }else{
                carryOver = 0;
            }

            result[i] = (char) ('0' + num);
        }
        return result;
    }

    /**
     * num1's valid length should not be equal with num2's valid length.
     * @param num1
     * @param num2
     * @return
     */
    private char[] printDiffOfTwoBigDigitsCore(char[] num1, char[] num2) {
        int num1StartIdx = findFirstNoneZeroIdx(num1);
        int num2StartIdx = findFirstNoneZeroIdx(num2);

        int bigger = (num1.length - num1StartIdx) >= (num2.length - num2StartIdx) ? 1 : -1;

        char[] big, small;

        if (bigger == 1){
            big = num1;
            small = num2;
        }else {
            big = num2;
            small = num1;
        }

        char[] result = new char[big.length];
        int num = 0;
        int j = 0;
        int subtracted = 0;
        for (int i = result.length - 1; i >= 0 ; i--) {
            if (j < big.length && j < small.length){
                num = (big[big.length - j - 1] - '0') - (small[small.length - j - 1] - '0');
                j++;
            } else if (j < big.length){
                num = num1[num1.length - j - 1] - '0';
                j++;
            }else {
                num = 0;
            }

            // 减去借位
            num -= subtracted;

            if (num < 0){
                subtracted = 1;
                num += 10;
            }else{
                subtracted = 0;
            }

            result[i] = (char) (num + '0');
        }

        return result;
    }

    private void printMaxDigits(int n) {
        if (n <= 0){
            System.out.println("Please do not input negative value or zero!");
            return;
        }
        
        //initialization
        char[] nums = new char[n];

        for (int i = 0; i < n; i++) {
            nums[i] = '0';
        }
        
        //Print number while check whether num reach it's max
        while(!increaseNum(nums)){
            this.printBigDigit(nums);
        }
    }

    /**
     *
     * @param nums
     * @return whether num reach it's max.
     */
    private boolean increaseNum(char[] nums) {
        int num = 0;
        int carryOver = 0;
        boolean isOverFlow = false;

        for (int i = nums.length - 1; i >= 0; i--) {
            num = nums[i] - '0';

            num = num + carryOver;

            if (i == nums.length - 1){
                num++;
            }

            if (num >= 10) {
                if (i == 0){
                    return true;
                }else {
                    carryOver = num/10;
                    num %= num;
                }
            }else{
                carryOver = 0;
            }

            nums[i] = (char) ('0' + num);
        }

        return false;
    }

    /**
     *
     * @param nums
     * @result print the num from the first non-zero digit
     */
    private void printBigDigit(char[] nums) {
        int noneZeroIdx = findFirstNoneZeroIdx(nums);

        for (int i = noneZeroIdx; i < nums.length; i++) {
            System.out.print(nums[i]);
        }
        System.out.println();
    }

    private int findFirstNoneZeroIdx(char[] nums){
        int noneZeroIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != '0'){
                break;
            }else {
                noneZeroIdx++;
            }
        }
        return noneZeroIdx;
    }
}
