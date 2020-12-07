package com.luo.labuladong.dataconstruct;

import com.luo.util.CommonUtil;

import static com.luo.util.CommonUtil.*;
/**
 * 数据结构系列
 * 反转链表操作
 */
public class ReverseNode {

    /**
     * 反转整条链表
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head){
        if(head.next==null){
            return head;
        }
        ListNode reverse=reverse(head.next);
        head.next.next=head;
        head.next=null;
        return reverse;
    }


    ListNode successor=null;
    /**
     * 反转链表的前n个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode reverseN(ListNode head,int n){
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
     * 反转链表的一部分
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head,int m,int n){
        if(m==1){
            return reverseN(head,n);
        }
        head.next=reverseBetween(head.next,m-1,n-1);

        return head;
    }


    public static void main(String[] args) {
        ReverseNode test=new ReverseNode();
        int[] nums={1,2,3,4,5};
        ListNode head = generateListNode(nums);
        CommonUtil.printListNode(head);
        int m=2,n=4;

        head = test.reverseBetween(head, m, n);
        CommonUtil.printListNode(head);
    }

}
