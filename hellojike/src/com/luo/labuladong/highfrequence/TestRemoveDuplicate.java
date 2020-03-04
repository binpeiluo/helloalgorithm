package com.luo.labuladong.highfrequence;

import com.luo.util.CommonUtil;

/**
 * 高频问题
 *      删除已排序数组的重复项
 *
 *      对于数组,在尾部删除元素或者插入元素是很高效的
 *      而在数组中间插入删除元素则涉及到元素搬运,这样复杂度就会很高
 */
public class TestRemoveDuplicate {

    /**
     * 已经排序的数组,需要原地和O(1) 空间复杂度的实现删除重复项
     *
     * 使用双指针方法,slow指针在后边,fast在前边探路,当发现不同的元素则告知slow
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int len=nums.length;
        if(len<2)
            return 1;
        int slow=0,fast=slow+1;
        while(fast<len){
            if(nums[fast]!=nums[slow]){
                each(nums,fast,slow+1);
                slow++;
            }
            fast++;
        }
        return slow+1;
    }

    private void each(int[] nums,int a,int b){
        int tmp=nums[a];
        nums[a]=nums[b];
        nums[b]=tmp;
    }

    public static void main(String[] args){
        TestRemoveDuplicate test=new TestRemoveDuplicate();
        int[] nums={0,0,1,1,2,2,2,3,3,4,5,6};
        int i = test.removeDuplicates(nums);
        System.out.println(i);
        CommonUtil.display(nums);
    }
}
