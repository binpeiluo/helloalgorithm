package com.luo.leetcode.bst;

import java.util.ArrayList;
import java.util.List;

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
        return root;
    }


}
