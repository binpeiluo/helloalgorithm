package com.luo.algorithm.doublepointer.fastslow;

import static com.luo.util.CommonUtil.*;
/**
 * 一、快慢指针的常见算法
 * 1、判定链表中是否含有环
 */
public class HasCycle {

    public boolean hasCycle(ListNode node){
        ListNode fast,slow;
        fast=slow=node;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)
                return true;
        }
        return false;
    }

    public static void main(String[] args){
        HasCycle test=new HasCycle();
        int[] nums={1,2,3,4,5,6,7,8,9,10};
        ListNode[] listNodes = generateListNodeCycle(nums, 5, 0);
        boolean b = test.hasCycle(listNodes[6]);
        System.out.println(b);
    }
}
