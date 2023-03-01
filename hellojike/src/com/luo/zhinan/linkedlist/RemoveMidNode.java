package com.luo.zhinan.linkedlist;

import com.luo.util.CommonUtil;
import com.luo.util.CommonUtil.ListNode;

public class RemoveMidNode {

    public static ListNode removeMidNode(ListNode h){
        // 如果只有一个元素 则移除
        if(h==null||h.next==null){
            return h;
        }
        ListNode slow=h, fast=h,prev=null;
        // 双指针寻找中间节点
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            // 如果快指针不为空, 则移动满指针
            // 因为如果中间有两个元素则打印前边的元素
            // 在这兼容只有三个元素的情况
            if(fast!=null){
                // 记录慢指针的前指针
                prev=slow;
                slow=slow.next;
            }
        }
        if(prev==null){
            // 如果前指针等于null 则说明元素小于两个, 直接移除头元素
            h=h.next;
        }else{
            // 否则通过prev指针跳过一个元素, prev.next肯定不为空
            prev.next=prev.next.next;
        }
        return h;
    }

    /**
     * 如果只有一个元素, 那么不需要删除
     * 如果有两个元素, 删除第一个元素
     * 如果有三个元素, 那么删除第二个元素
     * 如果有四个元素, 那么删除第二个元素
     * 如果有五个元素, 那么删除第三个元素
     * 如果有六个元素, 那么删除第三个元素
     * @param h
     * @return
     */
    public static ListNode removeMidNodeV2(ListNode h){
        // 只有一个元素, 不移除
        if(h==null||h.next==null){
            return h;
        }
        // 两个元素, 则移除第一个元素
        if(h.next.next==null){
            return h.next;
        }
        // 保留前指针, 用于移除下一个节点
        // 使用快指针 直接跳到第三个元素
        ListNode prev=h, curr=h.next.next;
        // 开始遍历
        while(curr.next!=null&&curr.next.next!=null){
            curr=curr.next.next;
            prev=prev.next;
        }
        prev.next=prev.next.next;
        return h;
    }

    public static void main(String[] args) {
        int[] nums={1,2,3,4};
        ListNode h = CommonUtil.generateListNode(nums);
        CommonUtil.printListNode(h);
//        h = RemoveMidNode.removeMidNode(h);
        h = RemoveMidNode.removeMidNodeV2(h);
        CommonUtil.printListNode(h);
    }
}
