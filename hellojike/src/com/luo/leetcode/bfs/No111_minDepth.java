package com.luo.leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

import static com.luo.util.CommonUtil.*;
/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明: 叶子节点是指没有子节点的节点。
 */
public class No111_minDepth {

    /**
     * 思路:
     *  使用bfs在层遍历过程中,当发现其节点的左子节点或者右子节点为空时就找到了最小深度
     *
     *  当树是一颗链表时,根节点不算叶子节点???
     *  题目已经进行了说明 说明: 叶子节点是指没有子节点的节点。
     * @param root
     * @return
     */
    public int minDepth(TreeNode root){
        if(root==null)
            return 0;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        int res=0;
        while(!queue.isEmpty()){
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if(poll.left==null&&poll.right==null)
                    return res;
                if(poll.left!=null)
                    queue.offer(poll.left);
                if(poll.right!=null)
                    queue.offer(poll.right);
            }
        }
        return res;
    }

    /**
     * 这种方式会将根节点计算为叶子节点
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root){
        if(root==null)
            return 0;
        return helper2(root,1);
    }

    /**
     * 由于题中是求没有子节点的节点的深度,在递归中判断当节点没有子节点时即可以返回
     * 同时还需要处理只有一个子节点的问题
     * @param node
     * @param level
     * @return
     */
    private int helper2(TreeNode node,int level){
        if(node.left==null&&node.right==null)
            return level;
        if(node.left==null)
            return helper2(node.right,level+1);
        else if(node.right==null)
            return helper2(node.left,level+1);
        else
            return Math.min(helper2(node.left,level+1),helper2(node.right,level+1));
    }


    public static void main(String[] args){
        No111_minDepth test=new No111_minDepth();
        Integer[] nums={3,9,20,null,null,15,7};
//        Integer[] nums={1,2};
        TreeNode root = generateNode(nums);
        int i = test.minDepth(root);
        System.out.println(i);

        int i1 = test.minDepth2(root);
        System.out.println(i1);
    }
}
