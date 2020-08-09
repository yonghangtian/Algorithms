package wc201;

public class FindKthBit {
    /**
     * 5484. 找出第 N 个二进制字符串中的第 K 位 显示英文描述
     * 题目难度Medium
     * 给你两个正整数 n 和 k，二进制字符串  Sn 的形成规则如下：
     *
     * S1 = "0"
     * 当 i > 1 时，Si = Si-1 + "1" + reverse(invert(Si-1))
     * 其中 + 表示串联操作，reverse(x) 返回反转 x 后得到的字符串，而 invert(x) 则会翻转 x 中的每一位（0 变为 1，而 1 变为 0）
     *
     * 例如，符合上述描述的序列的前 4 个字符串依次是：
     *
     * S1 = "0"
     * S2 = "011"
     * S3 = "0111001"
     * S4 = "011100110110001"
     * 请你返回  Sn 的 第 k 位字符 ，题目数据保证 k 一定在 Sn 长度范围以内。
     *
     *
     *
     * 示例 1：
     *
     * 输入：n = 3, k = 1
     * 输出："0"
     * 解释：S3 为 "0111001"，其第 1 位为 "0" 。
     * 示例 2：
     *
     * 输入：n = 4, k = 11
     * 输出："1"
     * 解释：S4 为 "011100110110001"，其第 11 位为 "1" 。
     * 示例 3：
     *
     * 输入：n = 1, k = 1
     * 输出："0"
     * 示例 4：
     *
     * 输入：n = 2, k = 3
     * 输出："1"
     *
     *
     * 提示：
     *
     * 1 <= n <= 20
     * 1 <= k <= 2n - 1
     */
    public static void main(String[] args) {
        int n = 4, k = 11;
        FindKthBit bit = new FindKthBit();

        System.out.println(bit.findKthBit(n, k));
    }

    public char findKthBit(int n, int k) {
        String[] strs = new String[n];

        strs[0] = "0";

        for (int i = 1; i < n; i++) {
            strs[i] = strs[i-1] + "1" + reverse(invert(strs[i-1]));
        }

        return strs[n-1].charAt(k-1);
    }

    private String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = str.length() - 1; i >= 0; i --) {
            sb.append(str.charAt(i));
        }

        return sb.toString();
    }

    private String invert(String str) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < str.length(); i ++) {
            if (str.charAt(i) == '1') {
                sb.append('0');
            }else {
                sb.append('1');
            }
        }
        return sb.toString();
    }
}
