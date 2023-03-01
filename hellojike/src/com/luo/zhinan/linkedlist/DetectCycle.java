package com.luo.zhinan.linkedlist;

import com.luo.util.CommonUtil;
import com.luo.util.CommonUtil.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 有两条链表, 可能有环也可能无环, 可能相交也可能不相交, 如果相交则返回第一个相交节点
 * 本题拆解成三个子问题
 * 1. 如何判断一个链表是否有环, 如果有, 怎么返回成环的第一个节点
 *      常规的方法需要使用哈希表存储, 不需外额外O(N)空间的话, 可以使用快慢指针找到节点(需要等量关系)
 * 2. 如何判断两条无环链表是否相交, 如果相交则返回第一个相交的节点
 *      无环链表相交的话, 最后一个节点肯定相等.
 *      如果最后节点相等的话, 再寻找第一个相交的节点, 可以通过逆向查找的方式.
 *      相交的部分最长等于较短链表的长度, 那么两条链表均遍历到最后N个节点, 在一起判断节点是否相等
 * 3. 如何判断两条有环链表是否相交, 相交则返回第一个节点
 */
public class DetectCycle {

    /**
     * 对于可能有环形的链表, 返回成环的第一个节点
     * @param head
     * @return
     */
    public static ListNode detectFirstCycleNode(ListNode head){
        if (head==null||head.next==null){
            return head;
        }
        // 可能有环, 也可能无环
        // 方法一 使用set保存遍历的节点
        // 时间复杂度O(N), 空间复杂度O(N)
        ListNode t=head;
        Set<ListNode> set=new HashSet<>();
        while(t!=null){
            if (set.contains(t)){
                return t;
            }
            set.add(t);
            t=t.next;
        }
        return null;
    }

    /**
     * 前边的方法空间复杂度为O(N), 这里优化为O(1)
     * 使用快慢指针, 如果有环的话, 那么快慢两指针一定会相遇
     * 这里假设环外有a个节点, 在环内的b个节点相遇, 剩下环内c个节点. 此时快指针在环内循环了n圈, 而慢指针还没一圈
     * 因为快指针比慢指针快一倍, 如果相遇的话, 那么慢指针在环内肯定不足一圈. 且快指针的步数是慢指针的两倍
     * a+n*(b+c)+b = 2*(a+b)  ==>  a=(n-1)(b+c)+c
     * 所以a的距离等于c的距离加上n-1圈环, 如果此时有一个指针从七点开始和慢指针一起移动, 那么他们一定相遇到环入口
     * @param head
     * @return
     */
    public static ListNode detectFirstCycleNodeV2(ListNode head){
        if (head==null||head.next==null){
            return head;
        }
        // 可能有环, 也可能无环
        ListNode slow=head, fast=head;
        while(fast!=null&&fast.next!=null){
            // 如果fast指针为空 说明没有成环
            slow=slow.next;
            fast=fast.next.next;
            if (slow==fast){
                // 如果快慢指针相遇, 那么说明有环
                fast=head;
                while(fast!=slow){
                    fast=fast.next;
                    slow=slow.next;
                }
                return slow;
            }
        }
        return null;
    }

    /**
     * 获取无环链表相交的第一个节点
     * @param h1
     * @param h2
     * @return
     */
    public static ListNode getCrossNode(ListNode h1, ListNode h2){
        if (h1==null||h2==null){
            return null;
        }
        // 寻找两条无环链表的最后一个节点
        // 通过判断是否相等, 得知是否相交
        ListNode t1=h1, t2=h2;
        int len1=1, len2=1;
        while(t1.next!=null||t2.next!=null){
            if (t1.next!=null){
                t1=t1.next;
                len1++;
            }
            if (t2.next!=null){
                t2=t2.next;
                len2++;
            }
        }
        if (t1!=t2){
            // 不相交
            return null;
        }
        // 相交
        // 较长链表遍历到尾部再一起遍历
        int diff=len2-len1;
        if (len1>len2){
            ListNode t=h2;
            h2=h1;
            h1=t;
            diff=len1-len2;
        }
        t2=h2;
        while(diff>0){
            t2=t2.next;
            diff--;
        }
        t1=h1;
        while(t1!=t2){
            t1=t1.next;
            t2=t2.next;
        }
        return t1;
    }

    public static void main(String[] args) {
        int[] num1={1,3,5,7,9,10,11,12};
        int[] num2={2,3,4,5};
//         判断环形链表的首个入环节点
//        ListNode node = CommonUtil.generateListNodeCycle(num, num.length-1, 4)[0];
//        CommonUtil.printListNodeCycle(node);
//        ListNode cycleFirst = DetectCycle.detectFirstCycleNode(node);
//        System.out.println("链表入口:" +cycleFirst.val);
//        ListNode cycleFirst = DetectCycle.detectFirstCycleNodeV2(node);
//        System.out.println("链表入口:" +cycleFirst.val);

        // 判断无环链表相交  获取第一个相交节点
        ListNode node1 = CommonUtil.generateListNode(num1);
        ListNode common = CommonUtil.generateListNode(num2);
        ListNode node2 = new ListNode(1);
        node2.next=common;
        ListNode n1Tail=node1;
        while(n1Tail.next!=null){
            n1Tail=n1Tail.next;
        }
        n1Tail.next=common;
        ListNode crossNode = DetectCycle.getCrossNode(node1, node2);
        System.out.println("链表相交节点:" +(crossNode==null?"无":crossNode.val));
    }
}
