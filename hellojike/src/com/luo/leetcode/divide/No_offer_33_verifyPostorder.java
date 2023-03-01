package com.luo.leetcode.divide;


import java.util.Stack;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 *
 * 参考以下这颗二叉搜索树：
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 * 示例 1：
 *
 * 输入: [1,6,3,2,5]
 * 输出: false
 * 示例 2：
 *
 * 输入: [1,3,2,6,5]
 * 输出: true
 *
 */
@SuppressWarnings("Duplicates")
public class No_offer_33_verifyPostorder {

    /**
     * 使用分治递归法
     *
     * 时间复杂度:   O(n^2)    每次调用 recur 则减少一个根节点,故需要遍历n次,
     * 空间复杂度:   O(n)
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }
    boolean recur(int[] postorder, int i, int j) {
//        如果区间的节点个数少于一个,那么属于base case
        if(i >= j)
            return true;
//        找到第一个大于根节点的节点,从而划分左子树和右子树
        int p = i;
        while(postorder[p] < postorder[j])
            p++;
        int m = p;
//        判断是否符合二叉树
//          左字树节点都小于根节点,这个在寻找第一个大于根节点的节点时已经判断过了,现在只需要判断右子树即可
        while(postorder[p] > postorder[j])
            p++;
        return p == j
                && recur(postorder, i, m - 1)
                && recur(postorder, m, j - 1);
    }

    /**
     * 后序遍历的顺序为:    左子树->右子树->根节点
     * 二叉树的大小顺序为:   左节点<根节点<右节点
     *
     * 所以将数组arr[i,j]从前往后遍历,当找到第一个大于根节点(最后一个元素arr[j])时,节点位置记为m,将数组分成三部分.
     *  第一部分为根节点的左子树 范围是arr[i,m-1].该部分按照二叉树规则应该小于根节点,这个已经验证过了
     *  第二部分是根节点的右子树 范围是arr[m,j-1].该部分按照二叉树的规则应该大于根节点
     *  第三部分是根节点arr[j]
     *
     *  首先判断这三部分组成的树是否符合二叉树定义,然后在递归判断左子树和右子树是否符合二叉树定义
     *
     * @param postorder
     * @return
     */
    public boolean verifyPostorder2(int[] postorder){
        return helper2(postorder,0,postorder.length-1);
    }

    private boolean helper2(int[] postorder,int i,int j){
//        base case
        if(i>=j){
            return true;
        }
        int m=i;
        while(postorder[m]<postorder[j]){
            m++;
        }
//        左子树为 [i,m-1]  右子树为 [m,j-1]    跟节点为 j
//        此时已经将数组划分为三部分.接下来判断左子树和右子树与根节点构成的树是否符合二叉树定义
//          1 判断左子树全部小于根节点,前边已经判断过了
//          2 判断右子树全部大于根节点
//        接着递归判断

        int p=m;
        while(postorder[p]>postorder[j]){
            p++;
        }
        boolean currValid=p==j;
        return currValid && helper2(postorder,i,m-1) && helper2(postorder,m,j-1);
    }


    /**
     * 使用单调栈 不懂
     * @param postorder
     * @return
     */
    public boolean verifyPostorder3(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for(int i = postorder.length - 1; i >= 0; i--) {
            if(postorder[i] > root) return false;
            while(!stack.isEmpty() && stack.peek() > postorder[i])
                root = stack.pop();
            stack.add(postorder[i]);
        }
        return true;
    }

    public static void main(String[] args){
        No_offer_33_verifyPostorder test=new No_offer_33_verifyPostorder();

//        int[] postorder={1,6,3,2,5};
        int[] postorder={1,3,2,6,5};
        boolean b = test.verifyPostorder(postorder);
        System.out.println(b);

        boolean b1 = test.verifyPostorder2(postorder);
        System.out.println(b1);

    }

}
