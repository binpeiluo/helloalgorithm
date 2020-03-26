package com.luo.leetcode.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.luo.util.CommonUtil.*;
/**
 * 102. 二叉树的层次遍历
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 */
public class No102_levelOrder {

    /**
     * 使用bfs实现
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result=new ArrayList<>();
        if(root==null)
            return result;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list=new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if(poll.left!=null)
                    queue.offer(poll.left);
                if(poll.right!=null)
                    queue.offer(poll.right);
            }
            result.add(list);
        }
        return result;
    }

    List<List<Integer>> result=new ArrayList<>();
    /**
     * 使用递归实现
     * 思路:
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        helper2(root,0);
        return result;
    }

    private void helper2(TreeNode t,int level){
        if(result.size()==level)
            result.add(new ArrayList<>());
        result.get(level).add(t.val);
        if(t.left!=null)
            helper2(t.left,level+1);
        if(t.right!=null)
            helper2(t.right,level+1);
    }

    public static void main(String[] args){
        No102_levelOrder test=new No102_levelOrder();
        Integer[] nums={3,9,20,null,null,15,7};
        TreeNode treeNode = generateNode(nums);
        List<List<Integer>> lists = test.levelOrder(treeNode);
        System.out.println(lists);

        List<List<Integer>> lists1 = test.levelOrder2(treeNode);
        System.out.println(lists1);

    }
}
