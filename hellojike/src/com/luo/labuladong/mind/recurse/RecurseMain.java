package com.luo.labuladong.mind.recurse;

import static com.luo.util.CommonUtil.*;
/**
 * 递归系列
 */
@SuppressWarnings("Duplicates")
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

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     * 注意:
     * 你可以假设树中没有重复的元素。
     *
     * 前序遍历的特点在于根节点在于第一个元素,[根节点,左子节点...部分,右子节点...部分]
     * 中序遍历的特点在于                    [左子节点...部分,根节点,右子节点...部分]
     *
     * 根据这个特点构造
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder,0,preorder.length-1,
                inorder,0,inorder.length-1);
    }

    private TreeNode buildTree(int[] preorder,int pleft,int pright,
                               int[] inorder,int ileft,int iright){
        if(pleft>pright){
            return null;
        }
//        二叉树构造在于根节点构造,然后在构造子节点

//        前序遍历的根节点的角标
        int pIndex=pleft;
//        后续遍历的根节点的角标
        int iIndex=-1;
        for (int i = ileft; i <=iright ; i++) {
            if(inorder[i]==preorder[pIndex]){
                iIndex=i;
                break;
            }
        }
        int leftLen=iIndex-ileft;
//        将前序数组分成两部分
//        [pIndex,pIndex+1...pIndex+leftLen,pIndex+leftLen+1,pright]
//        将后续数组分成两部分
//        [iIndex...iIndex+leftLen-1,iIndex,iIndex+1...iright]
        TreeNode root=new TreeNode(preorder[pIndex]);
        TreeNode left=buildTree(preorder,pIndex+1,pleft+leftLen,
                inorder,ileft,ileft+leftLen-1);
        TreeNode right = buildTree(preorder, pleft+leftLen+1, pright,
                inorder, iIndex + 1, iright);
        root.left=left;
        root.right=right;
        return root;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     * 注意:
     * 你可以假设树中没有重复的元素。
     *
     * 后序遍历可以将数组拆分为 [左子链表部分,右子链表部分,根节点]
     * 中序遍历可以将数组拆分为 [左子链表部分,根节点,右子链表部分]
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        return buildTree2(inorder,0,inorder.length-1,
                postorder,0,postorder.length-1);
    }

    private TreeNode buildTree2(int[] inorder,int ileft,int iright,
                                int[] postorder,int pleft,int pright){
        if(ileft>iright||pleft>pright){
            return null;
        }
        int pIndex=pright;
        int iIndex=-1;
        for (int i = ileft; i <=iright ; i++) {
            if(postorder[pIndex]==inorder[i]){
                iIndex=i;
                break;
            }
        }

//        中序被分为 [ileft...iIndex-1,iIndex,iIndex+1...iright]
//        后序被分为 [pleft...pleft+leftLen-1,pleft+leftLen...pIndex-1,pIndex]

        int leftLen=iIndex-ileft;
        TreeNode root=new TreeNode(postorder[pIndex]);
        TreeNode left = buildTree2(inorder, ileft, iIndex-1,
                postorder, pleft, pleft + leftLen - 1);
        TreeNode right = buildTree2(inorder, iIndex+ 1, iright,
                postorder, pleft + leftLen, pIndex-1);
        root.left=left;
        root.right=right;
        return root;
    }

    public static void main(String[] args) {
        RecurseMain test=new RecurseMain();

//        int[] preorder = {3,9,20,15,7};
//        int[] inorder = {9,3,15,20,7};
//        TreeNode treeNode = test.buildTree(preorder, inorder);

        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        TreeNode treeNode = test.buildTree2(inorder, postorder);

    }


}
