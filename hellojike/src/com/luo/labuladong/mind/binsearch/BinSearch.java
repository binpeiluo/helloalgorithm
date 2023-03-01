package com.luo.labuladong.mind.binsearch;

@SuppressWarnings("Duplicates")
/**
 * 测试二分查找
 */
public class BinSearch {

    /**
     * 最简单的二分查找
     * 在不重复的已排序数组中查找target的角标
     * @param nums
     * @return
     */
    public int binSearch(int[] nums,int target){
        int left=0,right=nums.length-1;
//        这里区间为 [left,right]
//        跳出循环的条件为 left>right
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
//                此时区间变成 [mid+1,right]
                left=mid+1;
            }else if(nums[mid]>target){
//                此时区间修改为 [left,mid-1]
                right=mid-1;
            }
        }
//        在区间中找不到 直接返回-1
        return -1;
    }

    /**
     * 前边是使用闭区间来搜索的，这里换开区间来搜索
     * @param nums
     * @param target
     * @return
     */
    public int binSearch2(int[] nums, int target){
        int left=0,right=nums.length;
//        注意这里的区别 区间为 [left,right) 所以right取len(nums)
//        这里跳出循环的条件为 left=right 因为半开半闭区间就取不到值了
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
//                这里犯了一个错 原来区间为[left,right) 经过判断通过mid将区间分为 [left,mid) [mid+1,right)
//                二分出来的区间加起来正好等于原来的区间
//                此时区间修改为 [mid+1,right)
                left=mid+1;
            }else if(nums[mid]>target){
//                此时区间修改为 [left,mid)
                right=mid;
            }
        }
        return -1;
    }

    /**
     * 包含重复元素的数组 查找第一个等于target的角标
     * 闭区间
     * @param nums
     * @param target
     * @return
     */
    public int binSearchLeft(int[] nums, int target){
        int left=0,right=nums.length-1;
//        搜索的区间为 [left,right]
//        跳出循环的条件为 left>right
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
//                此时缩小区间 【left,mid]
//                这里居然是 right=mid-1 感觉讲不通
//                这里可以将 搜索视为在数组查找小雨target的元素个数
//                难道因为跳出循环条件为left>right 而跳出循环是使用left来判断的,所以这里必须是right=mid-1
                right=mid-1;
            }else if(nums[mid]<target){
//                此时区间修改为 [mid+1,right]
                left=mid+1;
            }else if(nums[mid]>target){
//                此时区间修改为 [left,mid-1]
                right=mid-1;
            }
        }
//        按理是判断 nums[left]==target 假如不是说明没有target元素
//        需要判断left是否越界了
        return (left==nums.length||nums[left]!=target)?-1:left;
    }

    /**
     * 左侧二分查找 半闭半开区间搜索
     * @param nums
     * @param target
     * @return
     */
    public int binSearchLeft2(int[] nums, int target){
        int left=0, right=nums.length;
//        搜索区间为 【left,right)
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
//                此时区间修改为 [left,mid) ?
                right=mid;
            }else if(nums[mid]<target){
//                此时区间修改为 [mid+1,right)
                left=mid+1;
            }else if(nums[mid]>target){
//                此时区间修改为 [left,mid)
                right=mid;
            }
        }
//        结束条件为 left=right
        return (left==nums.length||nums[left]!=target)?-1:left;
    }



    public static void main(String[] args){
        BinSearch test=new BinSearch();

        int[] nums={1,2,3,4,4,4,6};
        int target=4;
        int i = test.binSearch(nums, target);
        int i1 = test.binSearch2(nums, target);
        System.out.printf("简单二分查找: %d , %d", i , i1);

        System.out.println();
        int i2 = test.binSearchLeft(nums, target);
        int i3 = test.binSearchLeft2(nums, target);
        System.out.printf("左侧二分查找: %d , %d" , i2, i3);


    }
}
