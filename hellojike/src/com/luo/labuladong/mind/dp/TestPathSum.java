package com.luo.labuladong.mind.dp;

/**
 *  递归思维
 *
 *  递归的基本思想是直接或者间接的调用自身,把问题分解成性质一样但是规模更小的问题
 *  递归具有两条重要的性质:结束条件和自身调用
 *      自身调用是在解决子问题
 *      结束条件是在定义最简子问题
 *  递归思想跟数学归纳法很像
 *
 *  递归技巧:明白一个函数的定义并且相信他能完成这个功能,千万不能跳进细节
 *
 */
public class TestPathSum {

    /**
     * 求二叉树路劲中包含的路径和等于sum的条数
     *
     * 定义函数pathSum定义:返回以root节点为跟的树中,返回路径和等于目标值的条数
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root,int sum){
        if(root==null)
            return 0;
//        首先计算以自己为起点的路径的情况
        int seftCount = count(root, sum);
//        在计算左边和右边子树包含的路径条数
        int leftCount = pathSum(root.left, sum);
        int rightCount = pathSum(root.right, sum);
        return seftCount+leftCount+rightCount;
    }

    /**
     * 定义count函数为:以root为起点的路径中,路径和等于sum的条数
     * @param root
     * @param sum
     * @return
     */
    private int count(TreeNode root,int sum){
        if(root==null)
            return 0;
        int leftCount=count(root.left,sum-root.val);
        int rightCount = count(root.right, sum-root.val);
        return (sum==root.val?1:0)+leftCount+rightCount;
    }

    static class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val,TreeNode left,TreeNode right){
            this.val=val;
            this.left=left;
            this.right=right;
        }
        TreeNode(int val){
            this.val=val;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "left=" + left +
                    ", right=" + right +
                    ", val=" + val +
                    '}';
        }
    }


    public static void main(String[] args){
        TestPathSum test=new TestPathSum();
//        TreeNode 的值列表,忽略第一个值
        Integer[] nodeValues={null,1,2,3,4,5,6,7,8};
        TreeNode root = generateNode(nodeValues);
//        System.out.println(root);
        int i = test.pathSum(root,7);
        System.out.println(i);

    }

    private static TreeNode generateNode(Integer[] valuas){
        int len=valuas.length;
        TreeNode[] nodes=new TreeNode[len];
        for (int i = 0; i < len; i++) {
            if(valuas[i]!=null)
                nodes[i]=new TreeNode(valuas[i]);
        }
        for (int i = 1; i < len / 2; i++) {
            if(nodes[i]!=null){
                nodes[i].left=nodes[2*i];
                nodes[i].right=nodes[2*i+1];
            }
        }
        return nodes[1];
    }
}
