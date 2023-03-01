package com.luo.algorithm;

import com.luo.util.CommonUtil;

/**
 * 堆排序
 */
public class TestHeapSort {

    private void buildHeap(int[] a,int n){
        for(int i=n/2;i>=1;i--){
            heapify(a,n,i);
        }
    }

    /**
     * 向下堆化
     * @param a
     * @param n
     * @param i
     */
    private void heapify(int[] a,int n,int i){
        while(true){
            if(2*i>n)
                return;
            int maxP=i;
            if(2*i<=n&&a[maxP]<a[2*i])
                maxP=2*i;
            if(2*i+1<=n&&a[maxP]<a[2*i+1])
                maxP=2*i+1;
            if(maxP==i)   //这里时break,不是continue
                break;
            CommonUtil.exch(a,maxP,i);
            i=maxP;
        }
    }

    public void sort(int[] a,int n){
        buildHeap(a,n);
        for(int i=n;i>1;i--){
            CommonUtil.exch(a,1,i);      //将当前对顶交换到最后边
            heapify(a,i-1,1);         //交换完后无序数组长度-1,需要调整边界值
        }
    }

    public static void main(String[] args){
        TestHeapSort heapSort=new TestHeapSort();
        int[] ints = CommonUtil.generateArray(10, 20, true);
//        int[] ints=new int[]{0,1,-12,2,5,15,-1,16,19,14};
        ints[0]=0;
        CommonUtil.display(ints);
        heapSort.sort(ints,ints.length-1);
        CommonUtil.display(ints);

    }
}
