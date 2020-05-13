package com.luo.algorithm.doublepointer.fastslow;

import static com.luo.util.CommonUtil.*;
/**
 * 2、已知链表中含有环，返回这个环的起始位置
 */
public class DetectCycle {

    /**
     * 有点秒
     * 首先通过快慢指针找到相遇的位置，这时慢指针走了k步，快指针走了2*k步。环的长度为k
     * 假设快慢指针相遇点距离环起始位置为m，则慢指针走到环起点的步长为k-m,而快指针继续走k-m步也可以回到起点
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast,slow;
        fast=slow=head;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow)
                break;
        }
        slow=head;
        while(slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }

    public static void main(String[] args){
        DetectCycle test=new DetectCycle();
        int[] nums={1,2,3,4,5,6,7,8,9,10};
        ListNode[] listNodes = generateListNodeCycle(nums, 5, 1);
        ListNode node = test.detectCycle(listNodes[0]);
        System.out.println(node.val);

    }
}
