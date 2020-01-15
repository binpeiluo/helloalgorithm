package com.luo.sevendays.day3;

import com.luo.util.CommonUtil;

/**
 * 快速排序
 */
public class SortQuick {
    public void quickSort(int[] a){
        quickSort(a,0,a.length-1);
    }
    private void quickSort(int[] a,int lo,int hi){
        if(lo>=hi)
            return;
        int i=partition(a,lo,a.length-1);
        quickSort(a,lo,i-1);
        quickSort(a,i+1,hi);
    }
    private int partition(int[] a,int lo,int hi){
        int povit=a[lo];
        int m=lo+1;
        for(int i=lo+1;i<=hi;i++){
            if(povit>a[i])
               exch(a,i,m++);
        }
        exch(a,lo,m-1);
        return m-1;
    }
    private void exch(int[] a,int i,int j){
        int temp=a[j];
        a[j]=a[i];
        a[i]=temp;
    }
    public static void main(String[] args){
        SortQuick quick=new SortQuick();
        int[] nums=new int[]{98,5,6,7,4,3,2,1,2};
        quick.quickSort(nums);
        CommonUtil.display(nums);
        for(int i=3;i>0;i--){
            int[] ints = CommonUtil.generateArray(10, 30, true);
            CommonUtil.display(ints);
            quick.quickSort(ints);
            CommonUtil.display(ints);
            CommonUtil.checkOrder(ints);
        }
    }
}
