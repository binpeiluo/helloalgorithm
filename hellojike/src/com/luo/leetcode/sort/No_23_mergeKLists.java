package com.luo.leetcode.sort;

import com.luo.util.CommonUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

import static com.luo.util.CommonUtil.*;
/**
 * 23. 合并K个排序链表
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 */
public class No_23_mergeKLists {

    /**
     * 思路一
     *      顺序合并,list1和list2合并到list1,然后list1和list3合并到list1...
     *
     * 时间复杂度:
     *      每次合并后长度都会增长,一共合并k-1次,故 (k-1)*k*n
     *      O(k^2*n)
     *
     * 思路二
     *      归并合并,拆分成两部分合并
     * 时间复杂度:
     *      O()
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return null;
    }

    /**
     * 合并两条链表
     * @param list1
     * @param list2
     * @return
     */
    private ListNode merge(ListNode list1,ListNode list2){
        return null;
    }


    /**
     * 归并算法合并
     * 子问题个数*每个子问题规模= logk*k*n
     * T(n)=2*T(n/2)+n
     * 或者从递归树看,每一层的复杂度为 k*n,一共logk层
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if(lists.length==0)
            return null;
        return divide2(lists,0,lists.length-1);
    }

    private ListNode divide2(ListNode[] lists,int start,int end){
        if(start==end)
            return lists[start];
        int mid=start+(end-start)/2;
        ListNode left = divide2(lists, start, mid);
        ListNode right = divide2(lists, mid + 1, end);
        return merge2(left,right);
    }

    private ListNode merge2(ListNode node1,ListNode node2){
        ListNode head=new ListNode(-1);
        ListNode last=head;
        while(node1!=null&&node2!=null){
            if(node1.val<node2.val){
                last.next=node1;
                last=node1;
                node1=node1.next;
            }else{
                last.next=node2;
                last=node2;
                node2=node2.next;
            }
        }
        if(node1==null)
            last.next=node2;
        if(node2==null)
            last.next=node1;
        return head.next;
    }

    /**
     * 使用优先队列储存每条链表,每次从中取最小节点的一条.
     *
     * 时间复杂度:   O(logk*kn)
     * 空间复杂度:   O(k)
     * @param lists
     * @return
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        int len= lists.length;
        if(len==0)
            return null;
        ListNode head=new ListNode(-1);
        ListNode last=head;
        PriorityQueue<ListNode> queue=new PriorityQueue<>(len,(n1,n2)->n1.val-n2.val);
        for (ListNode node:lists)
            if(node!=null)
                queue.offer(node);
        while(!queue.isEmpty()){
            ListNode poll = queue.poll();
            last.next=poll;
            last=poll;
            poll=poll.next;
            if(poll!=null)
                queue.offer(poll);
        }
        return head.next;
    }

    public static void main(String[] args){
        int[] l1={1,4,5};
        int[] l2={1,3,4};
        int[] l3={2,6};
        ListNode list1 = generateListNode(l1);
        ListNode list2 = generateListNode(l2);
        ListNode list3 = generateListNode(l3);
        ListNode[] lists=new ListNode[3];
        Arrays.asList(list1,list2,list3).toArray(lists);

        No_23_mergeKLists test=new No_23_mergeKLists();
//        ListNode listNode = test.mergeKLists2(lists);
//        CommonUtil.printListNode(listNode);

        ListNode listNode = test.mergeKLists2(lists);
        CommonUtil.printListNode(listNode);
    }

}
