package com.luo.leetcode.bst;

import static com.luo.util.CommonUtil.*;

/**
 * 二叉搜索树系列
 * 1373. 二叉搜索子树的最大键值和
 * 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
 *
 * 二叉搜索树的定义如下：
 *
 * 任意节点的左子树中的键值都 小于 此节点的键值。
 * 任意节点的右子树中的键值都 大于 此节点的键值。
 * 任意节点的左子树和右子树都是二叉搜索树。
 *
 */
public class No1373_maxSumBST {

    /**
     * 思路:
     *      十分朴素的实现,提交直接timeout,就算使用记录也一样
     *      参照别人的实现,从顶部发现一颗二叉搜索树时,二叉搜索子树一定在这颗树之中.利用此想法进行剪枝
     * @param root
     * @return
     */
    public int maxSumBST(TreeNode root) {
//        一开始使用 map记录,没有起很大作用
//        if(map.containsKey(root))
//            return map.get(root);
//        这里还可以优化,当以root为节点的树为bst时,那么节点和的最大值一定存在与这颗bst之中
        int[] max={0};
        if(validBst(root)){
            sum(max,root);
            return max[0];
        }
        if(root!=null){
            max[0]=Math.max(max[0],maxSumBST(root.left));
            max[0]=Math.max(max[0],maxSumBST(root.right));
        }
        return max[0];
    }

    /**
     * 判断是否是二叉搜索树
     * @param root
     * @return
     */
    private boolean validBst(TreeNode root){
        return validBst(root,null,null);
    }

    private boolean validBst(TreeNode curr,TreeNode min,TreeNode max){
        if(curr==null)
            return true;
        if(min!=null&&curr.val<=min.val)
            return false;
        if(max!=null&&curr.val>=max.val)
            return false;
        return validBst(curr.left,min,curr)&&validBst(curr.right,curr,max);
    }

    /**
     * 修改功能,统计这颗树的节点和的最大值
     * @param root
     * @return
     */
    private int sum(int[] max,TreeNode root){
        if(root==null)
            return 0;
        int res=root.val+sum(max,root.left)+sum(max,root.right);
        max[0]=Math.max(max[0],res);
        return res;
    }

//    start----------------------------------------------------------------------

    /**
     * 别人的思路:
     *      利用计算二叉树的最大节点和时,把这颗树底下的部分也计算出来,剪枝
     * @param root
     * @return
     */
    public int maxSumBST2(TreeNode root) {
        int[] res = {0};
        maxSumBST(res, root);
        return res[0];
    }

    /**
     * 判断root为根节点的树是否是二叉搜索树
     *
     * @param root root节点
     * @param min  最小值
     * @param max  最大值
     * @return 是否是二叉搜索树
     */
    private boolean isBST(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        return min < root.val && root.val < max && isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }

    /**
     * 求节点和的最大值
     *
     * @param res res
     * @param node node
     * @return 较大节点和
     */
    private int maxSumBST(int[] res, TreeNode node) {
        // 这个节点是BST，直接求和了
        if (isBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
            // 是二叉搜索树，求节点和，节点和的最优解，肯定在子节点求和的过程中
            return sumNodeValue(res, node);
        }
        // 不是BST，递归进入左右子树
        int left = maxSumBST(res, node.left);
        int right = maxSumBST(res, node.right);
        return Math.max(left, right);
    }

    /**
     * 以node为root的节点求和
     *
     * @param res  结果集
     * @param node node
     * @return 节点之和
     */
    private int sumNodeValue(int[] res, TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 二叉搜索树节点和的最大值，一定在子节点中会出现
        int sum = node.val + sumNodeValue(res, node.left) + sumNodeValue(res, node.right);
        // 这里记录结果的最大值
        res[0] = Math.max(res[0], sum);
        return sum;
    }

//    end----------------------------------------------------------------------



    public static void main(String[] args){
        No1373_maxSumBST test=new No1373_maxSumBST();
//        Integer[] nums={1,4,3,2,4,2,5,null,null,null,null,null,null,4,6};
//        Integer[] nums={4,3,null,1,2};
//        Integer[] nums={-4,-2,-5};
        Integer[] nums={-5,-10,-3,-12,-9,-4,5,null,null,null,null,null,null,4,6};
        TreeNode treeNode = generateNode(nums);
        int i = test.maxSumBST(treeNode);
        System.out.println(i);

        int i1 = test.maxSumBST2(treeNode);
        System.out.println(i1);
    }
}
