package com.luo.labuladong.dp.robber;

import static com.luo.util.CommonUtil.*;
/*

337. 打家劫舍 III
在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
        这个地区只有一个入口，我们称之为“根”。 除了“根”之外，
        每栋房子有且只有一个“父“房子与之相连。一番侦察之后，
        聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
        如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

示例 1:
输入: [3,2,3,null,3,null,1]

 3
/ \
2   3
\   \
3   1

输出: 7
解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
示例 2:

输入: [3,4,5,1,3,null,1]

     3
    / \
    4   5
    / \   \
    1   3   1

输出: 9
解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
*/

public class HouseRobber3 {


    public int rob(TreeNode root){
//        当小偷来到一个房屋前,有两种选择,打劫or不打劫
//        当打劫了,那么只能不能再打劫该节点的子节点
//        当没有打劫,可以打劫两个子节点之一

        return helper(root,true);
    }

    private int helper(TreeNode node,boolean canRob){
        if(node==null){
            return 0;
        }
        if(!canRob){
            return Math.max(helper(node.left,true),
                    helper(node.right,true));
        }else{
            int rob=node.val;
            rob+=helper(node.left,false)+
                    helper(node.right,false);
            int norob=helper(node.left,true)+helper(node.right,true);
            return Math.max(rob,norob);
        }
    }

    public int rob2(TreeNode root){
//        假如当前选择打劫了该房屋,那么就不能打劫两个子房屋
        if(root==null){
            return 0;
        }
        int rob=root.val;
        if(root.left!=null){
            rob+=rob2(root.left.left)+rob2(root.left.right);
        }
        if(root.right!=null){
            rob+=rob2(root.right.left)+rob2(root.right.right);
        }

        int norob=rob2(root.left)+rob2(root.right);
        return Math.max(rob,norob);
    }

    public static void main(String[] args){
//        Integer[] nums={3,2,3,null,3,null,1};
        Integer[] nums={3,4,5,1,3,null,1};
        TreeNode treeNode = generateNode(nums);

        HouseRobber3 test=new HouseRobber3();
        int rob = test.rob(treeNode);
        System.out.println(rob);

        int i = test.rob2(treeNode);
        System.out.println(i);
    }

}
