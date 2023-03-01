package com.luo.labuladong.dataconstruct;


import java.util.Stack;

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
        if(root.left!=null&&root.left.val>=root.val)
            return false;
        if(root.right!=null&&root.right.val<=root.val)
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
        if(min!=null&&root.val<=min.val)
            return false;
        if(max!=null&&root.val>=max.val)
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
        if(root.val>val)
            root.left=insertIntoBst(root.left,val);
        if(root.val<val)
            root.right=insertIntoBst(root.right,val);
        return root;
    }

    /**
     * 在bst删除一个节点
     * @param root
     * @param val
     * @return
     */
    public TreeNode deleteInBst(TreeNode root,int val){
        if(root==null)
            return null;
        if(root.val==val){
//            找到了该节点
            if(root.left==null)
                return root.right;
            if(root.right==null)
                return root.left;
//            左右子节点都不为空
//            在右子节点中找到最小的节点,该节点没有左子节点
//            这里只做了简单的替换,最好的实现是使用指针
            TreeNode min=minInBst(root.right);
            root.val=min.val;
            root.right=deleteInBst(root.right,min.val);
        }
        if(root.val<val){
            root.right=deleteInBst(root.right,val);
        }
        if(root.val>val){
            root.left=deleteInBst(root.left,val);
        }
        return root;
    }

    /**
     * 在bst中找到最小的节点
     * @param root
     * @return
     */
    public TreeNode minInBst(TreeNode root){
        while(root.left!=null)
            root=root.left;
        return root;
    }

    /**
     * 前序遍历 递归实现
     * @param root
     */
    public void preorderR(TreeNode root){
        if(root==null)
            return;
        System.out.print("--> "+root.val);
        preorderR(root.left);
        preorderR(root.right);
    }

    /**
     * 前序遍历 非递归实现
     * 每个节点,首先访问父节点,然后访问左子节点,再访问右子节点
     * 使用list无法实现,必须要借助stack结构
     * @param root
     */
    public void preorder(TreeNode root){
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
//        使用栈保存访问顺序,每次都对right先入栈,因为对于每个节点都是左子树先被访问
        while(!stack.isEmpty()){
            TreeNode pop = stack.pop();
            System.out.print("--> "+pop.val);
            if(pop.right!=null)
                stack.push(pop.right);
            if(pop.left!=null)
                stack.push(pop.left);
        }
        System.out.println();
    }

    /**
     * 中序遍历 递归实现
     * @param root
     */
    public void inorderR(TreeNode root){
        if(root==null)
            return ;
        inorderR(root.left);
        System.out.print("--> "+root.val);
        inorderR(root.right);
    }

    /**
     * 中序遍历 非递归实现
     * @param root
     */
    public void inorder(TreeNode root){
        Stack<TreeNode> stack=new Stack<>();
        TreeNode curr=root;
        while(curr!=null||!stack.isEmpty()){
//            使用栈保存访问顺序
            while(curr!=null){
                stack.push(curr);
                curr=curr.left;
            }
//            当节点没有左子节点时,则可以打印当前节点了,然后处理右子树
            TreeNode pop = stack.pop();
            System.out.print("--> "+pop.val);
            curr=pop.right;
        }
        System.out.println();
    }

    /**
     * 后序遍历 递归实现
     * @param root
     */
    public void postorderR(TreeNode root){
        if(root==null)
            return;
        postorderR(root.left);
        postorderR(root.right);
        System.out.print("--> " +root.val);
    }

    /**
     * 后序遍历 非递归实现
     * @param root
     */
    public void postorder(TreeNode root){
        Stack<TreeNode> stack=new Stack<>();
        TreeNode curr=root;
        TreeNode pre=null;
        while(curr!=null||!stack.isEmpty()){
//            使用栈保存访问顺序
            while(curr!=null){
                stack.push(curr);
                curr=curr.left;
            }
//            当没有左子节点时,获取栈顶.不能弹出,因为需要访问右子树后才能打印当前节点
            curr=stack.peek();
//            只有当节点没有右子节点或者右子节点已经被访问了,才弹出栈顶并打印
            if(curr.right==null||curr.right==pre){
                stack.pop();
                System.out.print("--> "+curr.val);
                pre=curr;
                curr=null;
            }else{
//                当存在右子节点且右子节点没有被访问时,先访问右子节点
                curr=curr.right;
            }
        }
        System.out.println();
    }


    public static void main(String[] args){
        BinaryTree test=new BinaryTree();
//        Integer[] nums={4,2,5,1,3,null,6};
        Integer[] nums1={4,2,5,1,3,4,6};
//        TreeNode treeNode = generateNode(nums);
        TreeNode treeNode1 = generateNode(nums1);
        System.out.println(treeNode1);
        test.plusOne(treeNode1);
        System.out.println(treeNode1);

        System.out.println("is the same="+test.isSameTree(treeNode1,treeNode1));

        boolean b = test.validBst(treeNode1);
        System.out.println("treeNode validBst="+b);

        boolean b1 = test.validBst2(treeNode1);
        System.out.println("treeNode validBst2="+b1);

        boolean inBst = test.isInBst(treeNode1, 3);
        System.out.println(inBst);

        TreeNode root = test.insertIntoBst(null, 4);
        test.insertIntoBst(root,2);
        test.insertIntoBst(root,5);
        test.insertIntoBst(root,1);
        test.insertIntoBst(root,3);
        test.insertIntoBst(root,6);
        test.insertIntoBst(root,0);
        test.insertIntoBst(root,10);
        System.out.println(root);

        test.deleteInBst(root,10);
        System.out.println(root);

        test.preorderR(root);
        System.out.println();
        test.preorder(root);

        test.inorderR(root);
        System.out.println();
        test.inorder(root);

        test.postorderR(root);
        System.out.println();
        test.postorder(root);

    }
}
