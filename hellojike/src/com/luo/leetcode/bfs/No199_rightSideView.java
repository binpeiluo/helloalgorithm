package com.luo.leetcode.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.luo.util.CommonUtil.*;
/**
 * 199. 二叉树的右视图
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 */
public class No199_rightSideView {

    /**
     * 使用bfs实现
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result=new ArrayList<>();
        if(root==null)
            return result;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if(poll.left!=null)
                    queue.offer(poll.left);
                if(poll.right!=null)
                    queue.offer(poll.right);
                if(i==size-1)
                    result.add(poll.val);
            }
        }
        return result;
    }

    /**
     * 使用dfs方式实现
     * @param root
     * @return
     */
    public List<Integer> rightSideView2(TreeNode root){
        List<Integer> result=new ArrayList<>();
        if(root==null)
            return result;
        helper2(root,0,result);
        return result;
    }

    private void helper2(TreeNode root,int level,List<Integer> result){
        if(root==null)
            return;
        if(result.size()>level)
            result.remove(level);
        result.add(level,root.val);
        helper2(root.left,level+1,result);
        helper2(root.right,level+1,result);
    }

    public static void main(String[] args){
        No199_rightSideView test=new No199_rightSideView();
        Integer[] nums={1,2,3,null,5,null,4};
        TreeNode root = generateNode(nums);
        List<Integer> integers = test.rightSideView(root);
        System.out.println(integers);

        List<Integer> integers1 = test.rightSideView2(root);
        System.out.println(integers1);

    }

}
