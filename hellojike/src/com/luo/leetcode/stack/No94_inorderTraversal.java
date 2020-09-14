package com.luo.leetcode.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.luo.util.CommonUtil.*;
/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class No94_inorderTraversal {

    /**
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result=new ArrayList<>();
        helper(result,root);
        return result;
    }

    private void helper(List<Integer> result,TreeNode node){
        if(node==null){
            return ;
        }
        helper(result,node.left);
        result.add(node.val);
        helper(result,node.right);
    }

    /**
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result=new ArrayList<>();
        if(root==null){
            return result;
        }
        Stack<TreeNode> stack=new Stack<>();
        TreeNode curr=root;
        while(true){
//            跳出循环
            if(curr==null&&stack.isEmpty()){
                break;
            }
//            找到最左子节点
            while(curr!=null){
                stack.push(curr);
                curr=curr.left;
            }
            TreeNode pop = stack.pop();
            result.add(pop.val);

            curr=pop.right;
//            其实并不需要这么判断 直接赋值 curr=pop.right;
//            if(pop.right!=null){
//                curr=pop.right;
//            }else{
////                会再次进入while循环
//                curr=null;
//            }
        }
        return result;
    }

    public static void main(String[] args) {
        No94_inorderTraversal test=new No94_inorderTraversal();
//        Integer[] nums={1,null,2,null,null,null,3};
        Integer[] nums={1,2,3,4,5,null,null,null,null,6,7};
        TreeNode root = generateNode(nums);
        System.out.println(root);

        List<Integer> integers = test.inorderTraversal(root);
        System.out.println(integers);

        List<Integer> integers1 = test.inorderTraversal2(root);
        System.out.println(integers1);
    }
}
