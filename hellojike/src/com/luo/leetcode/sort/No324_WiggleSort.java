package com.luo.leetcode.sort;

import com.luo.util.CommonUtil;

import java.util.Arrays;

/*
    324. 摆动排序 II
    给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
        示例 1:
        输入: nums = [1, 5, 1, 1, 6, 4]
        输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
        示例 2:
        输入: nums = [1, 3, 2, 2, 3, 1]
        输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2]
        说明:
        你可以假设所有输入都会得到有效的结果。
        进阶:
        你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
*/
public class No324_WiggleSort {

    /**
     * 思路:
     *      使用排序,切分成两个子数组.
     *      对子数组倒序,最后输出
     * @param nums
     */
    public void wiggleSort(int[] nums){
        int len=nums.length;
        int[] tmp=new int[len];
        System.arraycopy(nums,0,tmp,0,len);
//        排序,将数组分为两部分 [0~(len-1)/2],((len-1)/2,len)
        Arrays.sort(tmp);
//        由于可能含有多个相同的数字,可能导致nums[0]<=nums[1]=>nums[2]
//        一种方式是,找到含有相同数字个数相同的子数组先打印

//        另一种方式是将子数组倒序
        reverse(tmp,0,(len-1)/2);
        reverse(tmp,(len-1)/2+1,len-1);
        int a=0,b=(len-1)/2+1;
        boolean left=true;
        for(int i=0;i<len;i++){
            if(left)
                nums[i]=tmp[a++];
            else
                nums[i]=tmp[b++];
            left=!left;
        }
        CommonUtil.display(nums);
    }

    private void reverse(int[] nums,int from,int to){
        for(int i=0,size=(to-from+1)/2;i<size;i++){
            int tmp=nums[from+i];
            nums[from+i]=nums[to-i];
            nums[to-i]=tmp;
        }
    }

    /**
     * 思路:
     *      使用快速选择排序.类似于快速排序中的partition函数.寻找数组中的中位数
     *      然后使用三项快速排序将数组分为三部分.
     *      最后输出
     * @param nums
     */
    public void wiggleSort2(int[] nums){
        int[] tmp=new int[nums.length];
        System.arraycopy(nums,0,tmp,0,nums.length);
        int lo=0,hi=tmp.length-1;
        int mid=(tmp.length-1)/2;
        while(true){
            int currMid=partition(tmp,lo,hi);
            if(currMid<mid)
                lo=currMid+1;
            else if(currMid>mid)
                hi=currMid-1;
            else
                break;
        }
        threeWay(tmp,mid);
        reverse(tmp,0,mid);
        reverse(tmp,mid+1,tmp.length-1);
        int a=0,b=mid+1;
        boolean left=true;
        for(int i=0;i<nums.length;i++){
            if(left)
                nums[i]=tmp[a++];
            else
                nums[i]=tmp[b++];
            left=!left;
        }
        CommonUtil.display(nums);
    }

    private void threeWay(int[] nums,int index){
        int lo=0,hi=nums.length-1;
        for(int i=lo;i<hi;i++){
            if(i==index)
                continue;
            if(nums[i]<nums[index])
                exch(nums,i,lo++);
            else if(nums[i]>nums[index])
                exch(nums,i--,hi--);
        }
    }

    /**
     *  二分,返回切分点的元素角标
     */
    private int partition(int[] nums,int lo,int hi){
        int piovt=nums[hi];
//        i 代表此角标前的都是小于piovt的
        int i=lo;
        for(int j=lo;j<hi;j++){
//            需要维持 角标i之前的都是小于piovt的
            if(nums[j]<piovt){
                exch(nums,i,j);
                i++;
            }
        }
//        交换 piovt 与角标i
        exch(nums,hi,i);
        return i;
    }

    private void exch(int[] nums,int i,int j){
        int tmp=nums[j];
        nums[j]=nums[i];
        nums[i]=tmp;
    }



    public static void main(String[] args){
        No324_WiggleSort test=new No324_WiggleSort();
        int[] nums={2,2,1,2,3,1};
        test.wiggleSort(nums);
    }
}
