package com.luo.leetcode.bst;

import static com.luo.util.CommonUtil.*;

/**
 * 109. 有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 */
public class No109_sortedListToBST {


    /**
     * 自己还是没想出来
     * 思路是:
     *      如果需要高度平衡,那么左子树和右子树的节点数量应该接近或者相差一
     *      使用二分将链表分为两部分,然后构建二叉树
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        return divide(head);
    }

    /**
     * 使用快慢指针找出中间节点
     * @return
     */
    private TreeNode divide(ListNode head){
        if(head==null){
            return null;
        }
        ListNode midPrev = findMidPrev(head);
        if(midPrev==null){
            return new TreeNode(head.val);
        }
        ListNode mid=midPrev.next;
        ListNode midNext=mid.next;
        midPrev.next=null;
        mid.next=null;
        TreeNode midTree=new TreeNode(mid.val);
        TreeNode leftTree = divide(head);
        TreeNode rightTree = divide(midNext);
        midTree.left=leftTree;
        midTree.right=rightTree;
        return midTree;
    }

    /**
     * 找出中间节点的前驱
     * @param head
     * @return
     */
    private ListNode findMidPrev(ListNode head){
        ListNode fast,slow;
        ListNode prev=null;
        fast=head;
        slow=head;
        while(fast!=null&&fast.next!=null){
            prev=slow;
            slow=slow.next;
            fast=fast.next.next;
        }
        return prev;
    }


    /**
     * 别人的实现  更加简练
     * 边界处理的很nice
     * @param head
     * @return
     */
    public TreeNode sortedListToBST2(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return new TreeNode(head.val);
        }

        // 快慢指针找中心节点
        ListNode p = head, q = head, pre = null;
        while (q != null && q.next != null) {
            pre = p;
            p = p.next;
            q = q.next.next;
        }
        pre.next = null;

        // 以升序链表的中间元素作为根节点 root，递归的构建 root 的左子树与右子树。
        TreeNode root = new TreeNode(p.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(p.next);
        return root;
    }

    public static void main(String[] args){
        No109_sortedListToBST test=new No109_sortedListToBST();
        int[] nums={-10, -3, 0, 5, 9};
        ListNode listNode = generateListNode(nums);

        TreeNode treeNode = test.sortedListToBST(listNode);
        System.out.println(treeNode);
    }
}
