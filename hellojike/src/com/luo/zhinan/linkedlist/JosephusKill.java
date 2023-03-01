package com.luo.zhinan.linkedlist;

import com.luo.util.CommonUtil;
import com.luo.util.CommonUtil.ListNode;

/**
 * 环形单链表的约瑟夫问题
 */
public class JosephusKill {

    public static ListNode josephusKill(ListNode h, int m){
        // 遍历节点
        ListNode t=h, prev=null;
        // 循环结束条件为单节点循环
        // 如果m可能等于1的话, 需要找到节点的前置节点, 所以需要预先遍历一边, 找到第一个元素的前置节点
        while(t.next!=h){
            prev=t;
            t=t.next;
        }
        prev=t;
        t=t.next;

        while(t.next!=t){
            // 从1遍历到m
            int loop=1;
            while(loop<m){
                prev=t;
                t=t.next;
                loop++;
            }
            // 需要跳过当前节点 t
            prev.next=t.next;
            t=t.next;
        }
        return t;
    }


    /**
     * 时间复杂度为O(M*N)
     * @param head
     * @param m
     * @return
     */
    public static ListNode josephusKillV2(ListNode head, int m){
        // 遍历节点 找到尾结点
        ListNode last=head;
        while(last.next!=head){
            last=last.next;
        }
        int count=0;
        // 结束条件是last=head, 那么就剩下一个节点
        while(last!=head){
            // 如果还没到m的话, 那么久last和head指针都移动
            // 如果需要删除某个节点的话, 就使用last.next跳过head节点. 次数last不需要更新, 只需要更新head节点
            if(++count==m){
                last.next=head.next;
                count=0;
            }else{
                last=last.next;
            }
            head=last.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] nums={1};
        ListNode[] allNodes = CommonUtil.generateListNodeCycle(nums, nums.length - 1, 0);
        ListNode h=allNodes[0];
        CommonUtil.printListNodeCycle(h);
        h=JosephusKill.josephusKill(h, 1);
        CommonUtil.printListNodeCycle(h);

    }
}
