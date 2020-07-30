package binarytree;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

 /*
 *   Definition for a binary tree node.
 */

public class SumOfPath {


    public static void main(String[] args) {

    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        /*
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1

        sum = 22
        */

        if (root == null) {
            return new ArrayList<>();
        }

        Deque<TreeNode> stack = new LinkedList<>();
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        findPath(root, stack, sum, path, paths);

        return paths;
    }

    private void findPath(TreeNode node, Deque<TreeNode> stack, int sum, List<Integer> path,
                      List<List<Integer>> paths) {

        stack.push(node);
        sum -= node.val;
        path.add(node.val);

        boolean leaf = (node.left == null) && (node.right == null);

        if (sum ==0 && leaf) {
            //这里不能直接把path放进去，因为现在这个path会不断被修改，遍历到最后，path是空的，所以我们要放一个path的备份进去。
            paths.add(new ArrayList<Integer>(path));
        }

        if (node.left != null) {
            findPath(node.left, stack, sum, path, paths);
        }

        if (node.right != null) {
            findPath(node.right, stack, sum, path, paths);
        }

        path.remove(path.size() - 1);
        stack.pop();
    }
}