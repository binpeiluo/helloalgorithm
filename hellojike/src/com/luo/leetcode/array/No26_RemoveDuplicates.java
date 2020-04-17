package com.luo.leetcode.array;

/*
    26. 删除排序数组中的重复项
    给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
        不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
        示例 1:
        给定数组 nums = [1,1,2],
        函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
        你不需要考虑数组中超出新长度后面的元素。
        示例 2:
        给定 nums = [0,0,1,1,1,2,2,3,3,4],
        函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
        你不需要考虑数组中超出新长度后面的元素。
        说明:
        为什么返回数值是整数，但输出的答案是数组呢?
        请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
        你可以想象内部操作如下:
// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
        int len = removeDuplicates(nums);
// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
        for (int i = 0; i < len; i++) {
            print(nums[i]);
        }
*/
public class No26_RemoveDuplicates {

    /**
     *  思路:
     *      使用双指针法.十分巧妙慢指针i,快指针j.
     *      当发现快指针j元素和慢指针元素i相等时,则快指针j向后移动
     *      当发现快指针j元素和慢指针元素i不相同时,则将快指针元素移动到慢指针元素后边.然后慢指针向后移动
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if(nums.length<1)
            return nums.length;
        int i=0;
        for(int j=i+1;j<nums.length;j++){
            if(nums[j]!=nums[i]){
                nums[i+1]=nums[j];
                i++;
            }
        }
        return i+1;
    }

    /**
     * 双指针,i指向当前没有重复的元素,j指向当前遍历指向的元素
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums){
        int i=0,j=i+1;
        int len=nums.length;
        if(len<2)
            return len;
        for (;j<len;j++){
            if(nums[j]!=nums[i]){
//                这里并不能简单的进行i++,需要给i+1赋值
                nums[++i]=nums[j];
            }
        }
        return i+1;
    }

    private void print(int[] nums,int len){
        System.out.println("去重后长度:"+len);
        for (int i = 0; i < len; i++) {
            System.out.print("\t"+nums[i]);
        }
        System.out.println();
    }

    public static void main(String[] args){
        No26_RemoveDuplicates test=new No26_RemoveDuplicates();
        int[] nums={0,0,1,1,1,2,2,3,3,4};
//        int count = test.removeDuplicates(nums);
//        test.print(nums,count);

        int i = test.removeDuplicates2(nums);
        test.print(nums,i);


    }
}
