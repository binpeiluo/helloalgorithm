package com.luo.leetcode.bst;

import com.luo.util.CommonUtil;

import static com.luo.util.CommonUtil.*;
/**
 * 114. 二叉树展开为链表
 * 给定一个二叉树，原地将它展开为一个单链表。
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 */
public class No114_flatten {

    /**
     * 自己想的果然有点丑陋
     * @param root
     */
    public void flatten(TreeNode root) {
        recurse(root);
    }

    /**
     * 定义 recurse 函数作用.
     * 传入一个二叉树节点,将该节点链表化.
     * 逻辑不够优美
     * @param node
     * @return
     */
    private TreeNode[] recurse(TreeNode node){
        if(node==null){
            return new TreeNode[]{node,node};
        }
        if(node.left==null){
            TreeNode[] recurseRight = recurse(node.right);
            return new TreeNode[]{node,recurseRight[1]==null?node:recurseRight[1]};
        }
        TreeNode[] recurseLeft = recurse(node.left);
        node.left=null;
        TreeNode right = node.right;
        node.right=recurseLeft[0];
        recurseLeft[1].right=right;
        TreeNode[] recurseRight = recurse(right);
        return new TreeNode[]{node,recurseRight[1]==null?recurseLeft[1]:recurseRight[1]};
    }

    /**
     * leetcode思路
     *  naive想法是使用前序遍历得到顺序的访问元素,然后修改指针
     *
     *  有点难以理解
     *
     * @param root
     */
    public void flatten2(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }

    public static void main(String[] args){
        No114_flatten test=new No114_flatten();
//        Integer[] nums={1,2,5,3,4,6};
//        Integer[] nums={1,2};
//        Integer[] nums={1,2,null,3};
//        Integer[] nums={1,null,2,3};
        Integer[] nums={1,null,2,null,null,3};
        TreeNode root = generateNode(nums);
        CommonUtil.printTreeNode(root);
        test.flatten(root);
        CommonUtil.printTreeNode(root);
    }
}
