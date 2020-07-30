public class FindNthDigit {
    /*数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
    请写一个函数，求任意第n位对应的数字。*/

    public static void main(String[] args) {
        int target = 11;
        FindNthDigit finder = new FindNthDigit();

        System.out.println(finder.findNthDigit(target));
    }

    public int findNthDigit(int n) {
        int digit = 1;
        long start = 0;
        long count = 10;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            if (start == 0) {
                start = 1;
            }
            start *= 10;
            count = digit * start * 9;
        }
        //这里减去1，是是因为10以内的数只减去了9，所以这里减一用来弥补。
        long num = start + n / digit; // 2.
        return Long.toString(num).charAt(n % digit) - '0'; // 3.
    }

}
