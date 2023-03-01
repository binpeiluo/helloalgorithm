package com.luo.leetcode.sort;


import com.luo.util.CommonUtil;

import java.util.HashSet;
import java.util.Set;

/*
    349. 两个数组的交集
    给定两个数组，编写一个函数来计算它们的交集。
        示例 1:
        输入: nums1 = [1,2,2,1], nums2 = [2,2]
        输出: [2]
        示例 2:
        输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
        输出: [9,4]
        说明:
        输出结果中的每个元素一定是唯一的。
        我们可以不考虑输出结果的顺序。
*/
public class No349_Intersection {

    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1.length==0||nums2.length==0)
            return nums1.length==0?nums1:nums2;
        Set<Integer> set=new HashSet<>();
        Set<Integer> result=new HashSet<>();
        for(int num:nums1){
            set.add(num);
        }
        for(int num:nums2){
            if(set.remove(Integer.valueOf(num)))
                result.add(num);
        }
        Integer[] res=result.toArray(new Integer[result.size()]);
        int[] r=new int[result.size()];
        for(int i=0,size=res.length;i<size;i++){
            r[i]=res[i];
        }
        return r;
    }

    public static void main(String[] args){
        No349_Intersection test=new No349_Intersection();
        int[] nums1={4,9,5};
        int[] nums2={9,4,9,8,4};
        int[] intersection = test.intersection(nums1, nums2);
        CommonUtil.display(intersection);
    }
}
