package com.luo.algorithm.recurse;

/**
 * 递归之反转链表一部分
 */
public class RecurseList {

    /**
     * 反转整个链表
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head){
//        1 明确定义递归函数的处理
//        输入一个链表节点,反转链表并返回新的链表头节点
//        base case
        if(head==null||head.next==null)
            return head;
//        根据函数,reverse 会返回新的头结点
        ListNode newHead=reverse(head.next);
//        需要处理,当前头结点以及子问题的头结点关系问题
        head.next.next=head;
//        当前头结点现在变成尾节点,需要将后继置为空
        head.next=null;
        return newHead;
    }


//    end个节点的后驱节点
    private ListNode successor=null;
    /**
     * 反转链表前end个节点,并返回新的头节点
     * @param head
     * @param end
     * @return
     */
    public ListNode reverseFirst(ListNode head,int end){
//          需要使用一个临时节点保存,end个节点后的后继节点
//          end个节点之后的不需要处理
        if(end==1){
            successor=head.next;
            return head;
        }
//        从head.next开始计算,反转end-1个节点
        ListNode newHead=reverseFirst(head.next,end-1);
//        需要处理当前头结点与子问题头结点之间的关系
        head.next.next=head;
//        当前头结点后继需要指定后第end个节点
        head.next=successor;
        return newHead;
    }

    /**
     * 反转链表第start~end之间的节点,并返回新的头结点
     * @param head
     * @param start
     * @param end
     * @return
     */
    public ListNode reverseN(ListNode head,int start,int end){
        if(start==1){
            return reverseFirst(head,end);
        }
        head.next=reverseN(head.next,start-1,end-1);
        return head;
    }


    private void printListNode(ListNode node){
        if(node==null){
            System.out.println("\t->null");
        }else{
            System.out.print("\t"+node.val);
            printListNode(node.next);
        }

    }

    public static void main(String[] args){
        RecurseList test=new RecurseList();
        ListNode head=new ListNode(0);
        ListNode temp=head;
        for(int i=1;i<6;i++){
            ListNode t=new ListNode(i);
            temp.next=t;
            temp=t;
        }
        test.printListNode(head);

        head = test.reverse(head);
        test.printListNode(head);

        head = test.reverseFirst(head, 2);
        test.printListNode(head);

        head = test.reverseN(head, 2, 5);
        test.printListNode(head);

    }
}
// 单链表节点的结构
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
