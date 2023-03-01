package com.luo.zhinan.linkedlist;

import com.luo.util.CommonUtil;
import com.luo.util.CommonUtil.ListNode;

/**
 * 删除单向链表和双向链表倒数第N个节点
 */
public class DeleteLastNNode {

    public static ListNode removeLathNthNode(ListNode h, int n){
        ListNode t=h;
        // 遍历到最后一个节点
        while(t!=null){
            t=t.next;
            n--;
        }
        if(n==0){
            // 如果n等于0 说明需要删除头结点
            return h.next;
        }else{
            // 否则删除 第len
            t=h;
            while(++n<0){
                t=t.next;
            }
            t.next=t.next.next;
        }
        return h;
    }


    public static void main(String[] args) {
        int[] num={1,2,3,4,5};
        ListNode node = CommonUtil.generateListNode(num);
        CommonUtil.printListNode(node);
        node = DeleteLastNNode.removeLathNthNode(node, 4);
        CommonUtil.printListNode(node);

    }
}
