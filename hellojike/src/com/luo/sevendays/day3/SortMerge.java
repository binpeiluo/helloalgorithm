package com.luo.sevendays.day3;

import com.luo.util.CommonUtil;

/**
 * 归并排序
 */
public class SortMerge {
    int[] temp;
    private void merge(int[] a,int lo,int mid,int hi){
        System.arraycopy(a,lo,temp,lo,hi-lo+1);
        int m=lo,n=mid+1;
        for(int i=lo;i<=hi;i++){
            if(m>mid)
                a[i]=temp[n++];
            else if(n>hi)
                a[i]=temp[m++];
            else if(temp[m]<temp[n])
                a[i]=temp[m++];
            else
                a[i]=temp[n++];
        }
    }
    public void mergeSort(int[] a){
        temp=new int[a.length];
        mergeSort(a,0,a.length-1);
    }
    private void mergeSort(int[] a,int lo,int hi){
        if(lo==hi)
            return;
        int mid=lo+(hi-lo)/2;
        mergeSort(a,lo,mid);
        mergeSort(a,mid+1,hi);
        merge(a,lo,mid,hi);
    }
    public static void main(String[] args){
        SortMerge merge=new SortMerge();
        int[] nums=new int[]{98,5,6,7,4,3,2,1,2};
        merge.mergeSort(nums);
        CommonUtil.display(nums);
    }
}
