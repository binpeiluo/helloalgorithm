package com.luo.leetcode.linkednode;

import com.luo.util.CommonUtil;

import java.util.Comparator;
import java.util.PriorityQueue;

import static com.luo.util.CommonUtil.*;
/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 */
public class No23_mergeKLists {

    /**
     * 使用合并排序
     * 由于使用的是递归方式合并两条链表.
     * 所以 f(n)=f(n/2)+f(n/2)+n
     * 时间复杂度为   nlogn   感觉不太对. 不对,每一层的合并复杂度为n,但是需要额外空间
     * 空间复杂度为:  n
     *
     * 如果不使用递归方式呢
     * 时间复杂度为 nlogn
     * 空间复杂度为 n
     *
     * 假设链表数组长度为k,每条链表长度为n.
     * 想象成一颗二叉树,每一层切分两个子节点,第一层合并k/2个数组,每一层的成本为 O(2*n)
     *  所以时间复杂度为 层数*每一层的代价=O(nlogk)
     * 空间复杂度:这里递归栈的深度为 O(logk) 所以空间复杂度为logk
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null||lists.length==0){
            return null;
        }
        return mergeKLists(lists,0,lists.length-1);
    }

    private ListNode mergeKLists(ListNode[] lists,int left,int right){
        if(left>=right){
            return lists[right];
        }
        int mid=left+(right-left)/2;
        ListNode leftList = mergeKLists(lists, left, mid);
        ListNode rightList = mergeKLists(lists, mid + 1, right);
//        ListNode result = merge(leftList, rightList);
        ListNode result = merge2(leftList, rightList);
        return result;
    }

    /**
     * 使用递归方式
     * @param left
     * @param right
     * @return
     */
    private ListNode merge(ListNode left,ListNode right){
        if(left==null){
            return right;
        }
        if(right==null){
            return left;
        }
        if(left.val<right.val){
            left.next=merge(left.next,right);
            return left;
        }else{
            right.next=merge(left,right.next);
            return right;
        }
    }

    private ListNode merge2(ListNode left,ListNode right){
        if(left==null){
            return right;
        }
        if(right==null){
            return left;
        }
        ListNode result,tail;
        result=tail=null;
        while(left!=null&&right!=null){
            ListNode tmp=null;
            if(left.val<right.val){
                tmp=left;
                left=left.next;
            }else{
                tmp=right;
                right=right.next;
            }
            if(result==null){
                result=tail=tmp;
            }else{
                tail.next=tmp;
                tail=tmp;
            }
        }
        if(left!=null){
            tail.next=left;
        }
        if(right!=null){
            tail.next=right;
        }
        return result;

    }

    /**
     * 使用优先队列
     * 构造优先队列的复杂度为 klogk k为链表长度
     * 所以 时间复杂度为 n*klogk
     * 空间复杂度为 k
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if(lists==null||lists.length==0){
            return null;
        }
        PriorityQueue<ListNode> queue=new PriorityQueue<>((l1,l2)->l1.val-l2.val);
        for (int i = 0; i < lists.length; i++) {
            if(lists[i]!=null){
                queue.offer(lists[i]);
            }
        }
        ListNode result,tail;
        result=tail=null;
        while(!queue.isEmpty()){
            ListNode min = queue.poll();
            ListNode next=min.next;
            min.next=null;
            if(result==null){
                result=tail=min;
            }else{
                tail.next=min;
                tail=min;
            }
            if(next!=null){
                queue.offer(next);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        No23_mergeKLists test=new No23_mergeKLists();

        int[] a1={1,4,5};
        int[] a2={1,3,4};
        int[] a3={2,6};
        ListNode node1 = generateListNode(a1);
        ListNode node2 = generateListNode(a2);
        ListNode node3 = generateListNode(a3);
        ListNode[] array={node1,node2,node3};

//        ListNode node = test.mergeKLists(array);
        ListNode node = test.mergeKLists2(array);
        CommonUtil.printListNode(node);
    }
}
