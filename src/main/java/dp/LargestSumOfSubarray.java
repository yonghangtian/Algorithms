package dp;

/**
 * @author tian
 */
public class LargestSumOfSubarray {
    public static void main(String[] args) {

    }

    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                dp[i] = nums[i];
                result = dp[i];
            } else {
                dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
                result = Math.max(result, dp[i]);
            }
        }

        return result;
    }
}
