package com.luo.zhinan.linkedlist;

import com.luo.util.CommonUtil.ListNode;

/**
 * 给定一个链表和数字N, 按照每N个节点逆序
 */
public class ReverseListNodeGroup {

    public static ListNode reverseListNodeGroup(ListNode head, int n){
        // 不使用递归算法
        if(head!=null||head.next==null||n==1){
            return head;
        }
        return null;
    }

    /**
     * 将链表两个节点之间进行逆序
     * @param h1
     * @return
     */
    private static ListNode reverse(ListNode h1){
        if (h1==null){
            throw new IllegalArgumentException("ListNode can not be empty.");
        }
        if(h1.next==null){
            return h1;
        }
        ListNode t=h1, result=null;
        while(t!=null){
            ListNode next=t.next;
            t.next=result;
            result=t;
            t=next;
        }
        return result;
    }
}
