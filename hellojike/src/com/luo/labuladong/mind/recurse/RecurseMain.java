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




}
