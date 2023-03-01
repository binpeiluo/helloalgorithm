package com.luo.algorithm.dp;

import com.luo.util.CommonUtil;

import java.util.HashMap;
import java.util.Map;

import static com.luo.util.CommonUtil.*;

/**
 * 337. 打家劫舍 III
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 */
public class TestHourseRob3 {


    Map<TreeNode,Integer> memo=new HashMap<>();

    /**
     * 备忘录解法.
     * 状态   当前节点
     * 选择   假如抢劫了当前节点,那么不能抢劫子节点,只能抢子子节点
     *          假如没有抢劫当前节点,那么可以抢劫子节点
     *          由此指导递归函数定义,
     * 使用递归思想,rob表示以root为首节点,最多能抢rob(root)金额
     * 状态转移     dp(root)=Math(dp(root)+dp(root.left.left)+dp(root.left.right))
     *                          +dp(root.right.left)+dp(root.right.right)
     *                         ,dp(root.left)+dp(root.right)
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if(root==null)
            return 0;
        if(memo.containsKey(root))
            return memo.get(root);
        int selected=root.val
                +(root.left==null?0:(rob(root.left.left)+rob(root.left.right)))
                +(root.right==null?0:(rob(root.right.left)+rob(root.right.right)));
        int noSelected=rob(root.left)+rob(root.right);
        int res = Math.max(selected, noSelected);
        memo.put(root,res);
        return res;
    }

    /**
     * 另外一种递归定义
     * 定义dp(root) 返回一个长度为2的数组,分别代表抢劫root节点时的最大金额和没有抢劫root的最大金额
     * @param root
     * @return
     */
    public int rob2(TreeNode root){
        int[] ints = dp2(root);
        return Math.max(ints[0],ints[1]);
    }

    private int[] dp2(TreeNode root){
        if(root==null)
            return new int[]{0,0};
        int[] left = dp2(root.left);
        int[] right = dp2(root.right);
//        抢劫了根节点
        int selected=root.val+left[1]+right[1];
        int noSelected=Math.max(left[0],left[1])+Math.max(right[0],right[1]);
        return new int[]{selected,noSelected};
    }

    public static void main(String[] args){
        TestHourseRob3 test=new TestHourseRob3();
        Integer[] nodes={3,4,5,1,3,null,1};
        TreeNode treeNode = generateNode(nodes);
        int rob = test.rob(treeNode);
        System.out.println(rob);

        int i = test.rob2(treeNode);
        System.out.println(i);
    }

}
