package com.luo.leetcode.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.luo.util.CommonUtil.*;
/**
 * 103. 二叉树的锯齿形层次遍历
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）
 */
public class No103_zigzagLevelOrder {

    /**
     * bfs
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result=new ArrayList<>();
        if(root==null)
            return result;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        boolean direct=true;
        while(!queue.isEmpty()){
            int size = queue.size();
            direct=!direct;
            List<Integer> list=new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if(poll.left!=null)
                    queue.offer(poll.left);
                if(poll.right!=null)
                    queue.offer(poll.right);
                if(direct){
                    ((LinkedList<Integer>) list).offerFirst(poll.val);
                }else{
                    ((LinkedList<Integer>) list).offerLast(poll.val);
                }
            }
            result.add(list);
        }
        return result;
    }

    /**
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        helper2(root,0);
        return result;
    }

    List<List<Integer>> result=new ArrayList<>();
    private void helper2(TreeNode node ,int level){
        if(level==result.size())
            result.add(new LinkedList<>());
        if(node==null)
            return;
        if(level%2==0){
            ((LinkedList)result.get(level)).offerLast(node.val);
        }else{
            ((LinkedList)result.get(level)).offerFirst(node.val);
        }
        if(node.left!=null)
            helper2(node.left,level+1);
        if(node.right!=null)
            helper2(node.right,level+1);
    }

    public static void main(String[] args){
        No103_zigzagLevelOrder test=new No103_zigzagLevelOrder();
        Integer[] nums={3,9,20,null,null,15,7};
        TreeNode root = generateNode(nums);
        List<List<Integer>> lists = test.zigzagLevelOrder(root);
        System.out.println(lists);

        List<List<Integer>> lists1 = test.zigzagLevelOrder2(root);
        System.out.println(lists1);
    }
}
