package dp;


import java.util.HashMap;
import java.util.Map;

/**
 * @author tian
 */
public class MaxNoDuplicateCharSubstring {
    public static void main(String[] args) {
        String a = "abcabcbb";
        String b = "aabaab!bb";
        MaxNoDuplicateCharSubstring dp = new MaxNoDuplicateCharSubstring();

        System.out.println(dp.lengthOfLongestSubstring(b));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }

        if (s.trim().length() == 0){
            return 1;
        }

        int curr = 0;
        int max = 0;

        int[] prevPos = new int[95];

        for (int i = 0; i < 95; i++) {
            prevPos[i] = -1;
        }

        for (int i = 0; i < s.length(); i ++) {
            int charPrevPos = prevPos[s.charAt(i) - 32];

            if (charPrevPos < 0 || i - charPrevPos > curr) {
                // continue, we dont update max every round.
                curr ++;
            } else {
                // Since we dont update max every round,
                // this curr is referring to last character.
                if (curr > max) {
                    max = curr;
                }
                // this curr is for this character.
                curr = i - charPrevPos;
            }

            prevPos[s.charAt(i) - 32] = i;
        }
        if (curr > max) {
            max = curr;
        }

        return max;
    }

    public int lengthOfLongestSubstringUseMap(String s) {
        if (s.length() == 0) {
            return 0;
        }

        if (s.trim().length() == 0){
            return 1;
        }

        int curr = 0;
        int max = 0;

        Map<Character, Integer> posMap = new HashMap<>();

        for (int i = 0; i < s.length(); i ++) {
            int charPrevPos = -1;

            if (posMap.containsKey(s.charAt(i))) {
                charPrevPos = posMap.get(s.charAt(i));
            }

            if (charPrevPos < 0 || i - charPrevPos > curr) {
                // continue, we dont update max every round.
                curr ++;
            } else {
                // Since we dont update max every round,
                // this curr is referring to last character.
                if (curr > max) {
                    max = curr;
                }
                // this curr is for this character.
                curr = i - charPrevPos;
            }

            posMap.put(s.charAt(i), i);
        }
        if (curr > max) {
            max = curr;
        }

        return max;
    }
}
