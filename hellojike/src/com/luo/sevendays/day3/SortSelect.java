package com.luo.sevendays.day3;


import com.luo.util.CommonUtil;

/**
 * 选择排序
 */
public class SortSelect {
    public void selectSort(int[] a){
        for(int i=0;i<a.length;i++){
            int min=i;
            for(int j=i;j<a.length;j++){
                if(a[j]<a[min])
                    min=j;
            }
            CommonUtil.exch(a,min,i);
        }
    }

    public static void main(String[] args){
        SortSelect select=new SortSelect();
        for(int i=3;i>0;i--){
            int[] ints = CommonUtil.generateArray(10, 20, true);
            CommonUtil.display(ints);
            select.selectSort(ints);
            CommonUtil.display(ints);
            CommonUtil.checkOrder(ints);
        }
    }
}
