package com.luo.labuladong.dataconstruct;

import static com.luo.util.CommonUtil.*;
/**
 * 数据结构系列
 * 反转链表操作
 */
public class ReverseNode {

    ListNode sucessor=null;
    /**
     * 反转链表的一部分
     * @param head
     * @param num
     * @return
     */
    public ListNode reverseN(ListNode head,int num){
        if(num==1){
            sucessor=head.next;
            return head;
        }
        ListNode newHead = reverseN(head.next, num - 1);
        head.next.next=head;
        head.next=sucessor;
        return newHead;
    }

}
