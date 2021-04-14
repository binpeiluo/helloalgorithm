package com.luo.leetcode.binarysearch;

/**
 * 81. 搜索旋转排序数组 II
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。
 * 如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 *
 * 示例 1：
 * 输入：nums = [2,5,6,0,0,1,2], target = 0
 * 输出：true
 *
 * 示例 2：
 * 输入：nums = [2,5,6,0,0,1,2], target = 3
 * 输出：false
 *  
 * 提示：
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -104 <= target <= 104
 *  
 *
 * 进阶：
 *
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 *
 */
@SuppressWarnings("Duplicates")
public class No81_search {

    /**
     * 典型二分查找
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        int left=0,right=nums.length-1;

        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
//                中间就等于target
                return true;
            }else if(nums[right]>nums[mid]){
//                右边生序 这里分支操作是 left=mid，所以应该不能包括target=nums[right] (引起死循环)
                if(nums[mid]<target&&target<=nums[right]){
//                    在右边升序区间中
                    left=mid+1;
                }else{
                    right=mid;
                }
            }else if(nums[mid]<nums[right]){
//                左边升序 这里的分支也需要将 nums[left]==target包含
                if(nums[left]<=target&&target<nums[mid]){
//                    在左边升序区间中 区间为
                    right=mid-1;
                }else{
                    left=mid;
                }
            }else{
//                中间既不是target,两边也不是升序
//                减少一个元素 再次二分
                right=right-1;
            }
        }
//        所以这里打一个布丁？？？
        return nums[left]==target;
    }


    public static void main(String[] args) {
        No81_search test=new No81_search();

//        int[] nums={2,5,6,0,0,1,2};
//        int target=-1;

//        int[] nums={1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1};
//        int[] nums={1,1,2,1};
//        int target=2;
        int[] nums={3,2};
        int target=2;

        boolean search = test.search(nums, target);
        System.out.println(search);

    }
}
