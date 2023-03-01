package com.luo.leetcode.divide;

import static com.luo.util.CommonUtil.*;
/**
 * 572. 另一个树的子树
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
 * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 *
 * 示例 1:
 * 给定的树 s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 *
 * 示例 2:
 * 给定的树 s：
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * 给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 * 返回 false。
 */
public class No572_isSubtree {

    /**
     * 很典型的分治思想
     * 当前判断两棵树是否相等，假如不相等则判断s的左子树是否与t相等 或者 s的右子树是否与t相等
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
//        避免递归时空指针
        if(s==null&&t!=null)
            return false;
        boolean b = equalsTree(s, t);
        if(b)
            return b;
        else
            return isSubtree(s.left,t) || isSubtree(s.right,t);
    }

    private boolean equalsTree(TreeNode s,TreeNode t){
        if(s==null&&t==null)
            return true;
        if(s==null||t==null)
            return false;
        if(s.val!=t.val)
            return false;
        return equalsTree(s.left,t.left)
                && equalsTree(s.right,t.right);
    }

    public static void main(String[] args){
        No572_isSubtree test=new No572_isSubtree();
//        Integer[] snums={3,4,5,1,2};
//        Integer[] tnums={4,1,2};

        Integer[] snums={3,4,5,1,2,null,null,null,null,0};
        Integer[] tnums={4,1,2};



        TreeNode s = generateNode(snums);
        TreeNode t = generateNode(tnums);
        boolean subtree = test.isSubtree(s, t);
        System.out.println(subtree);
    }
}
