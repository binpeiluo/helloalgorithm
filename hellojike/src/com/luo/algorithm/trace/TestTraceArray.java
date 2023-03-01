package com.luo.algorithm.trace;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯-数组全排列
 */
public class TestTraceArray {
    List<List<Integer>> res=new ArrayList<>();
    public void permulation(int[] array){
        trace(array,new ArrayList<>());
    }
    private void trace(int[] array, List<Integer> list){
//        判断是否符合结束条件
        if(list.size()==array.length){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i=0;i<array.length;i++){
//            判断是否符合条件
            if(list.contains(array[i]))
                continue;
            list.add(array[i]);
            trace(array,list);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args){
        TestTraceArray test=new TestTraceArray();
        int[] array=new int[]{1,2,3};
        test.permulation(array);
        System.out.println(test.res);
    }
}
