package com.luo.labuladong.mind.recurse;

import static com.luo.util.CommonUtil.*;

public class BSTMain {

    /**
     * 230. 二叉搜索树中第K小的元素
     * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     *
     * 说明：
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        count=k;
        recurse(root);
        return result;
    }

    int count;
    Integer result;
    private void recurse(TreeNode root){
        if(root==null||result!=null){
            return;
        }
        recurse(root.left);
        count--;
        if(count==0){
            result=root.val;
        }
        recurse(root.right);
    }

    /**
     * 538. 把二叉搜索树转换为累加树
     * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
     * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
     * 提醒一下，二叉搜索树满足下列约束条件：
     *  节点的左子树仅包含键 小于 节点键的节点。
     *  节点的右子树仅包含键 大于 节点键的节点。
     *  左右子树也必须是二叉搜索树。
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        convertBSTAcc(root);
        return root;
    }

    int accSum;
    private void convertBSTAcc(TreeNode root){
        if(root==null){
            return;
        }
        convertBSTAcc(root.right);
        int origin=root.val;
        root.val+=accSum;
        accSum+=origin;
        convertBSTAcc(root.left);
    }

    /**
     * leetcode 98. 验证二叉搜索树
     * 明确每个节点应该做什么.需要判断左子节点,右子节点,当前节点的值关系
     * 但是仅仅是这样是不够的,有可能右子节点的左子节点比当前节点小
     *
     * 这时候就需要添加参数
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBst(root,null,null);
    }

    private boolean isValidBst(TreeNode root,TreeNode min,TreeNode max){
        if(root==null){
            return true;
        }
        boolean result;
        if(min==null&&max==null){
            result=true;
        }else if(min==null){
            result=root.val<max.val;
        }else if(max==null){
            result=root.val>min.val;
        }else{
            result=root.val<max.val&&root.val>min.val;
        }
        if(!result){
            return false;
        }
        result&=isValidBst(root.left,min,root);
        result&=isValidBst(root.right,root,max);
        return result;
    }

    /**
     * 700. 二叉搜索树中的搜索
     * 给定二叉搜索树（BST）的根节点和一个值。
     * 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。
     * 如果节点不存在，则返回 NULL。
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if(root==null){
            return null;
        }
        if(root.val==val){
            return root;
        }else if(root.val<val){
            return searchBST(root.right,val);
        }else{
            return searchBST(root.left,val);
        }
    }

    /**
     * 701. 二叉搜索树中的插入操作
     * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。
     * 返回插入后二叉搜索树的根节点。
     * 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
     *
     * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。
     * 你可以返回 任意有效的结果 。
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root==null){
            return new TreeNode(val);
        }
        if(root.val<val){
            root.right=insertIntoBST(root.right,val);
        }else{
            root.left=insertIntoBST(root.left,val);
        }
        return root;
    }

    /**
     * 450. 删除二叉搜索树中的节点
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，
     * 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     *
     * 一般来说，删除节点可分为两个步骤：
     * 首先找到需要删除的节点；
     * 如果找到了，删除它。
     * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
//        删除首先需要找到
        if(root==null){
            return null;
        }
        if(root.val==key){
//            已经找到
//            删除后需要维护BST有效性.
//            如果该节点没有子节点,那么可以直接删除
            if(root.left==null&&root.right==null){
                return null;
            }else if(root.left==null){
//                只有右子节点
                return root.right;
            }else if(root.right==null){
//                只有左子节点
                return root.left;
            }else{
//                这就麻烦了
//                可以在左子节点找到最大的或者在右子节点找到最小的
                TreeNode min = findMin(root.right);
//                这里没有做指针修改,为了方便直接修改值
//                如果是替换指针的话 就麻烦一些
                root.val=min.val;
//                删除节点
                root.right=deleteNode(root.right,min.val);
            }
        }else if(root.val<key){
//            在右边
            root.right=deleteNode(root.right,key);
        }else if(root.val>key){
//            在左边
            root.left=deleteNode(root.left,key);
        }
        return root;
    }

    private TreeNode findMin(TreeNode root){
        if(root==null){
            return null;
        }
        while(root.left!=null){
            root=root.left;
        }
        return root;
    }
}
