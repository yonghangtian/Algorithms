import java.util.ArrayList;
import java.util.List;

public class Cards {
    public static void main(String[] args) {
        int[] array = new int[]{10,11,0,12,6};

        Cards cards = new Cards();

        System.out.println(cards.isStraight(array));
    }

    public int sumNums(int n) {
        boolean flag = (n > 0) && (n += sumNums(n - 1)) > 0;

        return n;
    }

    public boolean isStraight(int[] nums) {
        List<Integer> nonZeros = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (nums[i] != 0) {
                nonZeros.add(nums[i]);
            }
        }

        nonZeros.sort(null);
        int zeros = 5 - nonZeros.size();
        int start = 0, end = 1;
        int gaps = 0;
        while(end < nonZeros.size()) {

            if (nonZeros.get(end).equals(nonZeros.get(start))) {
                return false;
            }
            gaps += nonZeros.get(end) - nonZeros.get(start) - 1;
            start = end;
            end ++;
        }
        return gaps > zeros ? false : true;

    }
}
