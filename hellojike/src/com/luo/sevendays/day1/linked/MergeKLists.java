package com.luo.sevendays.day1.linked;

public class MergeKLists {
    /**
     * Definition for singly-linked list.
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            return "[val="+val+"]";
        }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        return solve(lists,0,lists.length-1);
    }

    public ListNode solve(ListNode[] lists,int lo,int hi){
        if(lo==hi)
            return lists[lo];
        int mid=(lo+hi)>>2;
        ListNode left=solve(lists,lo,mid);
        ListNode right=solve(lists,mid+1,hi);
        return merge(left,right);
    }

    public ListNode merge(ListNode l1,ListNode l2){
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
        if(l1.val<l2.val){
            l1.next=merge(l1.next,l2);
            return l1;
        }else{
            l2.next=merge(l1,l2.next);
            return l2;
        }
    }

    public void display(ListNode node){
        ListNode temp=node;
        System.out.print("链表");
        while(temp!=null){
            System.out.print("-->"+temp.val);
            temp=temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args){
        ListNode l7=new ListNode(7);
        ListNode l5=new ListNode(5);
        ListNode l3=new ListNode(3);
        ListNode l1=new ListNode(1);
        l1.next=l3;
        l3.next=l5;
        l5.next=l7;

        ListNode r8=new ListNode(8);
        ListNode r6=new ListNode(6);
        ListNode r4=new ListNode(4);
        ListNode r2=new ListNode(2);
        r2.next=r4;
        r4.next=r6;
        r6.next=r8;

        MergeKLists test=new MergeKLists();
        test.display(l1);
        test.display(r2);
        ListNode res=test.merge(l1,r2);
        test.display(res);
    }
}
