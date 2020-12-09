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
}
