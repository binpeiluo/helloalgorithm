package com.luo.leetcode.bst;

import java.util.ArrayList;
import java.util.List;

import static com.luo.util.CommonUtil.*;
/**
 * 二叉搜索树系列
 * 1382. 将二叉搜索树变平衡
 * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。
 * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。
 * 如果有多种构造方法，请你返回任意一种。
 */
public class No1382_balanceBST {

    /**
     * 思路:
     *      假如不想手撕AVL树的话就需要取巧.
     *      利用二叉搜索树的性质,将节点放置一个数组中,然后在递归构造一颗新的平衡二叉树
     *
     * 时间复杂度:   O(nlogn)    使用递归
     * 空间复杂度:   O(n)
     *
     * @param root
     * @return
     */
    public TreeNode balanceBST(TreeNode root) {
        ArrayList<TreeNode> list=new ArrayList<>();
        orderTreeNode(root,list);
        return buildBst(null,list,0,list.size()-1);
    }

    private void orderTreeNode(TreeNode root,List<TreeNode> list){
        if(root==null)
            return;
        orderTreeNode(root.left,list);
        list.add(root);
        orderTreeNode(root.right,list);
    }

    private TreeNode buildBst(TreeNode curr,List<TreeNode> list,int start,int end){
        if(start>end)
            return null;
        int mid=start+(end-start)/2;
        if(curr==null){
            curr=new TreeNode(list.get(mid).val);
        }

        curr.left=buildBst(curr.left,list,start,mid-1);
        curr.right=buildBst(curr.right,list,mid+1,end);

        return curr;
    }

    public static void main(String[] args){
        No1382_balanceBST test=new No1382_balanceBST();
        Integer[] nums={1,null,2,null,null,null,3,null,null,null,null,null,null,null,4};
        TreeNode treeNode = generateNode(nums);
        System.out.println(treeNode);
        TreeNode treeNode1 = test.balanceBST(treeNode);
        System.out.println(treeNode1);
    }
}
