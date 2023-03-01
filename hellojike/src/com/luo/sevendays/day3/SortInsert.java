package com.luo.sevendays.day3;

import com.luo.util.CommonUtil;

/**
 * 插入排序
 */
public class SortInsert {
    public void insertSort(int[] a){
        if(a==null||a.length==1)
            return;
        for(int i=1;i<a.length;i++){
            int j=i;
            while(j>0&&a[j]<a[j-1]){
                CommonUtil.exch(a,j,j-1);
                j--;
            }
        }
    }

    public static void main(String[] args){
        SortInsert insert=new SortInsert();
        for(int i=3;i>0;i--){
            int[] ints = CommonUtil.generateArray(10, 20, true);
            CommonUtil.display(ints);
            insert.insertSort(ints);
            CommonUtil.display(ints);
            CommonUtil.checkOrder(ints);
        }

    }
}
