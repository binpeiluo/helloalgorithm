package com.luo.leetcode.linkednode;

import com.luo.util.CommonUtil;

import java.util.HashSet;
import java.util.Set;

import static com.luo.util.CommonUtil.*;

/**
 * 面试题 02.01. 移除重复节点
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 *
 * 示例1:
 *
 *  输入：[1, 2, 3, 3, 2, 1]
 *  输出：[1, 2, 3]
 * 示例2:
 *
 *  输入：[1, 1, 1, 1, 2]
 *  输出：[1, 2]
 * 提示：
 *
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 * 进阶：
 *
 * 如果不得使用临时缓冲区，该怎么解决？
 *
 */
public class No_m_0201_removeDuplicateNodes {

    /**
     * 使用空间换时间想法
     * 使用set储存没有重复的元素,在遍历链表时以此跳过重复元素
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        if(head==null||head.next==null)
            return head;
        Set<Integer> set=new HashSet<>();
        ListNode help=new ListNode(-1);
        help.next=head;
        set.add(head.val);
        while(head.next!=null){
            if(set.contains(head.next.val)){
                head.next=head.next.next;
            }else{
                set.add(head.next.val);
                head=head.next;
            }
        }
        return help.next;
    }

    public ListNode removeDuplicateNodes2(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
//        当前要保留的节点判断是否在前边出现过
        return null;
    }

    public ListNode removeDuplicateNodes3(ListNode head) {
//        当前需要保留的节点
        ListNode ob = head;
        while (ob != null) {
//            在需要保留的节点之后将相同值的节点过滤掉
            ListNode oc = ob;
            while (oc.next != null) {
                if (oc.next.val == ob.val) {
                    oc.next = oc.next.next;
                } else {
                    oc = oc.next;
                }
            }
            ob = ob.next;
        }
        return head;
    }

    public static void main(String[] args){
        No_m_0201_removeDuplicateNodes test=new No_m_0201_removeDuplicateNodes();

        int[] nums={1, 2, 3, 3, 2, 1};
//        int[] nums={1, 1, 1, 1, 2};
        ListNode node = CommonUtil.generateListNode(nums);
        CommonUtil.printListNode(node);

//        ListNode node1 = test.removeDuplicateNodes(node);
//        CommonUtil.printListNode(node1);

        ListNode node1 = test.removeDuplicateNodes3(node);
        CommonUtil.printListNode(node1);


    }
}
