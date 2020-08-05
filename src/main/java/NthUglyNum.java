public class NthUglyNum {
    public static void main(String[] args) {
        NthUglyNum ugly = new NthUglyNum();
        int a = 10;
        System.out.println(ugly.nthUglyNumber(a));
        System.out.println(ugly.nthUglyNumberShort(a));
    }

    public int nthUglyNumber(int n) {
        if (n <= 0) {return 0;}

        int[] uglyNums = new int[n];
        int multiplier2 = 0;
        int multiplier3 = 0;
        int multiplier5 = 0;
        int uglyNumCount = 0;

        while (uglyNumCount < n) {

            if (uglyNumCount == 0) {
                uglyNums[uglyNumCount] = 1;
            } else {
                uglyNums[uglyNumCount] = min(2*uglyNums[multiplier2], 3*uglyNums[multiplier3],5*uglyNums[multiplier5]);
            }


            while (2 * uglyNums[multiplier2] <= uglyNums[uglyNumCount]) {
                multiplier2 ++;
            }

            while (3 * uglyNums[multiplier3] <= uglyNums[uglyNumCount]) {
                multiplier3 ++;
            }

            while (5 * uglyNums[multiplier5] <= uglyNums[uglyNumCount]) {
                multiplier5 ++;
            }

            uglyNumCount ++;
        }

        return uglyNums[n-1];
    }

    public int nthUglyNumberShort(int n) {
        if (n <= 0) {return 0;}

        int[] uglyNums = new int[n];
        int multiplier2 = 0;
        int multiplier3 = 0;
        int multiplier5 = 0;
        int uglyNumCount = 1;

        uglyNums[0] = 1;

        while (uglyNumCount < n) {
            int n2 = 2 * uglyNums[multiplier2];
            int n3 = 3 * uglyNums[multiplier3];
            int n5 = 5 * uglyNums[multiplier5];

            uglyNums[uglyNumCount] = min(n2, n3, n5);

            if (uglyNums[uglyNumCount] == n2) {
                multiplier2++;
            }
            if (uglyNums[uglyNumCount] == n3) {
                multiplier3++;
            }

            if (uglyNums[uglyNumCount] == n5) {
                multiplier5++;
            }
            uglyNumCount ++;
        }

        return uglyNums[n-1];
    }

    public int min(int a, int b, int c) {
        int min = a > b ? b : a;
        min = min > c ? c : min;

        return min;
    }
}
