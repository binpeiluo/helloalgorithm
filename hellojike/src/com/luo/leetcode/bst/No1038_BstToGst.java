package com.luo.leetcode.bst;


import static com.luo.util.CommonUtil.*;

/**
 * 二叉搜索树系列
 * 1038. 从二叉搜索树到更大和树
 * 给出二叉 搜索 树的根节点，该二叉树的节点值各不相同，修改二叉树，使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 *
 */
public class No1038_BstToGst {

    int sum=0;
    /**
     * 可以利用二叉树的性质,节点左子树的值都小于父节点的值.节点右子树的值都大于父节点的值
     * @param root
     * @return
     */
    public TreeNode bstToGst(TreeNode root) {
        if(root==null)
            return null;
        if(root.right!=null)
            bstToGst(root.right);
        root.val+=sum;
        sum=root.val;
        if(root.left!=null)
            bstToGst(root.left);
        return root;
    }
}
