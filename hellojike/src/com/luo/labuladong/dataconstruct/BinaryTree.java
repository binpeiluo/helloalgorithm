package com.luo.labuladong.dataconstruct;


import static com.luo.util.CommonUtil.*;

/**
 * 二叉树操作集锦
 */
public class BinaryTree {

    /**
     * 二叉树遍历框架
     * @param root
     */
    public void traverse(TreeNode root){
        traverse(root.left);
        traverse(root.right);
    }

    /**
     * 给二叉树所有节点+1
     * @param root
     */
    public void plusOne(TreeNode root){
        if(root==null)
            return;
        root.val++;
        plusOne(root.left);
        plusOne(root.right);
    }

    /**
     * 判断两颗二叉树是否完全相同
     * @param t1
     * @param t2
     * @return
     */
    public boolean isSameTree(TreeNode t1,TreeNode t2){
        if(t1==null&&t2==null)
            return true;
        if(t1==null||t2==null)
            return false;
        boolean currSame=t1.val==t2.val;
        return currSame
                &&isSameTree(t1.left,t2.left)
                &&isSameTree(t1.right,t2.right);
    }

    /**
     * 判断是否是二叉搜索树
     * 这种实现时不正确的,比如
     *          2
     *       1      3
     *            0   4
     *
     * @param root
     * @return
     */
    public boolean validBst(TreeNode root){
        if(root==null)
            return true;
        if(root.left!=null&&root.left.val>root.val)
            return false;
        if(root.right!=null&&root.right.val<root.val)
            return false;
        return validBst(root.left)&&validBst(root.right);
    }

    /**
     * 校验是否为二叉搜索树
     * @param root
     * @return
     */
    public boolean validBst2(TreeNode root){
        return validBst2Helper(root,null,null);
    }

    private boolean validBst2Helper(TreeNode root,TreeNode min,TreeNode max){
        if(root==null)
            return true;
        if(min!=null&&root.val<min.val)
            return false;
        if(max!=null&&root.val>max.val)
            return false;
        return validBst2Helper(root.left,min,root)
                &&validBst2Helper(root.right,root,max);

    }

    /**
     * 在bst查找一个数存不存在
     * @return
     */
    public boolean isInBst(TreeNode root,int num){
        if(root==null)
            return false;
        if(root.val==num)
            return true;
        if(root.val<num)
            return isInBst(root.right,num);
        else
            return isInBst(root.left,num);
    }

    /**
     * 改进后二叉树遍历框架
     * @param root
     * @param num
     */
    public void bst(TreeNode root,int num){
        if(root.val==num){
//            执行操作
        }else if(root.val<num){
            bst(root.right,num);
        }else if(root.val>num){
            bst(root.left,num);
        }

    }

    /**
     * 往一颗bst插入一个节点
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBst(TreeNode root,int val){
        if(root==null)
            return new TreeNode(val);
        return root;
    }
}
