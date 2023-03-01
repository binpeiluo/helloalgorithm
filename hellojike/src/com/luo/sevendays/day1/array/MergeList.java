package com.luo.sevendays.day1.array;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 将两个有序数组合并成一个有序数组
 */
public class MergeList {
    static <T extends Comparable> Comparable[] merge(T[] list1,T[] list2){
        if(list1==null||list1.length==0)
            return list2;
        if(list2==null||list2.length==0)
            return list1;
        Comparable[] res= (Comparable[]) Array.newInstance(Comparable.class,list1.length+list2.length);
        int i=0,j=0;
        for(int k=0;k<res.length;k++){
            if(i>=list1.length)
                res[k]=list2[j++];
            else if(j>=list2.length)
                res[k]=list1[i++];
            else if(list1[i].compareTo(list2[j])>0)
                res[k]=list2[j++];
            else
                res[k]=list1[i++];
        }
        return res;
    }
    public static void disPlay(Comparable[] list) {
        for(int j = 0; j < list.length; j ++) {
            System.out.print(list[j] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        Integer[] list1=new Integer[5];
        for(int i=0;i<list1.length;i++){
            list1[i]=Integer.valueOf(2*i);
        }
        Integer[] list2=new Integer[5];
        for(int i=0;i<list1.length;i++){
            list2[i]=Integer.valueOf(i);
        }
        Comparable[] merge = merge(list1, list2);
        disPlay(merge);
    }


}
