package com.luo.leetcode.bfs;

import java.util.*;

import static com.luo.util.CommonUtil.*;
/**
 * 101. 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 */
public class No101_isSymmetric {

    /**
     * 递归算法
     * 分析:
     *      假如一颗树额左子树和右子树是对称的.那么该树就是对称的.
     *      那么如何判断左子树和右子树互为镜像呢
     *
     *      当满足:
     *          两颗子树的根节点值相同,
     *          每颗树的右子树和左子树相同
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return helper(root,root);
    }

    private boolean helper(TreeNode t1,TreeNode t2){
        if(t1==null&&t2==null)
            return true;
        if(t1==null||t2==null)
            return false;
        return t1.val==t2.val
                &&helper(t1.left,t2.right)
                &&helper(t1.right,t2.left);
    }

    public boolean isSymmetric2(TreeNode root){
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if(t1==null&&t2==null)
                continue;
            if(t1==null||t2==null)
                return false;
            if(t1.val!=t2.val)
                return false;
            queue.offer(t1.left);
            queue.offer(t2.right);
            queue.offer(t1.right);
            queue.offer(t2.left);
        }
        return true;
    }


    public static void main(String[] args){
        No101_isSymmetric test=new No101_isSymmetric();
//        Integer[] nums={1,2,2,3,4,4,3};
        Integer[] nums={1,2,2,null,3,null,3};
//        Integer[] nums={1,2,2,3,3,4,4,3,2,2,3,4,2,2,4};
        TreeNode root = generateNode(nums);
        boolean symmetric = test.isSymmetric(root);
        System.out.println(symmetric);

        boolean symmetric2 = test.isSymmetric2(root);
        System.out.println(symmetric2);
    }
}
