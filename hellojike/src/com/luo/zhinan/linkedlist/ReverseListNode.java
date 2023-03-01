package com.luo.zhinan.linkedlist;

import com.luo.util.CommonUtil;
import com.luo.util.CommonUtil.ListNode;

/**
 * 反转链表
 */
public class ReverseListNode {

    public static ListNode reverseListNode(ListNode h){
        if(h==null||h.next==null){
            return h;
        }
        ListNode result=null, curr=h;
        while(curr!=null){
            ListNode next=curr.next;
            curr.next=result;
            result=curr;
            curr=next;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums={1,2,3,4,5};
        ListNode h = CommonUtil.generateListNode(nums);
        CommonUtil.printListNode(h);
        h = ReverseListNode.reverseListNode(h);
        CommonUtil.printListNode(h);
    }
}
