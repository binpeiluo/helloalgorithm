package com.luo.labuladong.mind.recurse;

import static com.luo.util.CommonUtil.*;

public class BSTMain {

    /**
     * 230. 二叉搜索树中第K小的元素
     * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     *
     * 说明：
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        count=k;
        recurse(root);
        return result;
    }

    int count;
    Integer result;
    private void recurse(TreeNode root){
        if(root==null||result!=null){
            return;
        }
        recurse(root.left);
        count--;
        if(count==0){
            result=root.val;
        }
        recurse(root.right);
    }

    /**
     * 538. 把二叉搜索树转换为累加树
     * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
     * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
     * 提醒一下，二叉搜索树满足下列约束条件：
     *  节点的左子树仅包含键 小于 节点键的节点。
     *  节点的右子树仅包含键 大于 节点键的节点。
     *  左右子树也必须是二叉搜索树。
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        convertBSTAcc(root);
        return root;
    }

    int accSum;
    private void convertBSTAcc(TreeNode root){
        if(root==null){
            return;
        }
        convertBSTAcc(root.right);
        int origin=root.val;
        root.val+=accSum;
        accSum+=origin;
        convertBSTAcc(root.left);
    }
}
