package binarytree;

/**
 * @author tian
 */
public class HasSubTree {
    // we have to construct two binary tree, too time consuming.
    // Please check leetcode:https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/submissions/
    public static void main(String[] args) {
        double a = 1.000000002;
        double b = 1.000000001;

        System.out.println(isEqual(a, b));
    }

    private static boolean isEqual(double a, double b) {
        if (Math.abs(a-b) < (double)1.0e-7) {
            return true;
        }else{
            return false;
        }
    }
}
