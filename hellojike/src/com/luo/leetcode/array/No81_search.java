package com.luo.leetcode.array;

/**
 * 81. 搜索旋转排序数组 II
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 *
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 *
 * 示例 1:
 *
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例 2:
 *
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * 进阶:
 *
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 */
public class No81_search {

    public boolean search(int[] nums, int target) {
        int len=nums.length;
        if(len==0)
            return false;
        int left=0,right=len-1;
        while(left<=right){
//            可以在前边添加两端去重操作
//            还有这种技巧！！！
            while(left<len-1&&nums[left]==nums[left+1])
                left++;
            while(right>0&&nums[right]==nums[right-1])
                right--;

            int mid=left+(right-left)/2;
            if(nums[mid]==target)
                return true;
//            前半部分有序 这里并不能判断前半部分有序
            if(nums[left]<=nums[mid]){
                if(target>=nums[left]&&target<=nums[mid]){
                    right=mid;
                }else{
                    left=mid+1;
                }
            }
//            后半部分有序
            else{
                if(target>=nums[mid]&&target<=nums[right]){
                    left=mid;
                }else{
                    right=mid-1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        No81_search test=new No81_search();
//        int[] nums={4,4,4,4,0,1,2};
//        int[] nums={1,3,1,1,1};
        int[] nums={1,1,1,1,1,1,1,1,2,1};
        int target=2;
        boolean search = test.search(nums, target);
        System.out.println(search);

    }
}
