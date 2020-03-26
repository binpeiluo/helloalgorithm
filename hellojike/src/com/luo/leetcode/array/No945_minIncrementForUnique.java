package com.luo.leetcode.array;

import java.util.Arrays;

/**
 * 945. 使数组唯一的最小增量
 * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
 * 返回使 A 中的每个值都是唯一的最少操作次数。
 */
public class No945_minIncrementForUnique {

    /**
     * 思路:
     *      使用排序,然后遍历,当发现与前边元素相同或者更小时,更新统计当前需要的更次的最小次数,并且更新当前元素的值
     *
     * 空间复杂度:   O(1)
     * 时间复杂度:   O(nlogn)
     * @param A
     * @return
     */
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int len=A.length;
        if(len<2)
            return 0;
        int res=0;
        for (int i = 1; i < len; i++) {
            if(A[i]<=A[i-1]){
                int d=A[i-1]-A[i];
                A[i]=A[i-1]+1;
                res+=d+1;
            }
        }
        return res;
    }

    /**
     * 使用计数方法
     * @param A
     */
    public int minIncrementForUnique2(int[] A){
        int len=A.length;
        if(len<2)
            return 0;
        int[] count=new int[40000];
        int max=0;
        for (int i = 0; i < len; i++) {
            count[A[i]]++;
            max=Math.max(max,A[i]);
        }
        int move=0;
        for (int i = 0; i <max; i++) {
            if(count[i]>1){
                int d=count[i]-1;
                move+=d;
                count[i+1]+=d;
            }
        }
        move+=(count[max]-1)*count[max]/2;
        return move;
    }

    public static void main(String[] args){
        No945_minIncrementForUnique test=new No945_minIncrementForUnique();
//        int[] nums={1,1,2};
        int[] nums={3,2,1,2,1,7};
        int i = test.minIncrementForUnique(nums);
        System.out.println(i);

        int[] nums2={2,1,1,1};
        int i1 = test.minIncrementForUnique2(nums2);
        System.out.println(i1);
    }
}
