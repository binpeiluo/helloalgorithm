package com.luo.leetcode.divide;

import static com.luo.util.CommonUtil.*;
/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 */
public class No104_maxDepth {

    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }

    public static void main(String[] args){
        No104_maxDepth test=new No104_maxDepth();
        Integer[] nums={3,9,20,null,null,15,7};
        TreeNode treeNode = generateNode(nums);

        int i = test.maxDepth(treeNode);
        System.out.println(i);
    }
}
