package com.luo.leetcode.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
    179. 最大数
    给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
        示例 1:
        输入: [10,2]
        输出: 210
        示例 2:
        输入: [3,30,34,5,9]
        输出: 9534330
        说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
*/
public class No179_LargestNumber {

    /**
     * 思路:
     *      构建的这个数字,高位肯定越大越好.
     *      利用字符串比较的性质,自定义一个comparator
     *
     *      值得注意的是在处理以{0}+ 字符串起始的情况
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        List<String> list=new ArrayList<>();
        for(int num:nums){
            list.add(String.valueOf(num));
        }
        list.sort(new StringComparator());
        String collect = list.stream().collect(Collectors.joining());
        while(!"0".equals(collect)&&collect.startsWith("0")){
            collect=collect.substring(1);
        }

        return collect;
    }

    static class StringComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            String a=o1+o2;
            String b=o2+o1;
            return b.compareTo(a);
        }
    }


    public static void main(String[] args){
        No179_LargestNumber test=new No179_LargestNumber();
        int[] nums={10,2};
        String s = test.largestNumber(nums);
        System.out.println("result=="+s);
    }
}
