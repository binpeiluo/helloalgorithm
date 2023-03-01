package com.luo.algorithm.bst;

import static com.luo.util.CommonUtil.*;

/**
 * 二叉树系列
 * 1038. 从二叉搜索树到更大和树
 * 给出二叉 搜索 树的根节点，该二叉树的节点值各不相同，修改二叉树，
 * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 */
public class TestBstToGst {


    int sum=0;
    /**
     * 利用二叉搜索树性质.
     * 节点的值更新为树中大于等于该节点的值的和
     * @param root
     * @return
     */
    public TreeNode bstToGst(TreeNode root) {
        if(root!=null){
            if(root.right!=null)
                bstToGst(root.right);
            sum+=root.val;
            root.val=sum;
            if (root.left!=null)
                bstToGst(root.left);
        }
        return root;
    }



    public static void main(String[] args){
        TestBstToGst test=new TestBstToGst();
        Integer[] nums={4,1,6,0,2,5,7,null,null,null,3,null,null,null,8};
//        Integer[] nums={2,1,3};
        TreeNode treeNode = generateNode(nums);
        System.out.println(treeNode);
        test.bstToGst(treeNode);
        System.out.println(treeNode);

    }
}
