package com.luo.labuladong.highfrequence;


/**
 * 高频题目
 * k个一组反转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 */
public class TestReverseNode {

    /**
     * 给定一个链表节点,以k个一组反转这个链表,并返回链表头部
     *
     * 思考,这个功能具有重复性,反转了前面k个链表后,接下来在以后边的节点反转剩下的节点
     * @param head  链表头部
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null)
            return head;
        ListNode left=head,right=head;
        for(int i=0;i<k;i++){
            if(right==null)
                return head;
            right=right.next;
        }
        ListNode newHead = reverseR(left, right);
        left.next=reverseKGroup(right,k);
        return newHead;
    }

    /**
     * 给定两个节点,导致两个节点之间的节点顺序.并返回头结点
     * @param left
     * @param right
     * @return
     */
    private ListNode reverseR(ListNode left,ListNode right){
        ListNode pre=null,curr=left,next=left;
        while(curr!=right){
            next=curr.next;
            curr.next=pre;
            pre=curr;
            curr=next;
        }
        return pre;
    }

    /**
     * 反转整个链表   迭代实现
     * @param head
     * @return
     */
    private ListNode reverse(ListNode head){
        ListNode pre=null,curr=head,next=head;
        while(curr!=null){
            next=curr.next;
            curr.next=pre;
            pre=curr;
            curr=next;
        }
        return pre;
    }

    /**
     * 反转整个链表,递归实现
     *
     * 定义这个函数作用:
     *      给定一个链表节点,反转以该节点为起点的链表,并返回新的头结点
     * @param head
     * @return
     */
    private ListNode reverseRecurse(ListNode head){
//        base case
        if(head.next==null)
            return head;
        ListNode last=reverseRecurse(head.next);
        head.next.next=head;
        head.next=null;
        return last;
    }

    private ListNode successor=null;
    /**
     * 给定一个链表节点,倒置这个链表的前n个节点,并返回新起点
     * @param head  链表起点
     * @param n     倒置的个数
     * @return      新的链表起点
     */
    private ListNode reverseN(ListNode head,int n){
        if(n==1){
            successor=head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        head.next.next=head;
        head.next=successor;
        return last;
    }

    /**
     * 给定一个链表和两个数字,反转链表从第m个到第n个之间的部分,并返回表头
     * @param head  表头
     * @param m     从第m个节点
     * @param n     到第n个节点
     * @return      新的头结点
     */
    private ListNode reverseBetween(ListNode head,int m,int n){
        if(m==1)
            return reverseN(head,n);
        head.next=reverseBetween(head.next,m-1,n-1);
        return head;
    }


    public static void main(String[] args){
        TestReverseNode test=new TestReverseNode();
        int[] nums={1,2,3,4,5};
        ListNode node = generate(nums);
//        System.out.println(node.toString());

        ListNode reverse = test.reverse(node);
        System.out.println(reverse);

        ListNode newHead = test.reverseRecurse(reverse);
        System.out.println(newHead);

        ListNode node1 = test.reverseN(newHead, 2);
        System.out.println(node1);

        ListNode node2 = test.reverseBetween(node1, 2, 3);
        System.out.println(node2);

        ListNode node3 = test.reverseKGroup(node2, 2);
        System.out.println(node3);
    }

    private static ListNode generate(int[] nums){
        int len=nums.length;
        ListNode[] nodes=new ListNode[len];
        for (int i = 0; i < len; i++) {
            nodes[i]=new ListNode(nums[i]);
        }
        for (int i = 0; i < len - 1; i++) {
            nodes[i].next=nodes[i+1];
        }
        return nodes[0];
    }


    //     * Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
