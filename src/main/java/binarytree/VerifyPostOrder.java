package binarytree;

class VerifyPostOrder {
    public static void main(String[] args) {
        int[] post = new int[]{4, 8, 6, 12, 16, 14, 10};

        VerifyPostOrder verify = new VerifyPostOrder();

        System.out.println(verify.verifyPostorder(post));
    }
    public boolean verifyPostorder(int[] post) {
        /*
        输入: [1,6,3,2,5]
        输出: false
        示例 2：

        输入: [1,3,2,6,5]
        输出: true
        */

        if (post == null) {
            return false;
        }

        if (post.length == 0) {
            return true;
        }

        int len = post.length, rightStart = 0, index = 0;

        int root = post[len-1];

        return verifyPostorder(post, 0, len-1);
    }

    public boolean verifyPostorder(int[] post, int start, int end) {

        if (start == end) {
            return true;
        }

        if (start > end) {
            return true;
        }

        int rightStart = start, index = 0, root = post[end];
        boolean left = true, right = true;

        while (rightStart < end) {
            if (post[rightStart] >= root) {
                index = rightStart;
                while (index < end) {
                    if (post[index] < root) {
                        return false;
                    }
                    index ++;
                }
                // find the start of right subtree
                break;
            }
            rightStart ++;
        }

        if (rightStart > start) {
            left = verifyPostorder(post, start, rightStart-1);
        }

        if (rightStart < end) {
            right = verifyPostorder(post, rightStart, end - 1);
        }
        return left && right;
    }
}