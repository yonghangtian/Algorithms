package binarytree;

import java.util.*;

public class TraverseBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static int[] convertListToArray(List<Integer> list) {
        int[] array = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        return array;
    }

    public static void main(String[] args) {

        //initialize binary tree
        TraverseBinaryTree tree = new TraverseBinaryTree();
        TreeNode root = new TreeNode();

        //preorder result
        int[] preorder = new int[]{1, 2, 4, 7, 3, 5, 6, 8};

        //inorder result
        int[] inorder = new int[]{4, 7, 2, 1, 5, 3, 8, 6};

        //postorder result
        int[] postorder = new int[]{7, 4, 2, 5, 8, 6, 3, 1};

        root = tree.constructBinaryTreePreAndIn(preorder, inorder);

        System.out.println(root.val);

        //iterate way
        System.out.println("Iteration way");
        List<Integer> preorderList = tree.iteratePreorderBinaryTree(root);
        System.out.println("Preorder: " + Arrays.toString(convertListToArray(preorderList)));

        List<Integer> inorderList = tree.iterateInorderBinaryTree(root);
        System.out.println("Inorder: " + Arrays.toString(convertListToArray(inorderList)));

        List<Integer> postorderList = tree.iteratePostorderBinaryTree(root);
        System.out.println("Postorder: " + Arrays.toString(convertListToArray(postorderList)));

        // recursive way
        System.out.println("Recursion way");
        preorderList = tree.recursePreorderBinaryTree(root);
        System.out.println("Preorder: " + Arrays.toString(convertListToArray(preorderList)));

        inorderList = tree.recurseInorderBinaryTree(root);
        System.out.println("Inorder: " + Arrays.toString(convertListToArray(inorderList)));

        postorderList = tree.recursePostorderBinaryTree(root);
        System.out.println("Postorder: " + Arrays.toString(convertListToArray(postorderList)));
    }

    /**
     *  construct binary tree using preorder and inorder result.
     * @param preorder
     * @param inorder
     *
     * @return root
    */
    private TreeNode constructBinaryTreePreAndIn(int[] preorder, int[] inorder){
        //initial
        if (preorder == null || inorder == null || preorder.length != inorder.length){
            System.out.println("Invalid input");
            return null;
        }

        //construct
        TreeNode root = constructCorePreAndIn(preorder, 0, preorder.length - 1, inorder,
                    0, inorder.length - 1);

        return root;
    }

    /**
     *  recursive method to construct binary tree using preorder and inorder result.
     * @param preorder
     * @param preStart
     * @param preEnd
     * @param inorder
     * @param inStart
     * @param inEnd
     *
     *
     * @return root
     */
    private TreeNode constructCorePreAndIn(int[] preorder, int preStart, int preEnd,
                                           int[] inorder, int inStart, int inEnd) {
        // preorder: root, left, right
        // inorder: left, root, right
        TreeNode root = new TreeNode(preorder[preStart]);

        // return condition
        if (preStart == preEnd){
            if (inStart == inEnd && preorder[preStart] == inorder[inStart]){
                return root;
            } else {
                System.out.println("Invalid input");
                return null;
            }
        }


        if (preStart < preEnd) {
            int rootIndexInorder = this.findRootInorder(inorder, root.val);
            int leftTreeLen = rootIndexInorder - inStart;
            int rightTreeLen = inEnd - rootIndexInorder;

            // recursive to construct
            if (leftTreeLen > 0) {
                root.left = constructCorePreAndIn(preorder,preStart+1, preStart+leftTreeLen,
                        inorder,  inStart, rootIndexInorder-1);
            }

            if (rightTreeLen > 0) {
                root.right = constructCorePreAndIn(preorder,preStart+leftTreeLen+1, preEnd,
                        inorder, rootIndexInorder+1, inEnd);
            }
        }

        return root;
    }
    /**
     * @param inorder
     * @param val
     *
     * @return index of val in array
     */
    private int findRootInorder(int[] inorder, int val) {

        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }

        return -1;
    }

    /**
     *
     * @param postorder
     * @param inorder
     * @return root treenode
     */
    private TreeNode constructBinaryTreePostAndIn(int[] postorder, int[] inorder){
        return  null;
    }

    /**
     * Traverse binary tree in preorder using iteration
     *
     */
    private List<Integer> iteratePreorderBinaryTree(TreeNode root) {
        if (root == null) {
            return  null;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> list = new ArrayList<>();

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                list.add(root.val);
                root = root.left;
            }else {
                root = stack.pop();
                root = root.right;
            }
        }
        return list;
    }

    /**
     * Traverse binary tree in inorder using iteration
     */
    private List<Integer> iterateInorderBinaryTree(TreeNode root) {
        if (root == null) {
            return  null;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> list = new ArrayList<>();

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            }else {
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }
        }
        return list;
    }

    /**
     * Traverse binary tree in postorder using iteration
     *
     */
    private List<Integer> iteratePostorderBinaryTree(TreeNode root) {
        if (root == null) {
            return  null;
        }

        Deque<TreeNode> stack1 = new LinkedList<>();
        Deque<TreeNode> stack2 = new LinkedList<>();

        stack1.push(root);

        while (!stack1.isEmpty()) {
            TreeNode temp = stack1.pop();
            stack2.push(temp);

            if (temp.left != null) {
                stack1.push(temp.left);
            }

            if (temp.right != null) {
                stack1.push(temp.right);
            }
        }

        List<Integer> list = new ArrayList<>();
        while (!stack2.isEmpty()) {
            list.add(stack2.pop().val);
        }

        return list;
    }

    /**
     * Traverse binary tree in preorder using recursive
     */
    private List<Integer> recursePreorderBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> list = new ArrayList<>();

        recursePreorderBinaryTreeCore(root, list);

        return list;
    }

    private void recursePreorderBinaryTreeCore(TreeNode root, List<Integer> list) {
        if (root == null) {
            return ;
        }
        list.add(root.val);
        recursePreorderBinaryTreeCore(root.left, list);
        recursePreorderBinaryTreeCore(root.right, list);
    }

    /**
     * Traverse binary tree in inorder using recursive
     */
    private List<Integer> recurseInorderBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> list = new ArrayList<>();

        recurseInorderBinaryTreeCore(root, list);

        return list;
    }

    private void recurseInorderBinaryTreeCore(TreeNode root, List<Integer> list) {
        if (root == null) {
            return ;
        }
        recurseInorderBinaryTreeCore(root.left, list);
        list.add(root.val);
        recurseInorderBinaryTreeCore(root.right, list);
    }

    /**
     * Traverse binary tree in postorder using recursive
     */
    private List<Integer> recursePostorderBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> list = new ArrayList<>();

        recursePostorderBinaryTreeCore(root, list);

        return list;
    }

    private void recursePostorderBinaryTreeCore(TreeNode root, List<Integer> list) {
        if (root == null) {
            return ;
        }
        recursePostorderBinaryTreeCore(root.left, list);
        recursePostorderBinaryTreeCore(root.right, list);
        list.add(root.val);
    }
}
