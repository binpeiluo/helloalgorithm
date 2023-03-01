package com.luo.algorithm.binsearch;

/**
 * labuladong   二分查找
 */
public class BinarySearch {
    public int binarySearch(int[] nums,int target){
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target)
                return mid;
            else if(nums[mid]<target)
                left=mid+1;
            else
                right=mid-1;
        }
        return -1;
    }

    /**
     * 寻找元素中值等于target的第一个元素
     * 该方法源自 labudadong,巧妙的是该方法使用[left,right)半闭半开区间
     * @param nums
     * @param target
     * @return
     */
    public int leftBound(int[] nums,int target){
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length; // 注意

        //在这里left找到的是nums数组中小于target元素的个数,也是target位于nums的位置
        while (left < right) { // 注意
            int mid = (left + right) / 2;
            if (nums[mid] == target) {//此时找到target并不急于返回,而是在[left,mid)中继续寻找,不断缩小左侧边界.[left,mid)
                right = mid;
            } else if (nums[mid] < target) {//此时nums[mid]小于target,缩小搜索区间.[mid+1,right)
                left = mid + 1;
            } else if (nums[mid] > target) {//此时nums[mid]大于target,缩小搜索区间.[left,mid)
                right = mid; // 注意
            }
        }
        // target 比所有数都大
        if (left == nums.length) return -1;
        // 类似之前算法的处理方式
        return nums[left] == target ? left : -1;
    }

    /**
     * 向右查找最后一个等于target的元素的角标
     * @param nums
     * @param target
     * @return
     */
    public int rightBound(int[] nums,int target){
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid + 1; // 注意
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        if (left == 0) return -1;
//        注意这里取left-1
        return nums[left-1] == target ? (left-1) : -1;
    }


    /**
     * 查找数组中第一个等于target的元素角标
     * 这种方式使用全闭区间 [lo,hi] 与前边的前闭后开区间不同
     * @param nums
     * @param target
     * @return
     */
    public int leftBound2(int[] nums,int target){
        if(nums.length==0) return -1;
        int lo=0;
        int hi=nums.length-1;
        while(lo<=hi){
            int mid=lo+(hi-lo)/2;
            if(nums[mid]>target)
                hi=mid-1;
            else if(nums[mid]<target)
                lo=mid+1;
            else{
                if(mid==0||nums[mid-1]<target)
                    return mid;
                else
                    hi=mid-1;
            }
        }
        return -1;
    }


    /**
     * 查找数组中最后一个等于target的元素角标
     * @param nums
     * @param target
     * @return
     */
    public int rightBound2(int[] nums,int target){
        if(nums.length==0) return -1;
        int lo=0;
        int hi=nums.length-1;
        while(lo<=hi){
            int mid=lo+(hi-lo)/2;
            if(nums[mid]<target)
                lo=mid+1;
            else if(nums[mid]>target)
                hi=mid-1;
            else{
                if(mid==nums.length-1||nums[mid+1]>target)
                    return mid;
                else
                    lo=mid+1;
            }
        }
        return -1;

    }

    // 搜索左侧边界
    public int leftBound3(int[] a, int target){
        // [left, right)
        int left=0, right=a.length;
        // 退出条件是 left=right 此时[left, left)不包括任何值
        // 可为啥left就代表小于target的元素个数呢?
        // 想明白了, 如果mid元素等于target, 其角标就是前边元素的个数 前边可能也有等于target的元素, 那么边界值就是mid
        while(left < right){
            int mid = left + (right-left)/2;
            // [left, mid) mid [mid+1, right)
            if(a[mid] == target){
                right = mid;
            }else if(a[mid] < target){
                left = mid +1;
            }else if(a[mid] > target){
                right = mid;
            }
        }
        if(left == a.length || a[left] != target) {
            return -1;
        }
        return left;
    }

    public int rightBound3(int[] a, int target){
        int left=0, right=a.length;
        // [left, right) 退出条件是left=right
        // 小于等于target的元素个数
        while(left < right){
            // [left, mid) mid [mid+1, right)
            int mid = left + (right - left) / 2;
            if(a[mid] == target){
                left = mid + 1;
            }else if(a[mid] < target){
                left = mid + 1;
            }else if(a[mid] > target){
                right = mid;
            }
        }
        // 思考为啥要-1 因为left是小于等于target的元素个数 left对应元素肯定不等于target
        if(left == 0 || a[left-1] != target){
            return -1;
        }
        return left-1;
    }

    public static void main(String[] args){
        BinarySearch test=new BinarySearch();
        int[] nums={1,2,2,2,3,3,4,4,6};
        int target= 3;
        int i = test.binarySearch(nums, target);
        System.out.println("二分查找:"+i);

        int i1 = test.leftBound(nums, target);
        System.out.println("left bound 第一个等于target =="+i1);

        int i2 = test.leftBound2(nums, target);
        System.out.println("left bound2 第一个等于target == "+i2);

        i2 = test.leftBound2(nums, target);
        System.out.println("left bound3 第一个等于target == "+i2);

        int i3 = test.rightBound(nums, target);
        System.out.println("right bound 最后一个等于target =="+i3);

        int i4 = test.rightBound2(nums, target);
        System.out.println("right bound2 最后一个等于target=="+i4);

        i4 = test.rightBound3(nums, target);
        System.out.println("right bound3 最后一个等于target=="+i4);
    }
}
