package com.luo.labuladong.highfrequence;

import com.luo.util.CommonUtil;

/**
 * 高频题目
 *      数组nums包含从0到n的所有整数，但其中缺了一个。
 *      寻找处缺失的数字
 *      注意这道题跟寻找缺失的第一个正整数不同
 */
public class TestFindMissingNumber {

    /**
     * 在遍历过程中找到各个数字的正确位置,当然需要判断越界的数字
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums){
        int len=nums.length;
        for (int i = 0; i < len; i++) {
//            把位置不正确的找出来,寻找出其正确位置.准备交换
//            nums[i] 的正确位置是 nums[nums[i]]
            if(nums[i]>=0&&nums[i]<len
                &&nums[i]!=i&&nums[nums[i]]!=nums[i]){
                each(nums,i,nums[i]);
                i--;
            }
        }
        int res=0;
        while(res<len&&nums[res]==res)
            res++;
        return res;
    }

    private void each(int[] nums,int i,int j){
        int t=nums[i];
        nums[i]=nums[j];
        nums[j]=t;
    }

    /**
     * 使用异或完成,按位异或,数字相同则取0,不相同则取0
     * 任何数字跟0异或仍然是自己,任何数字跟自己异或是0
     *
     * 能使用异或完成是因为异或具有交换律和结合律
     * 同时这种方式使用的前提是数组不包含负数
     * @param nums
     * @return
     */
    public int missingNumber2(int[] nums){
        int res=0;
        int len=nums.length;
        res^=len;
        for (int i = 0; i < len; i++) {
            res^=i^nums[i];
        }
        return res;
    }

    /**
     * 更简单的是可以使用等差数列求和
     * 求得nums数组的和,与0~n数组求和,相减可得缺失的数字
     * @param nums
     * @return
     */
    public int missingNumber3(int[] nums){
        int len=nums.length;
        int expect=(0+len)*(len+1)/2;
        int sum=0;
        for (int num:nums)
            sum+=num;
        return expect-sum;
    }


    public static void main(String[] args){
        TestFindMissingNumber test=new TestFindMissingNumber();
        int[] nums={9,6,4,2,3,5,7,0,1};
        int i = test.missingNumber(nums);
        System.out.println(i);

        int i1 = test.missingNumber2(nums);
        System.out.println(i1);

        int i2 = test.missingNumber3(nums);
        System.out.println(i2);
    }
}
