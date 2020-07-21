package com.luo.leetcode.divide;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.luo.util.CommonUtil.*;
/**
 * 95. 不同的二叉搜索树 II
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 *
 *  
 *
 * 示例：
 *
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 *
 */
public class No95_generateTrees {

    public List<TreeNode> generateTrees(int n) {
        if(n==0){
            return new ArrayList<>();
        }
        return helper(1,n);
    }

    private List<TreeNode> helper(int start,int end){
        List<TreeNode> res=new ArrayList<>();
        if(start>end){
            res.add(null);
            return res;
        }

        for(int i=start;i<=end;i++){
            List<TreeNode> left=helper(start,i-1);
            List<TreeNode> right=helper(i+1,end);
            for(TreeNode l:left){
                for(TreeNode r:right){
                    TreeNode t=new TreeNode(i);
                    t.left=l;
                    t.right=r;
                    res.add(t);
                }
            }
        }
        return res;
    }

    public static void main(String[] args){
        No95_generateTrees test=new No95_generateTrees();
        List<TreeNode> treeNodes = test.generateTrees(3);
        System.out.println(treeNodes);
    }
}
