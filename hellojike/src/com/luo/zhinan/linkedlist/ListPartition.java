package com.luo.zhinan.linkedlist;

import com.luo.util.CommonUtil;
import com.luo.util.CommonUtil.ListNode;

import java.util.ArrayList;

/**
 * 给定一个链表头,以及pivot数值, 将链表重新排序为小于,等于,大于pivot的顺序, 要求内部顺序不变
 */
public class ListPartition {

    public static ListNode listPartition(ListNode head, int pivot){
        if(head==null||head.next==null){
            return head;
        }

        // 小于
        ListNode sH=null,sT=null;
        // 等于
        ListNode eH=null,eT=null;
        // 大于
        ListNode gH=null,gT=null;
        while(head!=null){
            ListNode next=head.next;
            head.next=null;
            if(head.val<pivot){
                if (sH!=null){
                    sT.next=head;
                    sT=head;
                }else{
                    sH=head;
                    sT=sH;
                }
            }else if(head.val==pivot){
                if (eH!=null){
                    eT.next=head;
                    eT=head;
                }else{
                    eH=head;
                    eT=eH;
                }
            }else{
                if (gH!=null){
                    gT.next=head;
                    gT=head;
                }else{
                    gH=head;
                    gT=gH;
                }
            }
            head=next;
        }
        // 重新拼装链表
        ListNode result=null;
        // 分别连起来是在容易出错
        /*
        if(sH!=null){
            // 小于链表有值
            result=sH;
            if(eH!=null){
                // 等于链表有值, 将小于和等于链表连起来
                sT.next=eH;
                if (gH!=null){
                    eT.next=gH;
                }
            }else{
                // 等于链表没值, 判断大于链表是否有值
                if (gH!=null){
                    // 大于链表有值, 将小于和大于链表连起来
                    sT.next=gH;
                }
            }
        }else{
            // 小于链表没值
            if(eH!=null){
                // 等于链表有值
                result=eH;
                if (gH!=null){
                    // 大于链表有值, 那么将等于和大于链表连起来
                    eT.next=gH;
                }
            }else{
                // 等于链表没有值, 全部都是大于链表
                result=gH;
            }
        }*/

        // 使用辅助数组串起来
        /*
        ListNode[][] help=new ListNode[3][2];
        int index=0;
        if (sH!=null){
            help[index++]= new ListNode[]{sH, sT};
        }
        if (eH!=null){
            help[index++]= new ListNode[]{eH, eT};
        }
        if (gH!=null){
            help[index++]= new ListNode[]{gH, gT};
        }
        for (int i = 1; i < index; i++) {
            help[i-1][1].next=help[i][0];
        }
        result=help[0][0];
        */

        // 优雅解法
        if(sT!=null){
            // 小于链表有值, 则直接将小于链表和等于链表连起来
            sT.next=eH;
            // 接下来需要处理大于链表连接的问题
            // 如果等于链表不为空, 那么是将等于链表末尾和大于链表末尾连起来
            // 如果等于链表为空, 那么是将小于链表末尾和大于链表末尾连起来
            eT=eT==null?sT:eT;
        }
        // 处理剩下的连接
        if (eT!=null){
            eT.next=gH;
        }
        result=sH!=null?sH:eH!=null?eH:gH;
        return result;
    }

    public static void main(String[] args) {
        int[] nums={5,4,6,3,3,4,5};
//        int[] nums={5,4,6,1,1,4,5};
//        int[] nums={2,0,1,1,1,0,0};
        ListNode h = CommonUtil.generateListNode(nums);
        CommonUtil.printListNode(h);
        h = ListPartition.listPartition(h, 3);
        CommonUtil.printListNode(h);
    }
}
