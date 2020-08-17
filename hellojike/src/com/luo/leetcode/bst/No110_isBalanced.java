package com.luo.leetcode.bst;

import java.util.HashMap;
import java.util.Map;

import static com.luo.util.CommonUtil.*;

/**
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 */
public class No110_isBalanced {

    /**
     * naive方法,通过递归遍历二叉树判断左右子树高度之差
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        int leftH=helper(root.left);
        int rightH=helper(root.right);
        return Math.abs(leftH-rightH)<=1
                && isBalanced(root.left)
                && isBalanced(root.right);
    }

    private Map<TreeNode,Integer> cache=new HashMap<TreeNode,Integer>();
    private int helper(TreeNode node){
        if(cache.containsKey(node)){
            return cache.get(node);
        }
        int res=0;
        if(node==null){
            res=0;
        }else{
            res=1+Math.max(helper(node.left),helper(node.right));
        }
        cache.put(node,res);
        return res;
    }

    /**
     * 别人的实现
     * @param root
     * @return
     */
    public boolean isBalanced2(TreeNode root) {
        return height(root) >= 0;
    }

    private int height(TreeNode root) {
        if(root == null)
            return 0;
        int lh = height(root.left), rh = height(root.right);
        if(lh >= 0 && rh >= 0 && Math.abs(lh - rh) <= 1) {
            return Math.max(lh, rh) + 1;
        } else {
            return -1;
        }
    }

    public static void main(String[] args){
        No110_isBalanced test=new No110_isBalanced();
        Integer[] nums={3,9,20,null,null,15,7};
//        Integer[] nums={1,2,2,3,3,null,null,4,4};

        TreeNode root = generateNode(nums);
        boolean balanced = test.isBalanced(root);
        System.out.println(balanced);


    }
}
