package com.luo.leetcode.divide;

import com.luo.util.CommonUtil;

/**
 * 剑指 Offer 51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 */
@SuppressWarnings("Duplicates")
public class No_offer_51_reversePairs {

    /**
     *
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {

        int len=nums.length;
        int[] buf=new int[len];
        return mersort(nums,buf,0,len-1);
    }

    private int mersort(int[] nums,int[] buf,int start,int end){
        if(start>=end){
            return 0;
        }
        int res=0;
        int mid=start+(end-start)/2;
        res+=mersort(nums,buf,start,mid);
        res+=mersort(nums,buf,mid+1,end);
        res+=merge(nums,buf,start,mid,end);
        return res;
    }

    private int merge(int[] nums,int[] buf,int start,int mid,int end){

//        将数据复制进辅助数组
        for (int i = start; i <=end; i++) {
            buf[i]=nums[i];
        }
        int res=0;
        int p=start;
        int q=mid+1;
        for (int i = start; i <=end; i++) {
            if(p==mid+1){
                nums[i]=buf[q++];
            }else if(q==end+1){
                nums[i]=buf[p++];
//                为什么发现右边数组使用完了,不计算逆序对呢???
//                因为计算过了!!!
//                比如 5 6 和 1 2 3
//                归并时,取了右边数组三次,已经计算了逆序对为6,那么当右边数组取完时,左边数组任何元素和右边组成的逆序对已经被计算过了
//                再次计算会导致逆序对*2
//                res+=end-mid;
            }else if(buf[p]<=buf[q]){
                nums[i]=buf[p++];
            }else {
                nums[i]=buf[q++];
                res+=mid-p+1;
            }
        }
        return res;
    }

    public static void main(String[] args){
        No_offer_51_reversePairs test=new No_offer_51_reversePairs();
//        int[] nums={7,5,6,4};
        int[] nums={2,1};

//        int i = test.reversePairs(nums);
//        System.out.println(i);
//        CommonUtil.display(nums);

        int[] t={5,6,1,2,3};
        int[] buf=new int[t.length];
        int merge = test.merge(t, buf, 0, 1, 4);
        System.out.println(merge);
    }
}
