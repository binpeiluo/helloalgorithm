package com.luo.leetcode.divide;

import static com.luo.util.CommonUtil.*;
/**
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class No98_isValidBST {

    public boolean isValidBST(TreeNode root) {
        if(root==null)
            return true;
        return helper(root,null,null);
    }

    private boolean helper(TreeNode node,TreeNode min,TreeNode max){
        if(min!=null&&node.val<=min.val)
            return false;
        if(max!=null&&node.val>=max.val)
            return false;
        boolean leftValid=true;
        if(node.left!=null)
            leftValid=helper(node.left,min,node);
        if(!leftValid)
            return false;
        boolean rightValid=true;
        if(node.right!=null)
            rightValid=helper(node.right,node,max);
        return rightValid;
    }

    public static void main(String[] args){
        No98_isValidBST test=new No98_isValidBST();
        Integer[] nums={5,1,4,null,null,3,6};
        TreeNode treeNode = generateNode(nums);
        boolean validBST = test.isValidBST(treeNode);
        System.out.println(validBST);
    }
}
