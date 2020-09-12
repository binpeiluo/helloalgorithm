package com.luo.leetcode.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.luo.util.CommonUtil.*;
/**
 * 637. 二叉树的层平均值
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 *
 * 示例 1：
 * 输入：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出：[3, 14.5, 11]
 * 解释：
 * 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
 *
 * 提示：
 * 节点值的范围在32位有符号整数范围内。
 *
 */
@SuppressWarnings("Duplicates")
public class No637_averageOfLevels {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result=new ArrayList<>();
        if(root==null){
            return result;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            double sum=0;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                sum+=poll.val;
                if(poll.left!=null){
                    queue.offer(poll.left);
                }
                if(poll.right!=null){
                    queue.offer(poll.right);
                }
            }
            double avg=sum/size;
            result.add(avg);
        }
        return result;
    }

    public static void main(String[] args) {
        No637_averageOfLevels test=new No637_averageOfLevels();
//        Integer[] nums={3,9,20,null,null,15,7};
        Integer[] nums={2147483647,2147483647,2147483647};

        TreeNode treeNode = generateNode(nums);
        List<Double> doubles = test.averageOfLevels(treeNode);
        System.out.println(doubles);
    }
}
