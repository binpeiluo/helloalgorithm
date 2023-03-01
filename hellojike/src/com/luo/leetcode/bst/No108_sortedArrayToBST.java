package com.luo.leetcode.bst;

import static com.luo.util.CommonUtil.*;
/**
 * 108. 将有序数组转换为二叉搜索树
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 * 给定有序数组: [-10,-3,0,5,9],
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 */
public class No108_sortedArrayToBST {

    /**
     * 思路就是使用分治构建节点的左子树和右子树
     * 而且题目要求构建的二叉树需要高度平衡,那么需要使用中间元素作为根节点
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return divide(nums,0,nums.length-1);
    }

    private TreeNode divide(int[] nums,int start,int end){
        if(start>end){
            return null;
        }
        if(start==end){
            return new TreeNode(nums[start]);
        }
        int mid=start+(end-start)/2;
        TreeNode root=new TreeNode(nums[mid]);
        TreeNode left = divide(nums, start, mid - 1);
        TreeNode right = divide(nums, mid + 1, end);
        root.left=left;
        root.right=right;
        return root;
    }

    public static void main(String[] args){
        No108_sortedArrayToBST test=new No108_sortedArrayToBST();
        int[] nums={-10,-3,0,5,9};

        TreeNode treeNode = test.sortedArrayToBST(nums);
        System.out.println(treeNode);
    }
}
