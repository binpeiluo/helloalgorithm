package com.luo.leetcode.bst;

import static com.luo.util.CommonUtil.*;
/**
 * 100. 相同的树
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * 示例 1:
 *
 * 输入:       1         1
 *           / \       / \
 *          2   3     2   3
 *
 *         [1,2,3],   [1,2,3]
 *
 * 输出: true
 * 示例 2:
 *
 * 输入:      1          1
 *           /           \
 *          2             2
 *
 *         [1,2],     [1,null,2]
 *
 * 输出: false
 * 示例 3:
 *
 * 输入:       1         1
 *           / \       / \
 *          2   1     1   2
 *
 *         [1,2,1],   [1,1,2]
 *
 * 输出: false
 *
 */
public class No100_isSameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null){
            return true;
        }
        if(p==null||q==null||p.val!=q.val){
            return false;
        }
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }

    public static void main(String[] args){
        No100_isSameTree test=new No100_isSameTree();
        Integer[] num1={1,2,3};
        Integer[] num2={1,3,3};
        TreeNode node1 = generateNode(num1);
        TreeNode node2 = generateNode(num2);

        boolean sameTree = test.isSameTree(node1, node2);
        System.out.println(sameTree);

    }
}
