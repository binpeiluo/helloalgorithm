package com.luo.leetcode.bfs;

import static com.luo.util.CommonUtil.*;

/**
 * 783. 二叉搜索树节点最小距离
 *
 */
public class No783_minDiffInBST {

    /**
     * 很明显 搜索树中序遍历即大小关系排序
     * @param root
     * @return
     */
    public int minDiffInBST(TreeNode root) {
        inorder(root);
        return result;
    }

    TreeNode pre=null;
    int result=Integer.MAX_VALUE;

    private void inorder(TreeNode root){
        if(root==null){
            return;
        }
        inorder(root.left);
        if(pre==null){
            pre=root;
        }else{
            result=Math.min(result,root.val-pre.val);
            pre=root;
        }
        inorder(root.right);
    }


    public static void main(String[] args) {
        No783_minDiffInBST test=new No783_minDiffInBST();

        Integer[] ints={1,0,48,null,null,12,49};
        TreeNode root = generateNode(ints);

        int i = test.minDiffInBST(root);
        System.out.println(i);


    }
}
