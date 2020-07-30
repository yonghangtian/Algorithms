import java.util.*;

/**
 * @author tian
 */
public class StringPermutation {

    List<String> list;
    public static void main(String[] args) {
        String str = "abc";
        StringPermutation permutation = new StringPermutation();

        String[] strs = permutation.permutation(str);

        System.out.println(Arrays.toString(strs));
    }
    public String[] permutation(String s) {
        if (s == null || s.length()== 0) {
            return null;
        }

        char[] chrs = s.toCharArray();
        list = new ArrayList<>();

        stringPermutation(chrs, 0, chrs.length - 1);

        Set<String> set = new HashSet<>(list);
        String[] strs = new String[set.size()];

        set.toArray(strs);
        return strs;
    }

    private void stringPermutation(char[] chrs, int start, int end) {
        if (start > end) {
            list.add(new String(chrs));
            return;
        } else {
            for (int i = start; i <= end; i++) {
                swap(chrs, i, start);
                stringPermutation(chrs, start+1, end);
                swap(chrs, i, start);
            }
        }
    }

    public static void swap(char[] chrs, int i, int start) {
        char temp = chrs[i];
        chrs[i] = chrs[start];
        chrs[start] = temp;
    }

}
