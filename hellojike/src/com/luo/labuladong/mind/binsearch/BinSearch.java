package com.luo.labuladong.mind.binsearch;

@SuppressWarnings("Duplicates")
public class BinSearch {

    /**
     * 找到往nums数组中插入target元素的位置
     * 使用左闭右开区间
     * @param nums
     * @param target
     * @return
     */
    private int findIndexLeft(int[] nums,int target){
        int lo=0,hi=nums.length;
//        搜索区间 [lo,hi)
//        结束条件 lo==hi
        while(lo<hi){
            int mid=lo+(hi-lo)/2;
            if(nums[mid]==target){
//                不急着返回,而是缩小右边界.
//                搜索区间,[left,mid)
                hi=mid;
            }else if(nums[mid]<target){
//                当发现中间值小于target时,target必定在中间值后边
//                搜索区间,[mid+1,hi)
                lo=mid+1;
            }else if(nums[mid]>target){
//                当发现中间值大于target时,有可能该位置就是target的插入位置
//                搜索区间,[left,mid)
                hi=mid;
            }
        }
//        此时lo是target插入的位置,如果需要返回target在nums的左侧边界,可以追加判断
        lo=(lo==nums.length||nums[lo]!=target)?-1:lo;
        return lo;
    }

    /**
     * 在nums插入target元素的位置
     * 使用闭区间实现
     * @param nums
     * @param target
     * @return
     */
    private int findIndexLeft2(int[] nums,int target){
        int lo=0,hi=nums.length-1;
//        搜索区间,[lo,hi]
        while(lo<=hi){
//            结束条件,lo=hi+1
            int mid=lo+(hi-lo)/2;
            if(nums[mid]==target){
//                不着急返回,而是缩小上限
//                搜索条件,[left,mid-1]
                hi=mid-1;
            }else if(nums[mid]<target){
//                当中间元素小于target,则需要增大下限
//                搜索条件,[mid+1,right]
                lo=mid+1;
            }else if(nums[mid]>target){
//                当中间值大于target时,有可能该位置就是target的插入位置
//                搜索条件,[left,mid-1]
                hi=mid-1;
            }
        }
        lo=(lo==nums.length||nums[lo]!=target)?-1:lo;
        return lo;
    }

    public static void main(String[] args){
        BinSearch test=new BinSearch();
        int[] nums={5,7,7,8,8,10};
        int target =5;

        int indexLeft = test.findIndexLeft(nums, target);
        System.out.println(indexLeft);
        int indexLeft2 = test.findIndexLeft2(nums, target);
        System.out.println(indexLeft2);

    }
}
