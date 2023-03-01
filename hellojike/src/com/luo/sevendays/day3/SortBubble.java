package com.luo.sevendays.day3;

import com.luo.util.CommonUtil;

/**
 * 冒泡排序
 */
public class SortBubble {
    public void bubbleSort(int[] a){
        if(a==null||a.length==1)
            return;
        for(int i=0;i<a.length-1;i++){
            for(int j=0;j<a.length-1-i;j++){
                if(a[j]>a[j+1])
                    CommonUtil.exch(a,j,j+1);
            }
        }
    }
    public static void main(String[] args){
        SortBubble bubble=new SortBubble();
        for(int i=3;i>0;i--){
            int[] ints = CommonUtil.generateArray(10, 20, true);
            CommonUtil.display(ints);
            bubble.bubbleSort(ints);
            CommonUtil.display(ints);
            CommonUtil.checkOrder(ints);
        }
    }
}
