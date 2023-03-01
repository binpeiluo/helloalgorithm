package com.luo.zhinan.linkedlist;

import com.luo.util.CommonUtil;
import com.luo.util.CommonUtil.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个有序链表头, 打印两条的公共部分
 */
public class PublicNode {

    // 使用两个指针遍历链表
    public static List<Integer> getPublicNode(ListNode h1, ListNode h2){
        List<Integer> result=new ArrayList<>();
        ListNode t1=h1, t2=h2;
        while(t1!=null&&t2!=null){
            if(t1.val==t2.val){
                result.add(t1.val);
                t1=t1.next;
                t2=t2.next;
            }else if(t1.val<t2.val){
                t1=t1.next;
            }else{
                t2=t2.next;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] n1={1,3,3,5,7,9};
        int[] n2={1,2,3,3,4,8,9,10};
        ListNode h1 = CommonUtil.generateListNode(n1);
        ListNode h2 = CommonUtil.generateListNode(n2);
        List<Integer> result = PublicNode.getPublicNode(h1, h2);
        System.out.println(result);
    }
}
