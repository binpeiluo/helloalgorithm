package com.luo.labuladong.mind.binsearch;

@SuppressWarnings("Duplicates")
public class RotateSearch {


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

    public static void main(String[] args){
        RotateSearch test=new RotateSearch();
//        int[] numbers={3,4,5,1,2};
//        int[] numbers={1,2,3,4,5};
//        int[] numbers={1,0,1,1,1};
        int[] numbers={1,1,1,0,1};

        int minInRotaft = test.findMinInRotaft(numbers);
        System.out.println(minInRotaft);

        int minRotate = test.findMinRotate(numbers);
        System.out.println(minRotate);

        int max = test.findMax(numbers);
        System.out.println(max);
    }
}
