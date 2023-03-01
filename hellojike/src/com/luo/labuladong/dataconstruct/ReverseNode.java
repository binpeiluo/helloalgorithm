package com.luo.labuladong.dataconstruct;

import com.luo.util.CommonUtil;

import static com.luo.util.CommonUtil.*;
/**
 * 数据结构系列
 * 反转链表操作
 */
@SuppressWarnings("Duplicates")
public class ReverseNode {

    /**
     * 反转整条链表
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head){
        if(head.next==null){
            return head;
        }
        ListNode reverse=reverse(head.next);
        head.next.next=head;
        head.next=null;
        return reverse;
    }


    ListNode successor=null;
    /**
     * 反转链表的前n个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode reverseN(ListNode head,int n){
        if(n==1){
            successor=head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        head.next.next=head;
        head.next=successor;
        return last;
    }

    /**
     * 反转链表的一部分
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head,int m,int n){
        if(m==1){
            return reverseN(head,n);
        }else{
            head.next=reverseBetween(head.next,m-1,n-1);
        }
        return head;
    }

    /**
     * 以k个为一组,反转链表
     * 递归性质
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head,int k){
        ListNode temp=head;
//        不足k个则不反转
        for (int i = 0; i < k; i++) {
            if(temp==null){
                return head;
            }
            temp=temp.next;

        }
        ListNode node = reverseBetween(head, temp);
        head.next=reverseKGroup(temp,k);
        return node;
    }

    /**
     * 反转链表节点[left,right)
     * @param left
     * @param right
     * @return
     */
    private ListNode reverseBetween(ListNode left,ListNode right){
        ListNode curr=left,head=right,next=null;
        while(curr!=right){
            next=curr.next;
            curr.next=head;
            head=curr;
            curr=next;
        }
        return head;
    }


    /**
     * 判断单链表是否回文
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head){
//        第一种 新建一个链表，然后反转，接着判断两条链表是否相同

//        第二种 快慢指针寻找中间节点，然后反转后边的链表，接着判断是否相同
//        缺点在于会破坏链表  需要特殊处理一下
        return palindorme(head);

//        第三种 辅助使用栈判断.其实就是讲链表放入方法栈中，然后再取出来比较
//        left=head;
//        return traverse(head);

    }

    private boolean palindorme(ListNode head){
        ListNode slow,fast;
        slow=fast=head;
        ListNode p,q;
        p=null;
        while(fast!=null&&fast.next!=null){
            p=slow;
            slow=slow.next;
            fast=fast.next.next;
        }
//        链表节点个数奇偶数判断
        if(fast!=null){
            p=slow;
            slow=slow.next;
        }
        ListNode left=head;
        ListNode right = reverse(slow);
        q=right;
        while(right!=null){
            if(right.val!=left.val){
                return false;
            }
            right=right.next;
            left=left.next;
        }
//        将链表还原
        p.next=reverse(q);
        return true;
    }

    ListNode left;
    private boolean traverse(ListNode head){
        if(head==null){
            return true;
        }
        boolean res=traverse(head.next);
        res&=left.val==head.val;
        left=left.next;
        return res;
    }



    public static void main(String[] args) {
        ReverseNode test=new ReverseNode();
        int[] nums={1,2,3,2,1};
        ListNode head = generateListNode(nums);
        CommonUtil.printListNode(head);
        int m=2,n=4;

//        head = test.reverseBetween(head, m, n);
//        CommonUtil.printListNode(head);

//        ListNode listNode = test.reverseKGroup(head, 2);
//        CommonUtil.printListNode(listNode);

        boolean palindrome = test.isPalindrome(head);
        System.out.println(palindrome);

        CommonUtil.printListNode(head);
    }

}
