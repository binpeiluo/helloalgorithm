package com.luo.zhinan.linkedlist;


import com.luo.util.CommonUtil;
import com.luo.util.CommonUtil.ListNode;

/**
 * 反转链表的一部分
 */
public class ReversePartListNode {

    /**
     * 使用普通解法
     * @param h
     * @param from
     * @param to
     * @return
     */
    public static ListNode reversePartListNode(ListNode h, int from, int to){
        if(from<0||to<0||from>to||h==null||h.next==null){
            return h;
        }
        // 遍历第一遍, 校验值合法
        int count=0;
        ListNode curr=h;
        ListNode fromPrev=null, toNext=null;
        while(curr!=null){
            // 首节点为1
            count++;
            // 获取from的前置节点, 反转后需要控制prev.next元素
            if(count+1==from){
                fromPrev=curr;
            }
            // 获取to的后置指针,反转链表后需要需要控制链表的next到该元素
            if(count-1==to){
                toNext=curr;
            }
            curr=curr.next;
        }
        if(to>count){
            return h;
        }

        // 反转中间
        // 反转 (fromPrev -> toNext) 闭区间
        // fromPrev==null代表需要反转头结点了
        // toNext==null代表反转到末尾
        ListNode fromNode=fromPrev==null?h:fromPrev.next;
        curr=fromNode;
        ListNode reverseHead=toNext;
        while(curr!=toNext){
            // 下一个节点
            ListNode next=curr.next;
            // 将当前节点的next指针指向新头部
            curr.next=reverseHead;
            // 更新新链表头部
            reverseHead=curr;
            // 更新curr节点
            curr=next;
        }
        ListNode result=h;
        // 处理前部分
        if(fromPrev!=null){
            fromPrev.next=reverseHead;
        }else{
            result=reverseHead;
        }
        // 处理后部分
        if(toNext!=null){
            fromNode.next=toNext;
        }
        return result;
    }


    public static ListNode reversePartListNodeV2(ListNode h, int from, int to){
        int len=0;
        ListNode node1=h, fPre=null, tPos=null;
        while(node1!=null){
            len++;
            // 前置节点
            fPre=len==from-1?node1:fPre;
            // 后置节点
            tPos=len==to+1?node1:tPos;
            node1=node1.next;
        }
        if(from>to||from<1||to>len){
            return h;
        }
        // 遍历的起点
        node1=fPre==null?h:fPre.next;
        // ?
        ListNode node2=node1.next;
        // 将需要翻转的头结点的next指针指向前边找到的后置节点
        node1.next=tPos;
        ListNode next=null;
        // 遍历剩下的节点
        while (node2!=tPos){
            // 下一个节点
            next=node2.next;
            // 当前节点的next指向新链表的头结点
            node2.next=node1;
            // 更新新链表的头指针
            node1=node2;
            // 更新下一个节点
            node2=next;
        }
        // 如果from的前置节点不为空, 那么就返回原来的头结点
        if(fPre!=null){
            // 返回之前需要将前置节点的next更新为新链表头结点
            fPre.next=node1;
            return h;
        }
        // 否则返回新节点
        return node1;

    }

    public static void main(String[] args) {

        int[] nums={1,2,3,4,5};
        int from=1, to=3;
        ListNode h = CommonUtil.generateListNode(nums);
        CommonUtil.printListNode(h);
        h = ReversePartListNode.reversePartListNode(h, from, to);
        CommonUtil.printListNode(h);
    }
}
