package com.luo.leetcode.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.luo.util.CommonUtil.*;
/**
 * 107. 二叉树的层次遍历 II
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 */
public class No107_levelOrderBottom {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result=new LinkedList<>();
        Queue<TreeNode> queue=new LinkedList();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            List<Integer> list=new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if(poll.left!=null)
                    queue.offer(poll.left);
                if(poll.right!=null)
                    queue.offer(poll.right);
            }
            ((LinkedList<List<Integer>>) result).offerFirst(list);
        }
        return result;
    }

    /**
     * 这种方式不正确的原因在于 helper2方法中 result.get(0).add(node.val); 当
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        helper2(root,0);
        return result;
    }
    List<List<Integer>> result=new LinkedList<>();
    private void helper2(TreeNode node,int level){
        if(node==null)
            return ;
        if(level==result.size())
            ((LinkedList)result).offerFirst(new ArrayList<>());
//        因为该helper方法类似与前序遍历,按照顺序会触底,所以result.get(0)获取的当前层级在result中的位置不正确,需要调整下
//        result.get(0).add(node.val);
        result.get(result.size()-1-level).add(node.val);
        if(node.left!=null)
            helper2(node.left,level+1);
        if(node.right!=null)
            helper2(node.right,level+1);

    }



    public static void main(String[] args){
        No107_levelOrderBottom test=new No107_levelOrderBottom();
        Integer[] nums={1,2,3,4,5};
        TreeNode root = generateNode(nums);
        List<List<Integer>> lists = test.levelOrderBottom(root);
        System.out.println(lists);

        List<List<Integer>> lists1 = test.levelOrderBottom2(root);
        System.out.println(lists1);
    }
}
