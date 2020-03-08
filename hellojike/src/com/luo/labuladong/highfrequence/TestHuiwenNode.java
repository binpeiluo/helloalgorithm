package com.luo.labuladong.highfrequence;

/**
 * 高频问题
 * 判断链表是否是回文串
 *
 */
public class TestHuiwenNode {

    ListNode left=null;
    /**
     * 链表判断是否回文比数组或者字符串复杂,能像到的是遍历链表将值存入数组中然后再判断.但这种方式不够优雅
     *
     * labuladong提供一种新的实现方式.可以使用递归构造一个栈完成.比较巧妙.
     * 想象一下,树的遍历,后序遍历.可以在外部使用一个变量接收链表的前部分,而函数中传入的值为链表的后部分
     *
     * 时间复杂度:   O(n)
     * 空间复杂度:   O(n),函数栈
     * @param head
     * @return
     */
    public boolean isHuiwen(ListNode head){
        left=head;
        return traverse(left);
    }

    private boolean traverse(ListNode right){
        if(right==null)
            return true;
        boolean res=traverse(right.next);
        res=res&&left.val==right.val;
        left=left.next;
        return res;
    }


    /**
     * 通过快慢指针找到中间节点.然后倒序后半部分,再比较两个链表
     *
     * 时间复杂度:   O(n)
     * 空间复杂度:   O(1)
     * @param head
     * @return
     */
    public boolean isHuiwen2(ListNode head){
        boolean res=true;
//        找出中间点,pre为slow的前节点
        ListNode slow,fast,pre;
        pre=null;
        slow=fast=head;
        while(fast!=null&&fast.next!=null){
            pre=slow;
            slow=slow.next;
            fast=fast.next.next;
        }
//        假如节点个数为偶数则slow还需往前一步
        if(fast!=null){
            pre=slow;
            slow=slow.next;
        }
        ListNode left=head,right;
        ListNode rightHead;
//        反转slow后边的
        right=reverse(slow);
        rightHead=right;
//        判断两个链表,注意left链表有可能多一个节点
        while(right!=null){
            if(right.val!=left.val){
                res=false;
                break;
            }
            left=left.next;
            right=right.next;
        }
//        恢复链表
        pre.next=reverse(rightHead);
        return res;

    }

    private ListNode reverse(ListNode head){
        ListNode pre=null,curr=head,next=head;
        while(curr!=null){
            next=curr.next;
            curr.next=pre;
            pre=curr;
            curr=next;
        }
        return pre;
    }

    private static void display(ListNode head){
        System.out.println("链表:");
        while(head!=null){
            System.out.print(head.val+"\t");
            head=head.next;
        }
        System.out.println();
    }



    public static void main(String[] args){
        TestHuiwenNode test=new TestHuiwenNode();
        int[] nums={1,2,3,4,4,3,2,1};
        ListNode head = generate(nums);
        display(head);
        boolean valid = test.isHuiwen(head);
        System.out.println(valid);

        display(head);
        boolean huiwen2 = test.isHuiwen2(head);
        System.out.println(huiwen2);

        display(head);

    }





    static class ListNode{
        ListNode next;
        int val;
        public ListNode(ListNode next, int val) {
            this.next = next;
            this.val = val;
        }
    }

    private static ListNode generate(int[] nums){
        int len=nums.length;
        ListNode[] nodes=new ListNode[len];
        for (int i = 0; i < len; i++) {
            nodes[i]=new ListNode(null,nums[i]);
        }
        for (int i = 0; i < len - 1; i++) {
            nodes[i].next=nodes[i+1];
        }
        return nodes[0];
    }
}
