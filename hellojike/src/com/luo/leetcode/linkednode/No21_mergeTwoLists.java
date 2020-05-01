package com.luo.leetcode.linkednode;

import com.luo.util.CommonUtil;

import static com.luo.util.CommonUtil.*;
/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class No21_mergeTwoLists {

    /**
     * 遍历
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(-1);
        ListNode last=head;
        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                last.next=l1;
                last=l1;
                l1=l1.next;
            }else{
                last.next=l2;
                last=l2;
                l2=l2.next;
            }
        }
        if(l1==null)
            last.next=l2;
        if(l2==null)
            last.next=l1;
        return head.next;
    }

    /**
     * 递归
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1,ListNode l2){
        ListNode head=new ListNode(-1);
        head.next=helper2(l1,l2);
        return head.next;
    }

    private ListNode helper2(ListNode l1,ListNode l2){
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
        if(l1.val<l2.val){
            l1.next=helper2(l1.next,l2);
            return l1;
        }
        else{
            l2.next=helper2(l1,l2.next);
            return l2;
        }
    }

    public static void main(String[] args){
        No21_mergeTwoLists test=new No21_mergeTwoLists();
        int[] num1={2,4,6,8,10};
        int[] num2={1,3,5,7,9};
        ListNode l1 = generateListNode(num1);
        ListNode l2 = generateListNode(num2);
        ListNode listNode = test.mergeTwoLists2(l1, l2);
        CommonUtil.printListNode(listNode);
    }
}
