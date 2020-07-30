package binarytree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author tian
 */
public class SerializeBinaryTree {
    public static void main(String[] args) {
        // preorder traverse
        String preorder = "1 2 4 $ $ $ 3 5 $ $ 6 $ $ ";

        System.out.println("Is preorder serialize & deserialize correct? "
                + preorder.equals(serializeUsePreorder(deserializePreorder(preorder))));

        // leverorder traverse
        String levelorder = "1 2 3 $ $ 4 5 $ $ $ $ ";
        System.out.println("Is preorder serialize & deserialize correct? "
                + levelorder.equals(serializeUseLevelorder(deserializeLevelorder(levelorder))));
    }

    /**
     * serialize binary tree by each level.
     * @param node
     * @return
     */
    public static String serializeUseLevelorder(TreeNode node) {
        StringBuilder sb = new StringBuilder();

        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        serializeUseLevelorder(sb, queue);

        return sb.toString();
    }

    /**
     * do the serialize binary tree by each level.
     */
    private static void serializeUseLevelorder(StringBuilder sb, Deque<TreeNode> queue) {
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node != null) {
                sb.append(node.val + " ");
                queue.add(node.left);
                queue.add(node.right);
            } else {
                sb.append("$ ");
            }
        }
    }

    /**
     * deserialize level order reserve of binary tree.
     * @param data
     * @return
     */
    public static TreeNode deserializeLevelorder(String data) {
        if (data == ""){
            return null;
        }

        TreeNode node = null;
        Deque<TreeNode> queue = new LinkedList<>();

        String[] vals = data.split(" ");
        int i = 0;
        if (vals[i].equals("$")){return null;}
        node = new TreeNode(Integer.parseInt(vals[i]));
        queue.add(node);
        i++;

        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();

            if (!vals[i].equals("$")) {
                temp.left = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(temp.left);
            }
            i++;

            if (!vals[i].equals("$")) {
                temp.right = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(temp.right);
            }
            i++;
        }

        return node;
    }

    /**
     *
     * @param node
     * @return string, which contains serialized preorder traverse
     */
    public static String serializeUsePreorder(TreeNode node) {
        StringBuilder sb = new StringBuilder();

        serializeUsePreorder(node, sb);

        return sb.toString();
    }

    /**
     * do the preorder traverse.
     * @param node
     * @param sb
     */
    private static void serializeUsePreorder(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("$ ");
            return;
        } else {
            sb.append(node.val + " ");
        }

        serializeUsePreorder(node.left, sb);
        serializeUsePreorder(node.right, sb);
    }

    /**
     * deserialize the preorder traverse string.
     * @param data, a string that contains preorder traverse of a binary tree.
     * @return the root node of a binary tree.
     */
    public static TreeNode deserializePreorder(String data) {
        TreeNode node = null;

        Scanner sc = new Scanner(data);

        node = deserializePreorder(sc, node);

        return node;
    }

    /**
     * do the deserialize the preorder traverse string.
     * @param sc
     * @param node
     */
    private static TreeNode deserializePreorder(Scanner sc, TreeNode node) {
        if (sc.hasNext()){
            if (sc.hasNextInt()) {
                node = new TreeNode(sc.nextInt());

                node.left = deserializePreorder(sc, node.left);
                node.right = deserializePreorder(sc, node.right);

                return node;
            } else {
                sc.next();
            }
        }

        return null;
    }
}
