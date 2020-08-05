package com.luo.leetcode.bst;

import static com.luo.util.CommonUtil.*;

/**
 * 337. 打家劫舍 III
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 */
@SuppressWarnings("Duplicates")
public class No337_rob {

    /**
     * 二叉树遍历问题
     * naive想法,递归
     * 太慢了,使用map记录
     * @param root
     * @return
     */
    public int rob(TreeNode root){
        return recurse(root);
    }

    private int recurse(TreeNode root){
        if(root==null){
            return 0;
        }
//        方案一,打劫该根节点
        int robRoot=root.val;
        if(root.left!=null){
            robRoot+=recurse(root.left.left);
            robRoot+=recurse(root.left.right);
        }
        if(root.right!=null){
            robRoot+=recurse(root.right.left);
            robRoot+=recurse(root.right.right);
        }
//        方案二,不打劫该根节点
        int noRobRoot=0;
        noRobRoot+=recurse(root.left);
        noRobRoot+=recurse(root.right);

        return Math.max(robRoot,noRobRoot);
    }

    /**
     * 这种想法也是666
     * @param root
     * @return
     */
    public int rob2(TreeNode root){
        int[] ints = recurse2(root);
        return Math.max(ints[0],ints[1]);
    }

    /**
     * 该函数作用,输入一个二叉树节点,从该节点开始打劫
     * 返回一个长度为2的数组,位置0代表打劫了根节点所能得到的最大和.位置1代表不打劫根节点能获取的最大值
     * @param root
     * @return
     */
    private int[] recurse2(TreeNode root){
        if(root==null){
            return new int[]{0,0};
        }
        int[] leftValue = recurse2(root.left);
        int[] rightValue = recurse2(root.right);
        int[] rootValue=new int[2];
        rootValue[0]=root.val+leftValue[1]+rightValue[1];
        rootValue[1]=Math.max(leftValue[0],leftValue[1])+Math.max(rightValue[0],rightValue[1]);
        return rootValue;
    }

    public static void main(String[] args){
        No337_rob test=new No337_rob();
        Integer[] nums={3,2,3,null,3,null,1};
        TreeNode root = generateNode(nums);

        int rob = test.rob(root);
        System.out.println(rob);

        int i = test.rob2(root);
        System.out.println(i);
    }

}
