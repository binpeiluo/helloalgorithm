package com.luo.leetcode.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.luo.util.CommonUtil.*;
/**
 * 面试题 17.12. BiNode
 * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。
 * 实现一个方法，把二叉搜索树转换为单向链表，要求值的顺序保持不变，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
 */
public class No1712_convertBiNode {
    /**
     * 朴素的实现,使用O(n)空间储存节点,然后修改其指针指向
     * 但是提交超时
     * @param root
     * @return
     */
    public TreeNode convertBiNode(TreeNode root) {
        List<TreeNode> list=new ArrayList<>();
        orderList(root,list);
        if(list.size()<2)
            return root;
        for (int i = 1; i < list.size(); i++) {
            list.get(i-1).left=null;
            list.get(i-1).right=list.get(i);
        }
        return list.get(0);
    }

    private void orderList(TreeNode root, List<TreeNode> list) {
        if(root==null)
            return ;
        orderList(root.left,list);
        list.add(root);
        orderList(root.right,list);
    }

    /**
     * 利用中序遍历的有序性,非递归实现
     * @param root
     * @return
     */
    public TreeNode convertBiNode2(TreeNode root) {
        TreeNode tmp=new TreeNode(0);
        tmp.right=root;
        Stack<TreeNode> stack=new Stack<>();
        TreeNode pre=tmp;
        TreeNode curr=root;
        while(curr!=null||!stack.isEmpty()){
            while(curr!=null){
                stack.push(curr);
                curr=curr.left;
            }
            curr=stack.pop();
            pre.right=curr;
//            为什么可以直接置空?因为之前已经访问过了
            curr.left=null;
            pre=curr;
            curr=curr.right;
        }
        return tmp.right;
    }

    /**
     * 递归实现
     * @param root
     * @return
     */
    public TreeNode convertBiNode3(TreeNode root){
        TreeNode tmp=new TreeNode(0);
        tmp.right=root;
        helper3(tmp,root);
        return tmp.right;
    }

    /**
     * 功能是将
     * @param pre
     * @param curr
     * @return
     */
    private TreeNode helper3(TreeNode pre,TreeNode curr){
        if(curr!=null){
            pre=helper3(pre,curr.left);
            curr.left=null;
            pre.right=curr;
            pre=curr;
            pre=helper3(pre,curr.right);
        }
        return pre;

    }

    public static void main(String[] args){
        No1712_convertBiNode test=new No1712_convertBiNode();
        Integer[] nums={4,2,6,1,3,5,8};
        TreeNode treeNode = generateNode(nums);
//        TreeNode root = test.convertBiNode(treeNode);
//        System.out.println(root);

//        TreeNode root = test.convertBiNode2(treeNode);
//        System.out.println(root);

        TreeNode root = test.convertBiNode3(treeNode);
        System.out.println(root);
    }

}
