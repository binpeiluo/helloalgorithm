package com.luo.zhinan.linkedlist;

import com.luo.util.CommonUtil;
import com.luo.util.CommonUtil.ListNode;

import java.util.Stack;

/**
 * 对两条链表求和
 */
public class ListNodeSum {

    /**
     * 一种方式是求出链表对应的值, 然后计算, 但是如果链表长度过长, 那么会溢出
     * 另外可以使用栈结构辅助计算, 将链表值入栈, 然后在计算和
     * 如果不需要辅助栈的话, 就先逆序两条链表, 然后计算生成新链表, 最后再将原链表再次逆序
     * @return
     */
    public static ListNode listNodeSum(ListNode h1, ListNode h2){
        if(h1==null||h2==null){
            return h1==null?h2:h1;
        }
        Stack<Integer> s1=new Stack<>(), s2=new Stack<>();
        ListNode t=null, result=null;
        t=h1;
        // 入栈
        while(t!=null){
            s1.push(t.val);
            t=t.next;
        }
        t=h2;
        while(t!=null){
            s2.push(t.val);
            t=t.next;
        }

        // 计算
        int add=0; // 进位 可能只剩下进位了 所以需要放在外边
        while(!s1.isEmpty()||!s2.isEmpty()){
            int curr=0;
            if (!s1.isEmpty()){
                curr+=s1.pop();
            }
            if (!s2.isEmpty()){
                curr+=s2.pop();
            }
            curr+=add;
            add=curr/10;
            ListNode n=new ListNode(curr%10);
            n.next=result;
            result=n;
        }
        if(add>0){
            ListNode n=new ListNode(add);
            n.next=result;
            result=n;
        }
        return result;
    }


    public static void main(String[] args) {
        int[] nums={0,0,2,3,0};
        ListNode h1 = CommonUtil.generateListNode(nums);
        CommonUtil.printListNode(h1);
        nums=new int[]{0,9, 0, 1, 4};
        ListNode h2 = CommonUtil.generateListNode(nums);
        CommonUtil.printListNode(h2);

        ListNode result = ListNodeSum.listNodeSum(h1, h2);
        CommonUtil.printListNode(result);
    }
}
