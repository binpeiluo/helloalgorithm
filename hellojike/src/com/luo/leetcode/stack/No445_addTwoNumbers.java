package com.luo.leetcode.stack;

import java.util.Stack;

import static com.luo.util.CommonUtil.*;
/**
 * 445. 两数相加 II
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。
 * 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 进阶：
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * 示例：
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 */
public class No445_addTwoNumbers {

    /**
     * navie想法是将两个链表组装成数字计算后再生成链表
     *
     * 对于逆序操作,应该想到栈,将链表入两个栈.然后处理相加,需要注意进位问题
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1=new Stack<>();
        while(l1!=null){
            s1.push(l1.val);
            l1=l1.next;
        }
        Stack<Integer> s2=new Stack<>();
        while(l2!=null){
            s2.push(l2.val);
            l2=l2.next;
        }
//        处理进位
        int c=0;
        ListNode res=null;
        while(!s1.isEmpty() && !s2.isEmpty()){
            Integer i1 = s1.pop();
            Integer i2 = s2.pop();
            int n=(i1+i2+c)%10;
            c=(i1+i2+c)/10;
            ListNode node=new ListNode(n);
            node.next=res;
            res=node;
        }
        while(!s1.isEmpty()){
            Integer i1=s1.pop()+c;
            c=i1/10;
            ListNode node=new ListNode(i1%10);
            node.next=res;
            res=node;
        }
        while(!s2.isEmpty()){
            Integer i2 = s2.pop()+c;
            c=i2/10;
            ListNode node=new ListNode(i2%10);
            node.next=res;
            res=node;
        }
        if(c!=0){
            ListNode node=new ListNode(c);
            node.next=res;
            res=node;
        }
        return res;
    }

    public static void main(String[] args){
        No445_addTwoNumbers test=new No445_addTwoNumbers();
//        [6,4,5,0,4,4,9,4,1]
//        [3,8,8,0,3,0,1,4,8]
        int[] nums1={6,4,5,0,4,4,9,4,1};
        int[] nums2={3,8,8,0,3,0,1,4,8};
        ListNode listNode = generateListNode(nums1);
        ListNode listNode1 = generateListNode(nums2);
        ListNode listNode2 = test.addTwoNumbers(listNode, listNode1);
        System.out.println(listNode2);
    }
}
