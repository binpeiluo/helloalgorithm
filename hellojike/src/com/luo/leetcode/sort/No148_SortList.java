package com.luo.leetcode.sort;

import java.util.Arrays;
import java.util.List;

/*
    148. 排序链表

    在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
        示例 1:
        输入: 4->2->1->3
        输出: 1->2->3->4
        示例 2:
        输入: -1->5->3->4->0
        输出: -1->0->3->4->5
*/
public class No148_SortList {
    //     * Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 思路:
     *      时间复杂度O(nlogn),可以使用二分法
     *      空间复杂度O(1),不可以使用递归实现
     *
     *      非递归实在有点复杂,暂时使用递归实现
     *      二分法实现重点在于如何二分,二分后如何合并
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if(head==null||head.next==null)
            return head;
//        使用快慢指针法实现链表切分
//        这里需要注意快指针需要赋值为慢指针后继,不然当指针过短时会出现循环原地调用 sortList(head)而引发栈溢出
        ListNode slow=head,fast=head.next;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode lat=slow.next;
        slow.next=null;
        ListNode left=sortList(head);
        ListNode right=sortList(lat);
        ListNode h=new ListNode(-1);
        ListNode tmp=h;
        while(left!=null&&right!=null){
            if(left.val<right.val){
                tmp.next=left;
                left=left.next;
            }else{
                tmp.next=right;
                right=right.next;
            }
            tmp=tmp.next;
        }
        tmp.next=left==null?right:left;
        return h.next;
    }

    public static ListNode generateListNode(List<Integer> nums){
        ListNode head=null;
        for(int i=nums.size()-1;i>=0;i--){
            ListNode temp=new ListNode(nums.get(i));
            temp.next=head;
            head=temp;
        }
        return head;
    }

    public static void print(ListNode head){
        System.out.println("打印链表:");
        printListNode(head);
        System.out.println();
    }

    private static void printListNode(ListNode head){
        if(head!=null){
            System.out.print("->"+head.val);
            printListNode(head.next);
        }
    }

    public static void main(String[] args){
        ListNode listNode = generateListNode(Arrays.asList(4, 2));
        print(listNode);

        No148_SortList test=new No148_SortList();
        ListNode newHead = test.sortList(listNode);
        print(newHead);
    }
}
