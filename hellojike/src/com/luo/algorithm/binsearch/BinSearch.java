package com.luo.algorithm.binsearch;

import com.luo.util.CommonUtil;

import java.text.MessageFormat;
import java.util.Arrays;

public class BinSearch {
    /**
     * 二分查找 元素角标
     *
     * @param nums   元素数组
     * @param target 目标元素值
     * @return 目标元素角标
     */
    public int binSearch(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    /**
     * 查询数组中第一个等于target的元素角标
     * 两种写法 搜索区间为 [left, len(array)-1] 和 [left, len(array))
     * 这两种搜索区间写法不一样
     * 当搜索区间为[left, len(array)-1]时
     *      根据mid元素的大小和target的关系划分搜索区间 [left, mid-1], mid, [mid+1, right]
     * 当搜索区间为[left, len(array))时
     *      根据mid元素的大小和target的关系划分搜索区间 [left, mid), mid, [mid+1, right)
     *
     *
     * 结束循环条件时left角标可以视为数组中小于target的元素个数
     * @param nums   数组
     * @param target 目标元素
     * @return 第一个等于target的元素角标
     */
    public int leftBound_v1(int[] nums, int target) {
        int left = 0, right = nums.length;
//        退出循环条件为 left =right   left的取值范围是[0, len(nums)]
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target){
                right = mid;
            }else if (nums[mid] > target){
                right = mid;
            }else if (nums[mid] < target){
                left = mid + 1;
            }
        }
        if (left == nums.length){
            return -1;
        }
        return nums[left]==target?left:-1;
    }

    public int leftBound_v2(int[] nums, int target) {
        int left=0, right=nums.length-1;
//        结束循环条件为right>left, left取值为[0, len(nums)]
        while(left<=right){
            int mid=left+(right-left)/2;
            if (nums[mid]==target){
                right=mid-1;
            }else if (nums[mid]<target){
                left=mid+1;
            }else if (nums[mid]>target){
                right=mid-1;
            }
        }
        if(left==nums.length){
            return -1;
        }
        return nums[left]==target?left:-1;
    }



    public static void main(String[] args) {
        BinSearch search = new BinSearch();
        int arraySize = 10;
        int bounds = 5;
        int target = 3;
        int[] ints = CommonUtil.generateArray(arraySize, bounds);
        Arrays.sort(ints);

        int eleIndex = search.binSearch(ints, target);
        int eleIndexV1 = search.leftBound_v1(ints, target);
        int eleIndexV2 = search.leftBound_v2(ints, target);
        System.out.printf("eleIndex=%d, eleIndexV1=%d, eleIndexV2=%d\n", eleIndex, eleIndexV1, eleIndexV2);
        BinSearch.printMessage(ints, target, eleIndex);
    }

    private static void testBinSearch(){
    }

    private static void testLeftBound(){
    }

    public static void printMessage(int[] ints, int target, int eleIndex) {
        CommonUtil.display(ints);
        if (eleIndex == -1) {
            System.out.println(MessageFormat.format("target {0}  角标为 {1} ,未查询到"
                    , target, eleIndex));
        } else {
            System.out.println(MessageFormat.format("target {0}  角标为 {1} ,该角标 [{1}] = {2}"
                    , target, eleIndex, ints[eleIndex]));
            assert ints[eleIndex] == target;

            if (eleIndex > 0) {
                System.out.println("判断是否首个target元素角标-------");
                System.out.println(MessageFormat.format("角标前一个元素 [{0}]={1}"
                        , eleIndex - 1, ints[eleIndex - 1]));
                assert ints[eleIndex - 1] < target;

            }
        }
    }
}
