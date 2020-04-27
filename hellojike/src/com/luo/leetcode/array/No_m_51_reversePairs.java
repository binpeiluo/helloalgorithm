package com.luo.leetcode.array;

/**
 * 面试题51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组，求出这个数组中的逆序对的总数。
 * 示例 1:
 * 输入: [7,5,6,4]
 * 输出: 5
 * 限制：
 * 0 <= 数组长度 <= 50000
 */
public class No_m_51_reversePairs {

    /**
     * 朴素算法
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        int res=0;
        int len=nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                if(nums[i]>nums[j])
                    res++;
            }
        }
        return res;
    }

    /**
     * 通过归并算法计算
     * 算法思想是在归并排序过程中计算逆序对的个数
     *
     * 时间复杂度:   O(nlogn)
     * 空间复杂度:   O(n)
     * @param nums
     * @return
     */
    public int reversePairs2(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }
        int[] copy = new int[len];
        for (int i = 0; i < len; i++) {
            copy[i] = nums[i];
        }

        int[] temp = new int[len];
        return reversePairs(copy, 0, len - 1, temp);
    }

    /**
     * nums[left..right] 计算逆序对个数并且排序
     *
     * @param nums
     * @param left
     * @param right
     * @param temp
     * @return
     */
    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int leftPairs = reversePairs(nums, left, mid, temp);
        int rightPairs = reversePairs(nums, mid + 1, right, temp);

        // 如果整个数组已经有序，则无需合并，注意这里使用小于等于
        if (nums[mid] <= nums[mid + 1]) {
            return leftPairs + rightPairs;
        }

        int crossPairs = mergeAndCount(nums, left, mid, right, temp);
        return leftPairs + rightPairs + crossPairs;
    }

    /**
     * nums[left..mid] 有序，nums[mid + 1..right] 有序
     *
     * @param nums
     * @param left
     * @param mid
     * @param right
     * @param temp
     * @return
     */
    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }
        int i = left;
        int j = mid + 1;
        int count = 0;
        for (int k = left; k <= right; k++) {
            // 有下标访问，得先判断是否越界
            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                // 注意：这里是 <= ，写成 < 就不对，请思考原因
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;

                // 在 j 指向的元素归并回去的时候，计算逆序对的个数，只多了这一行代码
                count += (mid - i + 1);
            }
        }
        return count;
    }

    /**
     * 自己实现的归并算法计算
     * @param nums
     * @return
     */
    public int reversePairs3(int[] nums) {
        int len=nums.length;
        if(len<2)
            return 0;
//        为了避免修改原数组
        int[] copy=new int[len];
        for (int i = 0; i < len; i++) {
            copy[i]=nums[i];
        }
//        归并使用辅助数组
        int[] temp=new int[len];
        return mergeSortAndCount3(copy,0,len-1,temp);
    }

    private int mergeSortAndCount3(int[] nums,int start,int end,int[] temp){
        if(start==end)
            return 0;
        int mid=start+(end-start)/2;
        int leftCount = mergeSortAndCount3(nums, start, mid, temp);
        int rightCount = mergeSortAndCount3(nums, mid + 1, end, temp);
        int mergeCount=0;
//        有可能不需要归并,因为已经有序了
        if(nums[mid]>nums[mid+1]){
            mergeCount=mergeAndSort3(nums,start,mid,end,temp);
        }
        return leftCount+rightCount+mergeCount;
    }

    private int mergeAndSort3(int[] nums,int start,int mid,int end,int[] temp){
        for (int i = start; i <=end; i++) {
            temp[i]=nums[i];
        }
        int res=0;
        int i=start,j=mid+1;
        for (int k = start; k <=end; k++) {
            if(i==mid+1)
                nums[k]=temp[j++];
            else if(j==end+1)
                nums[k]=temp[i++];
            else if(temp[i]>temp[j]){
                nums[k]=temp[j++];
                res+=(mid-i+1);
            }else{
                nums[k]=temp[i++];
            }
        }
        return res;
    }


    public static void main(String[] args){
        No_m_51_reversePairs test=new No_m_51_reversePairs();
        int[] nums={7,5,6,4};
        int i = test.reversePairs(nums);
        System.out.println(i);

        int i1 = test.reversePairs2(nums);
        System.out.println(i1);

        int i2 = test.reversePairs3(nums);
        System.out.println(i2);
    }
}
