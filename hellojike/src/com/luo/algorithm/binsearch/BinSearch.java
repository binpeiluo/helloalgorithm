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
     *
     * @param nums   数组
     * @param target 目标元素
     * @return 第一个等于target的元素角标
     */
    public int leftBound(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else {// nums[mid]=target
                if (mid == 0||nums[mid - 1] !=target ) //第一个元素或者前一个元素不等于target
                    return mid;
                else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }



    public static void main(String[] args) {
        BinSearch search = new BinSearch();
        int arraySize = 10;
        int bounds = 5;
        int target = 3;
        int[] ints = CommonUtil.generateArray(arraySize, bounds);
        Arrays.sort(ints);

        int eleIndex = search.binSearch(ints, target);
//        int eleIndex = search.leftBound(ints, target);
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
