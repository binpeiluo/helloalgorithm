package com.luo.algorithm.doublepointer.fastslow;

import static com.luo.util.CommonUtil.*;
/**
 * 4、寻找链表的倒数第 k 个元素
 */
public class FindLastK {

    /**
     * 使用两个指针，
     * @param node
     * @param k
     * @return
     */
    public ListNode findLastK(ListNode node,int k){
        ListNode fast,slow;
        fast=slow=node;
        while(k-->0)
            fast=fast.next;
        while(fast!=null){
            fast=fast.next;
            slow=slow.next;
        }
        return slow;
    }

    public static void main(String[] args){
        FindLastK test=new FindLastK();
        int[] nums={1,2,3,4,5,6,7,8,9,10};
        ListNode node = generateListNode(nums);
        ListNode lastK = test.findLastK(node,1);
        System.out.println(lastK.val);
    }
}

