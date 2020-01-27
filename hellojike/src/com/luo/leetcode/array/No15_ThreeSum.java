package com.luo.leetcode.array;

import java.util.*;

/*
    15. 三数之和
    给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
        注意：答案中不可以包含重复的三元组。
        示例：
        给定数组 nums = [-1, 0, 1, 2, -1, -4]，
        满足要求的三元组集合为：
        [
        [-1, 0, 1],
        [-1, -1, 2]
        ]
*/
public class No15_ThreeSum {

    /**
     * 思路:
     *      使用三个指针.lo指针指向数值最低元素的角标,然后在后边的元素使用双指针方法查找 0-array[lo] 的两个元素和
     *      注意角标越界问题
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        if(nums.length<3)
            return res;
        Arrays.sort(nums);
        for (int lo = 0; lo < nums.length - 2; lo++) {
//            优化,当发现最小数值的元素大于0,那么不需要查找了
            if(nums[lo]>0)
                break;
//            去重
            if(lo>0&&nums[lo]==nums[lo-1])
                continue;
//            初始赋值mid,hi角标
            int mid=lo+1,hi=nums.length-1;
//            细品mid<hi这一判断条件,已经防止角标越界异常了
            while(mid<hi){
                int sum=nums[lo]+nums[mid]+nums[hi];
                if(sum<0)
                    mid++;
                else if(sum>0)
                    hi--;
                else{
                    res.add(Arrays.asList(nums[lo],nums[mid],nums[hi]));
//                    去重
                    while(mid<hi&&nums[mid+1]==nums[mid])
                        mid++;
                    while(mid<hi&&nums[hi-1]==nums[hi])
                        hi--;
//                    去重后还需进行递减,不然可能造成空循环
                    mid++;
                    hi--;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum2(int[] nums){
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if(nums == null || len < 3) return ans;
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len ; i++) {
            // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if(nums[i] > 0) break;
            // 去重
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int L = i+1;
            int R = len-1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) L++; // 去重
                    while (L<R && nums[R] == nums[R-1]) R--; // 去重
                    L++;
                    R--;
                }
                else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return ans;
    }

    /**
     *  思考:
     *      就像no1.题在数组中查找两个和等于target的元素一样.  使用map保存缺省的元素(key)与已经存在的元素(value)
     *      在本题中,需要返回三个元素组合.可以适应map保存缺省的元素(key)与已经存在的两个元素(value)
     *
     *      但是需要解决的是三元素重复的问题
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum3(int[] nums){
        List<List<Integer>> result=new ArrayList<>();
        if(nums==null||nums.length<3)
            return result;
        Arrays.sort(nums);
        int len=nums.length;
        Map<Integer,List<Integer>> tmpMap=new HashMap<>();
        for(int i=0;i<len-2;i++){
            for(int j=i+1;j<len-1;j++){
//                查询是否有缺少当前元素的组合,有的话则在该两个元素添加上该元素即可.
//                并且在组成新组合之后需要将该组合从临时map中删除
                if(tmpMap.containsKey(nums[j])){
                    if(tmpMap.get(nums[j]).size()==3)
                        continue;
                    else{
                        List<Integer> list = tmpMap.get(nums[j]);
                        list.add(nums[j]);
                        tmpMap.put(nums[j],list);
                        result.add(list);
                    }

                }else{
//                    当前的两个元素组成一个组合,在map中记录缺少的一个元素
                    Integer t=0-nums[i]-nums[j];
                    List<Integer> list=new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    tmpMap.put(t,list);
                }
            }
        }
        return result;
    }



    public static void main(String[] args){
        No15_ThreeSum test=new No15_ThreeSum();
        int[] nums={2,-3,0,-2,-5,-5,-4,1,2,-2,2,0,2,-4};
        List<List<Integer>> lists = test.threeSum3(nums);
        System.out.println(lists);
    }
}
