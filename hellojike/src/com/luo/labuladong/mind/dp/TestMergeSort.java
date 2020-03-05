package com.luo.labuladong.mind.dp;

import com.luo.util.CommonUtil;

/**
 * 递归思想
 *      使用递归思想思考合并排序
 *      要排序一个数组
 *          首先将数组分成两部分,将左边排序,然后将右边排序
 */
public class TestMergeSort {

    /**
     * 归并排序处理排序
     *
     * 步骤是
     *      定义函数 mergeSort 合并数组
     *      mergeSort(一个数组):
     *          if(结束条件)    return
     *          mergeSort(左边数组);
     *          mergeSort(右边数组);
     *          合并两个数组;
     *
     * @param a
     */
    public void mergeSort(int[] a){
        int len=a.length;
        aux=new int[len];
        mergeSort(a,0,len-1);
    }

    private int[] aux;

    private void mergeSort(int[] a,int lo,int hi){
        if(lo==hi)
            return;
        int mid=lo+(hi-lo)/2;
        mergeSort(a,lo,mid);
        mergeSort(a,mid+1,hi);
        merge(a,lo,mid,hi);
    }

    private void merge(int[] a,int lo,int mid,int hi){
        for (int i = lo; i <= hi; i++) {
            aux[i]=a[i];
        }
        int left=lo,right=mid+1;
        for (int i = lo; i <=hi; i++) {
            if(left==mid+1)
                a[i]=aux[right++];
            else if(right==hi+1)
                a[i]=aux[left++];
            else{
                if(aux[left]<=aux[right])
                    a[i]=aux[left++];
                else
                    a[i]=aux[right++];
            }
        }
    }


    public static void main(String[] args){
        TestMergeSort test=new TestMergeSort();
        int[] nums={12,3,4,5,6,7,7,2,4,0,10};
        test.mergeSort(nums);
        CommonUtil.display(nums);
    }
}
