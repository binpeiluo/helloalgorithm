package com.luo.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    18. 四数之和
    给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
        注意：
        答案中不可以包含重复的四元组。
        示例：
        给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
        满足要求的四元组集合为：
        [
            [-1,  0, 0, 1],
            [-2, -1, 1, 2],
            [-2,  0, 0, 2]
        ]
*/

public class No18_FourSum {

    /**
     * 思路:
     *      使用四个指针,(i,j,p,q).两层for循环加上一次while条件
     *
     *      值得注意的是如何避免产生相同的四元数组
     *      此方法在执行耗时以及消耗内存为 62 ms	43.5 MB
     *      应该可以再优化
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result=new ArrayList<>();
        if(nums==null||nums.length<4)
            return result;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i+1; j < nums.length-2; j++) {
                int p=j+1,q=nums.length-1;
                while(p<q){
                    int sum=nums[i]+nums[j]+nums[p]+nums[q];
                    if(sum==target){
                        result.add(Arrays.asList(nums[i],nums[j],nums[p],nums[q]));
                        while(p<q&&nums[p+1]==nums[p])
                            p++;
                        while(p<q&&nums[q-1]==nums[q])
                            q--;
                        p++;
                        q--;
                    }else if(sum<target)
                        p++;
                    else
                        q--;
                }
                while(j<nums.length-2-1&&nums[j+1]==nums[j])
                    j++;
            }
            while(i<nums.length-3-1&&nums[i+1]==nums[i])
                i++;
        }
        return result;
    }

    /**
     * 这是leetcode大神的实现,只需要4ms.
     * 优化的关键在于两层for循环中边界值的判断,
     * 比如当前for循环中最小的四元素和大于target,那么肯定无解break.
     * 比如当前for循环中最大的四元素和小于target,那么continue,因为还有下一次for循环,该值可能就会大于等于target
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum2(int[] nums,int target){
        /*定义一个返回值*/
        List<List<Integer>> result=new ArrayList<>();
        /*当数组为null或元素小于4个时，直接返回*/
        if(nums==null||nums.length<4){
            return result;
        }
        /*对数组进行从小到大排序*/
        Arrays.sort(nums);
        /*数组长度*/
        int length=nums.length;
        /*定义4个指针k，i，j，h  k从0开始遍历，i从k+1开始遍历，留下j和h，j指向i+1，h指向数组最大值*/
        for(int k=0;k<length-3;k++){
            /*当k的值与前面的值相等时忽略*/
            if(k>0&&nums[k]==nums[k-1]){
                continue;
            }
            /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏*/
            int min1=nums[k]+nums[k+1]+nums[k+2]+nums[k+3];
            if(min1>target){
                break;
            }
            /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
            int max1=nums[k]+nums[length-1]+nums[length-2]+nums[length-3];
            if(max1<target){
                continue;
            }
            /*第二层循环i，初始值指向k+1*/
            for(int i=k+1;i<length-2;i++){
                /*当i的值与前面的值相等时忽略*/
                if(i>k+1&&nums[i]==nums[i-1]){
                    continue;
                }
                /*定义指针j指向i+1*/
                int j=i+1;
                /*定义指针h指向数组末尾*/
                int h=length-1;
                /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏，忽略*/
                int min=nums[k]+nums[i]+nums[j]+nums[j+1];
                if(min>target){
                    continue;
                }
                /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
                int max=nums[k]+nums[i]+nums[h]+nums[h-1];
                if(max<target){
                    continue;
                }
                /*开始j指针和h指针的表演，计算当前和，如果等于目标值，j++并去重，h--并去重，当当前和大于目标值时h--，当当前和小于目标值时j++*/
                while (j<h){
                    int curr=nums[k]+nums[i]+nums[j]+nums[h];
                    if(curr==target){
                        result.add(Arrays.asList(nums[k],nums[i],nums[j],nums[h]));
                        j++;
                        while(j<h&&nums[j]==nums[j-1]){
                            j++;
                        }
                        h--;
                        while(j<h&&i<h&&nums[h]==nums[h+1]){
                            h--;
                        }
                    }else if(curr>target){
                        h--;
                    }else {
                        j++;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        No18_FourSum test=new No18_FourSum();
        int[] nums={-1,-5,-5,-3,2,5,0,4};
        int target=-7;
        List<List<Integer>> lists = test.fourSum(nums, target);
        System.out.println(lists);
    }
}
