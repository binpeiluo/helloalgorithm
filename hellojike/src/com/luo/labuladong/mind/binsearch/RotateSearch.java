package com.luo.labuladong.mind.binsearch;

@SuppressWarnings("Duplicates")
public class RotateSearch {

    /**
     * 在没有重复元素的数组中查找target元素角标
     * @param nums
     * @param target
     * @return
     */
    public int findInRotate(int[] nums,int target){
        int len=nums.length;
        if(len==0)
            return -1;
        int left=0,right=len-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }

            if(nums[mid]<nums[right]){
//                右边有序
                if(nums[mid]<target&&target<=nums[right]){
                    left=mid+1;
                }else{
                    right=mid;
                }
            }else{
//                右边非有序
                if(nums[mid]<target||target<=nums[right]){
                    left=mid+1;
                }else{
                    right=mid;
                }
            }
        }
        return -1;
    }

    /**
     * 在旋转数组中查找target角标
     * @param nums
     * @param target
     * @return
     */
    public int findInRotate2(int[] nums,int target){
        int len=nums.length;
        if(len==0){
            return -1;
        }
        int left=0,right=len-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }
            if(nums[left]<=nums[mid]){
//                前边有序,有可能left=mid
                if(nums[left]<=target&&target<=nums[mid]){
                    right=mid;
                }else{
                    left=mid+1;
                }
            }else{
//                后边有序
                if(nums[mid]<target||target<nums[right]){
                    left=mid+1;
                }else{
                    right=mid;
                }
            }
        }
        return -1;
    }

    /**
     * 在没有重复旋转数组中找到最小值
     * @param numbers
     * @return
     */
    public int findMinInRotaft(int[] numbers) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int mid = left+(right-left)/2;

            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            }
            else if (numbers[mid] < numbers[right]){
                right = mid;
            }else{
                right--;
            }
        }
        return numbers[left];
    }

    public int findMinRotate(int[] nums){
        int len=nums.length;
        int left=0,right=len-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]<nums[right]){
                right=mid;
            }else if(nums[mid]>nums[right]){
                left=mid+1;
            }else if(nums[mid]==nums[right]){
                right--;
            }
        }
        return nums[left];
    }

    public int findMax(int[] nums){
        int len=nums.length;
        int left=0,right=len-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[left]<nums[mid]){
                left=mid;
            }else if(nums[left]>nums[mid]){
                right=mid-1;
            }else if(nums[left]==nums[mid]){
                left++;
            }
        }
        return nums[left];
    }

    /**
     * 找到旋转数组的中位数
     * @param nums
     * @return
     */
    public double findMiddleNumber(int[] nums){
        int len=nums.length;
        int minIndex = this.findMinIndex(nums);
        System.out.println(minIndex);
        int right=len/2;
        int left=(len-1)/2;
        return (nums[(minIndex+left)%len]+nums[(minIndex+right)%len])/2.0;
    }

    /**
     * 找出旋转数组的最小元素的角标
     * @param nums
     * @return
     */
    private int findMinIndex(int[] nums){
        int len=nums.length;
        int left=0,right=len-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]<nums[right]){
//                有可能当前mid是最小的,所以不能right=mid+1
                right=mid;
            }else if(nums[mid]>nums[right]){
//
                left=mid+1;
            }else if(nums[mid]==nums[right]){
                right--;
            }
        }
        return left;
    }


    /**
     * 在旋转数组中找到
     * @param nums
     * @param target
     * @return
     */
    public boolean exsist(int[] nums,int target){
        int len=nums.length;
        int left=0,right=len-1;
        while(left<right){
            int mid=left+(right-left)/2;
//            当前找到则返回
            if(nums[mid]==target){
                return true;
            }

            if(nums[mid]<nums[right]){
//                右边有序
                if(target>nums[right]){
//                    查找值大于有序数组最大值,则在左边数组中查找
                    right=mid-1;
                }else if(target<nums[mid]){
//                    查找值小于有序数组最小值,则在左边数组中查找
                    right=mid-1;
                }else{
//                    当target==nums[mid]则会进入此分支,这样子不会有问题吗
                    left=mid+1;
                }
            }else if(nums[mid]>nums[right]){
//                当右边数组非有序
                if(target>nums[mid]){
//                    当查找数大于非有序数组左边值,那应该在该非有序数组中查找
                    left=mid+1;
                }else if(target<nums[right]){
//                    当查找数小于非有序数组右边值,那应该在该非有序数组中查找
                    left=mid+1;
                }else {
//                    如果不是,则在左边左边数组查找
                    right=mid;
                }
            }else{
//                当nums[mid]==nums[right]时,无法判断那边是有序数组
                right--;
            }
        }
        return nums[left]==target;
    }

    /**
     * 在旋转数组中查找等于target的最小角标.
     * @param nums
     * @param target
     * @return
     */
    public int findTargetMinIndex(int[] nums,int target){
        int len=nums.length;
        int left=0,right=len-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]<nums[right]){
//                右边有序
                if(target<nums[mid]){
//                    小于有序数组最小值
                    right=mid-1;
                }else if(target>nums[right]){
//                    大于有序数组最大值
                    right=mid-1;
                }else if (target==nums[mid]){
//                    当等于中间元素,那么固定右边界
                    right=mid;
                }else if(target==nums[right]&&nums[left]==target){
//                    当target==nums[right]时,需要判断前边数组是否有target,如果有必须在前边数组中查找
                    right=mid;
                } else {
//                    其余情况在有序数组中,更新left必须使用left=mid+1不然会出现死循环
                    left=mid+1;
                }
            }else if(nums[mid]>nums[right]){
//                右边非有序
                if(target>nums[mid]){
//                    当大于非有序数组左边值,应该在非有序数组中查找
                    left=mid+1;
                }else if(target<nums[right]){
//                    当查找值小于非有序数组右边值,应该在非有序数组中查找
                    left=mid+1;
                }else if(target==nums[right]){
                    left=mid+1;
                }else{
//                    其余情况,
                    right=mid;
                }

            }else{
                right--;
            }
        }
        return (left==len||nums[left]!=target)?-1:left;
    }

    /**
     * 在有重复元素的旋转数组中查找target的最小角标
     * @param nums
     * @param target
     * @return
     */
    public int findTargetMinIndex2(int[] nums,int target){
        int len=nums.length;
        int left=0,right=len-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]<nums[right]){
//                后边有序
                if(nums[mid]<target&&(target<nums[right]||(target==nums[right]&&nums[left]<target))){
//                    在后边有序数组范围之内,
//                    对于target==nums[right],需要特殊处理.因为当前nums[right]有可能是唯一一个target值,也可能前边数组中包含该target值
                    left=mid+1;
                }else{
//                    包括nums[mid]==target,则在前边数组中查找
                    right=mid;
                }
            }else if(nums[mid]>nums[right]){
//                后边乱序,那么前边是有序
                if(nums[mid]>target||target<nums[right]){
//                    大于后边数组第一个元素或者小于后边数组最后一个元素
                    left=mid+1;
                }else{
//                    包括nums[mid]==target,但是不包括target==nums[rigjt]
                    right=mid;
                }
            }else{
                right--;
            }
        }
        return nums[left]==target?left:-1;
    }

    public static void main(String[] args){
        RotateSearch test=new RotateSearch();
//        int[] numbers={3,4,5,1,2};
//        int[] numbers={4,5,1,2,3};
//        int[] numbers={1,2,3,4,5};
//        int[] numbers={1,0,1,1,1};
//        int[] numbers={1,1,1,0,1};
//        int[] numbers={5,5,5,1,2,3,4,5};
        int[] numbers={0,0,5,5,5,6,7,8,9};

//        int[] numbers={2,1};
        int target=5;

        int inRotaft = test.findInRotate(numbers, target);
        System.out.println("无重复旋转数组中查找:"+inRotaft);

        int inRotate2 = test.findInRotate2(numbers, target);
        System.out.println("无重复旋转数组中查找:"+inRotate2);

        int minInRotaft = test.findMinInRotaft(numbers);
        System.out.println(minInRotaft);

        int minRotate = test.findMinRotate(numbers);
        System.out.println(minRotate);

        int max = test.findMax(numbers);
        System.out.println(max);

        double middleNumber = test.findMiddleNumber(numbers);
        System.out.println(middleNumber);

        boolean exsist = test.exsist(numbers, target);
        System.out.println(exsist);

        int targetMinIndex = test.findTargetMinIndex(numbers, target);
        System.out.println("可重复旋转数组查找target最小角标"+targetMinIndex);

        int targetMinIndex2 = test.findTargetMinIndex2(numbers, target);
        System.out.println("可重复旋转数组查找target最小角标"+targetMinIndex2);

    }
}
