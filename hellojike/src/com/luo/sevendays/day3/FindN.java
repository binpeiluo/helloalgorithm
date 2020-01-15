package com.luo.sevendays.day3;

import com.luo.util.CommonUtil;

/**
 * 寻找数组中第n个小的元素
 */
public class FindN {
    public int findTopN(int[] a,int n){
        int i = partition(a, 0, a.length - 1, n);
        return a[i];
    }
    private int partition(int[] a,int lo,int hi,int n){
        int i = partition(a, lo, hi);
        if(i==n)
            return i;
        else if(i>n)
            return partition(a,lo,i-1,n);
        else
            return partition(a,i+1,hi,n);
    }
    private int partition(int[] a,int lo,int hi){
        if(lo>=hi)
            return lo;
        int povit=lo;
        int m=lo+1;
        for(int i=lo+1;i<=hi;i++){
            if(a[povit]>a[i])
                CommonUtil.exch(a,i,m++);
        }
        CommonUtil.exch(a,povit,m-1);
        return m-1;
    }
    public static void main(String[] args){
        FindN test=new FindN();
//        int[] ints = CommonUtil.generateArray(10, 20, true);
        int[] ints=new int[]{-10,-12,14,-15,-1,-19,0,0,-14,9};
        CommonUtil.display(ints);
        int topN = test.findTopN(ints, 9);
        System.out.println("the ele is "+ topN);
        SortInsert insert=new SortInsert();
        insert.insertSort(ints);
        CommonUtil.display(ints);
    }
}
