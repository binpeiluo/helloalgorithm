package com.luo.leetcode.array;

/**
 * 1095. 山脉数组中查找目标值(这是一个 交互式问题 ）
 * 给你一个 山脉数组 mountainArr，请你返回能够使得 mountainArr.get(index) 等于 target 最小 的下标 index 值。
 * 如果不存在这样的下标 index，就请返回 -1。
 * 何为山脉数组？如果数组 A 是一个山脉数组的话，那它满足如下条件：
 * 首先，A.length >= 3
 * 其次，在 0 < i < A.length - 1 条件下，存在 i 使得：
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * 你将 不能直接访问该山脉数组，必须通过 MountainArray 接口来获取数据：
 * MountainArray.get(k) - 会返回数组中索引为k 的元素（下标从 0 开始）
 * MountainArray.length() - 会返回该数组的长度
 * 注意：
 *  对 MountainArray.get 发起超过 100 次调用的提交将被视为错误答案。
 *  此外，任何试图规避判题系统的解决方案都将会导致比赛资格被取消。
 *  为了帮助大家更好地理解交互式问题，我们准备了一个样例 “答案”：https://leetcode-cn.com/playground/RKhe3ave，
 *  请注意这 不是一个正确答案。
 * 示例 1：
 * 输入：array = [1,2,3,4,5,3,1], target = 3
 * 输出：2
 * 解释：3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。
 * 示例 2：
 * 输入：array = [0,1,2,4,2,1], target = 3
 * 输出：-1
 * 解释：3 在数组中没有出现，返回 -1。
 * 提示：
 * 3 <= mountain_arr.length() <= 10000
 * 0 <= target <= 10^9
 * 0 <= mountain_arr.get(index) <= 10^9
 */
public class No1095_findInMountainArray {

    public int findInMountainArray(int target, MountainArray mountainArr) {
//        核心思想就是二分查找找出峰值位置，然后在两边二分查找找出target
        int len=mountainArr.length();
//        在left和right之间找出峰值位置
        int left=0, right=len-1;
        int mid=0;
        while(left<=right){
            mid=left+(right-left)/2;
//            因为arr数组长度大于等于3，故这个中间值不可能在数组两边。
            int midValue=mountainArr.get(mid);
            int midLeftValue=mountainArr.get(mid-1);
            int midRightValue=mountainArr.get(mid+1);
            if(midLeftValue<midValue&&midRightValue<midValue)
                break;
            else if(midLeftValue>midValue)
                right=mid;
            else if(midValue<midRightValue)
                left=mid;
        }

        int i = binSearch(mountainArr, 0, mid, target, true);
        if(i!=-1)
            return i;
        i=binSearch(mountainArr,mid,len-1,target,false);
        return i;
    }

    private int binSearch(MountainArray arr,int start,int end,int target,boolean asc){
        int left=start,right=end;
        while(left<=right){
            int mid=left+(right-left)/2;
            int midValue=arr.get(mid);
            if(midValue==target)
                return mid;
            else {
                if(asc){
                    if(midValue<target)
                        left=mid+1;
                    else
                        right=mid-1;
                }else{
                    if(midValue<target)
                        right=mid-1;
                    else
                        left=mid+1;
                }
            }
        }
        return -1;
    }

    static class MountainArray{
        int[] nums;
        public MountainArray(int[] nums){
            this.nums=nums;
        }
        public int get(int index) {
            return nums[index];
        }
        public int length() {
            return nums.length;
        }
    }

    public static void main(String[] args){
        No1095_findInMountainArray test=new No1095_findInMountainArray();
//        int[] nums={1,2,3,4,5,3,1};
//        int target=3;

//        int[] nums={0,1,2,4,3,1};
//        int target=3;

//        int[] nums={1,5,2};
//        int target=0;

        int[] nums={3,5,3,2,0};
        int target=0;

        MountainArray arr=new MountainArray(nums);
        int inMountainArray = test.findInMountainArray(target, arr);
        System.out.println(inMountainArray);
    }
}
