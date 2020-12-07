package com.luo.labuladong.dataconstruct;

import com.luo.util.CommonUtil;

import static com.luo.util.CommonUtil.*;
/**
 * 数据结构系列
 * 反转链表操作
 */
@SuppressWarnings("Duplicates")
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
        }else{
            head.next=reverseBetween(head.next,m-1,n-1);
        }
        return head;
    }

    /**
     * 以k个为一组,反转链表
     * 递归性质
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head,int k){
        ListNode temp=head;
//        不足k个则不反转
        for (int i = 0; i < k; i++) {
            if(temp==null){
                return head;
            }
            temp=temp.next;

        }
        ListNode node = reverseBetween(head, temp);
        head.next=reverseKGroup(temp,k);
        return node;
    }

    /**
     * 反转链表节点[left,right)
     * @param left
     * @param right
     * @return
     */
    private ListNode reverseBetween(ListNode left,ListNode right){
        ListNode curr=left,head=right,next=null;
        while(curr!=right){
            next=curr.next;
            curr.next=head;
            head=curr;
            curr=next;
        }
        return head;
    }

    public static void main(String[] args) {
        ReverseNode test=new ReverseNode();
        int[] nums={1,2,3,4,5};
        ListNode head = generateListNode(nums);
        CommonUtil.printListNode(head);
        int m=2,n=4;

//        head = test.reverseBetween(head, m, n);
//        CommonUtil.printListNode(head);

        ListNode listNode = test.reverseKGroup(head, 2);
        CommonUtil.printListNode(listNode);
    }

}
