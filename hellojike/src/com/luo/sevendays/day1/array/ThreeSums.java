package com.luo.sevendays.day1.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 三数之和
 */
public class ThreeSums {
    /**
     * 想使用 hash ,没实现
     * @param nums
     * @return
     */
    public static List<List<Integer>>  threeSum(int[] nums) {
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        HashMap<Integer,List<Integer>> map=new HashMap<Integer,List<Integer>>();
        for(int i=0,ilen=nums.length-2;i<ilen;i++){
            for(int j=i+1,jlen=nums.length-1;j<jlen;j++){
                if(map.containsKey(nums[j])){
                    List<Integer> list=Arrays.asList(nums[j],map.get(nums[j]).get(0),map.get(nums[j]).get(1));
                    res.add(list);
                    System.out.println("add="+list);
                    map.remove(nums[j]);
                }else{
                    int mark=0-nums[i]-nums[j];
                    map.put(mark, Arrays.asList(nums[i],nums[j]));
                }
            }
        }
        return res;
    }

    /**
     * 使用排序加双指针
     * @param nums
     * @return
     */
    public static List<List<Integer>>  threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        for(int i=0,len=nums.length;i<len-2;i++){
            if(nums[i]>0)
                break;
            if(i>0&&nums[i]==nums[i-1])
                continue;
            int lo=i+1;
            int hi=len-1;
            while(lo<hi){
                int sum=nums[i]+nums[lo]+nums[hi];
                if(sum==0){
                    List<Integer> integers = Arrays.asList(nums[i], nums[lo], nums[hi]);
                    res.add(integers);
                    while(lo<hi&&nums[lo]==nums[++lo]);
                    while(lo<hi&&nums[hi]==nums[--hi]);
                }else if(sum>0){
                    while(lo<hi&&nums[hi]==nums[--hi]);
                }else{
                    while(lo<hi&&nums[lo]==nums[++lo]);
                }
            }
        }
        return res;
    }


    public static void main(String[] args){
        int[] input=new int[]{-1,0,1,2,-1,-4};
        List<List<Integer>> lists = threeSum2(input);
        System.out.println(lists);
    }
}
