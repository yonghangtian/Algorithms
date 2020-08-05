public class FirstUniqChar {
    public static void main(String[] args) {
        FirstUniqChar chars = new FirstUniqChar();
        String a = "aaaaabbbbc";
        String b = "";

        System.out.println(chars.firstUniqChar(b));

        System.out.println(chars.firstUniqCharInStream(a));
    }

    public char firstUniqChar(String s) {
        if (s.length() == 0) {
            return ' ';
        }

        int[] times = new int[26];

        for (int i = 0; i < times.length; i ++) {
            times[i] = -1;
        }

        for (int i = 0; i < s.length(); i ++) {
            int idx = s.charAt(i) - 'a';

            if (times[idx] == -1) {
                times[idx] = 1;
            } else {
                times[idx] ++;
            }
        }

        for (int i = 0; i < s.length(); i ++) {
            int idx = s.charAt(i) - 'a';

            if (times[idx] == 1){
                return s.charAt(i);
            }
        }

        return ' ';
    }

    public char firstUniqCharInStream(String s) {
        if (s.length() == 0) {
            return ' ';
        }

        int[] prevPos = new int[256];

        for (int i = 0; i < prevPos.length; i ++) {
            prevPos[i] = -1;
        }

        for (int i = 0; i < s.length(); i ++) {
            int idx = s.charAt(i);

            if (prevPos[idx] == -1) {
                prevPos[idx] = i;
            } else {
                prevPos[idx] = -2;
            }
        }

        char result = ' ';
        int minIdx = Integer.MAX_VALUE;
        for (int i = 0; i < prevPos.length; i ++) {
            if (prevPos[i] > 0 && prevPos[i] < minIdx) {
                minIdx = prevPos[i];
                result = (char) i;
            }
        }

        return result;
    }
}
