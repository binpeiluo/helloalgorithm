package com.luo.leetcode.array;

/**
 * 80. 删除排序数组中的重复项 II
 * 给定一个排序数组，你需要在原地删除重复出现的元素，
 * 使得每个元素最多出现两次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 *
 * 给定 nums = [1,1,1,2,2,3],
 *
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 */
public class No80_removeDuplicates {
    /**
     * 类似于No26题.不同的是这里重复元素不超过两个
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int len=nums.length;
        if(len<=2)
            return len;
        int i=0,j=1;
        int count=1;
        for (;j<len;j++){
            if(nums[i]!=nums[j]){
                nums[++i]=nums[j];
                count=1;
            }else if(count>=2)
                continue;
            else{
                nums[++i]=nums[j];
                count++;
            }
        }
        return i+1;
    }

    public int removeDuplicates3(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i-2])
                nums[i++] = n;
        return i;
    }

    private void print(int[] nums,int len){
        System.out.println("去重后长度:"+len);
        for (int i = 0; i < len; i++) {
            System.out.print("\t"+nums[i]);
        }
        System.out.println();
    }

    public static void main(String[] args){
        No80_removeDuplicates test=new No80_removeDuplicates();
        int[] nums={1,1,1,1,2,2,3};
//        int i = test.removeDuplicates(nums);
//        test.print(nums,i);

        int i = test.removeDuplicates3(nums);
        test.print(nums,i);
    }
}
