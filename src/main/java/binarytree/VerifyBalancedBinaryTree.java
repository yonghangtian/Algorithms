package binarytree;

public class VerifyBalancedBinaryTree {
    public static void main(String[] args) {
        String tree = "1 2 2 3 3 $ $ 4 4 $ $ $ $ $ $";

        TreeNode root = SerializeBinaryTree.deserializeLevelorder(tree);

        VerifyBalancedBinaryTree balanced = new VerifyBalancedBinaryTree();

        System.out.println(balanced.isBalanced(root));

    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (isBalanced(root.left) && isBalanced(root.right)) {
            int left = depth(root.left);
            int right = depth(root.right);

            if (Math.abs(left - right) <= 1){
                return true;
            }
        }

        return false;
    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = depth(root.left);
        int right = depth(root.right);

        return left > right ? ++left : ++ right;
    }
}
