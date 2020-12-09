package com.luo.labuladong.mind.recurse;

import static com.luo.util.CommonUtil.*;
/**
 * 递归系列
 */
public class RecurseMain {

    /**
     * leetcode 226 反转二叉树
     * 可以前序遍历
     * 可以后序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root==null){
            return root;
        }
        TreeNode tmp=root.right;
        root.right=root.left;
        root.left=tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };


    /**
     * leetcode 116
     * 填充二叉树右侧指针
     *
     * 当一个参数无法完成时，可以考虑使用多个参数
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if(root==null){
            return root;
        }
//        这样不会填充不同父节点的两个相邻节点，可以通过添加参数完成
//        root.left.next=root.right;
//        connect(root.left);
//        connect(root.right);

        connectTwoNode(root.left,root.right);
        return root;
    }

    private void connectTwoNode(Node left,Node right){
        if(left==null||right==null){
            return ;
        }
        left.next=right;
        connectTwoNode(left.left,left.right);
        connectTwoNode(right.left,right.right);
        connectTwoNode(left.right,right.left);
    }

    /**
     * leetcode 114. 二叉树展开为链表
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        flattenNode(root);
    }

    private TreeNode flattenNode(TreeNode root){
        if(root==null){
            return null;
        }
        TreeNode left=flattenNode(root.left);
        TreeNode right=flattenNode(root.right);

//        如果左子链表不为空则需要找到链表尾部然后指向右子链表
//        如果左子链表为空则可以不处理,因为右子链表已经处理了
        if(left!=null){
            root.left=null;
            root.right=left;
//            找到左子链表尾节点,方便将右子链表置于尾部
            TreeNode tail=left;
            while(tail.right!=null){
                tail=tail.right;
            }
            tail.right=right;
        }
        return root;
    }

    /**
     * 654. 最大二叉树
     * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
     * 二叉树的根是数组中的最大元素。
     * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
     * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
     * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
     *
     * 构造二叉树首先在于构造本节点然后在构造子节点
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildMaxBinaryTree(nums,0,nums.length-1);
    }

    private TreeNode buildMaxBinaryTree(int[] nums,int left,int right){
        if(left>right){
            return null;
        }
//        首先构造根节点,然后在构造子节点
        int maxIndex=left;
        for (int i = left; i <=right; i++) {
            maxIndex=nums[i]>nums[maxIndex]?i:maxIndex;
        }
        TreeNode root=new TreeNode(nums[maxIndex]);
        TreeNode rootLeft=buildMaxBinaryTree(nums,left,maxIndex-1);
        TreeNode rootRight=buildMaxBinaryTree(nums,maxIndex+1,right);
        root.left=rootLeft;
        root.right=rootRight;
        return root;
    }




}
