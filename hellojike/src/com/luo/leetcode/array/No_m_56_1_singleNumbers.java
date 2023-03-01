package com.luo.leetcode.array;

import com.luo.util.CommonUtil;

/**
 * 面试题56 - I. 数组中数字出现的次数
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * 示例 1：
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 *
 */
public class No_m_56_1_singleNumbers {

    /**
     * 要求空间复杂度为O(1),那就无法使用map缓存出现的个数
     *
     * 可以考虑异或,异或运算的性质有交换律,结合律,自反性. A^0=A,A^A=0
     * 思路:
     *      比如 A^B^C^A^C^B^D^E=D^E
     *      将全部数字异或计算得两个单次出现的数字的异或,然后做区分(根据这两个数字不同的部分)
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
//        所有数值进行异或
        int xor=0;
        for(int n:nums)
            xor^=n;
//        找出这两个数第一个不同的位数,根据这个将这两个数分开
        int diff=xor&(~xor+1);
        int res1=0;
        for (int n:nums){
            if((n&diff)==0)
                res1^=n;
        }
        int[] result={res1,xor^res1};
        return result;
    }

    public static void main(String[] args){
        No_m_56_1_singleNumbers test=new No_m_56_1_singleNumbers();
        int[] nums={1,2,10,4,1,4,3,3};

        int[] ints = test.singleNumbers(nums);
        CommonUtil.display(ints);
    }
}
