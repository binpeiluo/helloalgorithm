package com.luo.algorithm.doublepointer.fastslow;

import static com.luo.util.CommonUtil.*;
/**
 * 3、寻找链表的中点
 */
public class FindMiddle {

    public ListNode findMiddle(ListNode node){
        ListNode fast,slow;
        fast=slow=node;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }

    public static void main(String[] args){
        FindMiddle test=new FindMiddle();
        int[] nums={1,2,3,4,5,6,7,8,9,10,11};
        ListNode node = generateListNode(nums);
        ListNode middle = test.findMiddle(node);
        System.out.println(middle.val);
    }
}

