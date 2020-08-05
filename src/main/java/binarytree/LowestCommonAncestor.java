package binarytree;

import java.util.Deque;
import java.util.LinkedList;

public class LowestCommonAncestor {

    public static void main(String[] args) {

    }

    public TreeNode lowestCommonAncestorReverse(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null && right == null) {
            return null;
        }

        if (left == null) {return right;}
        if (right == null) {return left;}

        return root;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        Deque<TreeNode> pathp = new LinkedList<>();
        Deque<TreeNode> pathq = new LinkedList<>();

        TreeNode node = root;

        // find path for p
        findPath(node, p.val, pathp);

        //find path for q
        node = root;
        findPath(node, q.val, pathq);

        while (pathp.size() > pathq.size()) {
            pathp.pollFirst();
        }

        while (pathp.size() < pathq.size()) {
            pathq.pollFirst();
        }

        while (!pathp.isEmpty() && !pathq.isEmpty()) {
            if (pathp.peek().val == pathq.peek().val){
                return pathp.pollFirst();
            }

            pathp.pollFirst();
            pathq.pollFirst();
        }

        return null;
    }

    public boolean findPath(TreeNode node, int value, Deque<TreeNode> path) {
        if (node == null) {
            return false;
        }

        boolean result = false;
        path.offerFirst(node);
        if (node.val == value) {
            return true;
        }

        if (node.left != null) {
            result = findPath(node.left, value, path);
        }

        if (!result && node.right != null) {
            result = findPath(node.right, value, path);
        }

        if (!result){
            path.pollFirst();
        }

        return result;
    }
}
