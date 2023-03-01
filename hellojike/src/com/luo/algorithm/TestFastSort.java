package com.luo.algorithm;

import com.luo.util.CommonUtil;

/**
 * 测试另一种快速排序
 */
public class TestFastSort {

    private void fastSort(int[] a,int lo,int hi){
        if(hi<=lo)
            return;
        int i=partition(a,lo,hi);
        System.out.println("-----start");
        CommonUtil.display(a);
        System.out.println("-----end");
        fastSort(a,lo,i-1);
        fastSort(a,i+1,hi);
    }
    private int partition(int[] a,int lo,int hi){
        int pivot=a[hi];
        int i=lo;
        for(int j=lo;j<hi;j++){
            if(a[j]<pivot){
                CommonUtil.exch(a,i,j);
                i++;
            }
        }
        CommonUtil.exch(a,hi,i);
        return i;
    }

    public static void main(String[] args){
        TestFastSort test=new TestFastSort();
//        int[] input=new int[]{10,4,6,3,7,3,4,8,9,5};
//        int[] input=new int[]{4,3,3,4};
        int[] input=CommonUtil.generateArray(10,10);
        CommonUtil.display(input);
        test.fastSort(input,0,input.length-1);
        CommonUtil.display(input);
    }
}
