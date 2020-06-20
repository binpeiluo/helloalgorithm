package com.luo.labuladong.mind.binsearch;

@SuppressWarnings("Duplicates")
public class BinSearch {

    public int findIndex(int[] nums,int target){
        int len=nums.length;
        int left=0,right=len-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                left=mid+1;
            }else if(nums[mid]>target){
                right=mid-1;
            }
        }
        return -1;
    }

    public int findIndex2(int[] nums,int target){
        int len=nums.length;
        int left=0,right=len;
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                left=mid+1;
            }else if(nums[mid]>target){
                right=mid;
            }
        }
        return -1;
    }


    /**
     * 找到数组中第一个target元素
     *
     * 使用左闭右开区间
     * @param nums
     * @param target
     * @return
     */
    private int findIndexLeft(int[] nums,int target){
        int lo=0,hi=nums.length;
//        搜索区间 [lo,hi)
//        结束条件 lo==hi
//        首先找到大于等于target的第一个元素位置
        while(lo<hi){
            int mid=lo+(hi-lo)/2;
            if(nums[mid]==target){
//                不急着返回,而是缩小右边界.
//                搜索区间,[left,mid)
//                注意这里右边mid边界为开区间,接下来是取不到这个mid的值的.
//                哪这里是否会有逻辑问题呢?
//                其实是不会的,因为如果该mid真的是第一个target值的元素,那么区间的左边界会一直增大.直到取值到mid.退出while循环
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
    public int findIndexLeft2(int[] nums,int target){
        int lo=0,hi=nums.length-1;
//        搜索区间,[lo,hi]
        while(lo<=hi){
//            结束条件,lo=hi+1
            int mid=lo+(hi-lo)/2;
            if(nums[mid]==target){
//                不着急返回,而是缩小上限
//                搜索条件,[left,mid-1]
//                当前右边界取值不到mid元素.会不会错误第一个target元素
                hi=mid-1;
            }else if(nums[mid]<target){
//                当中间元素小于target,则需要增大下限
//                搜索条件,[mid+1,right]
//                但是当mid值小于target,第一个大于等于target的元素必须在mid+1后边
                lo=mid+1;
            }else if(nums[mid]>target){
//                当中间值大于target时,有可能该位置就是target的插入位置
//                搜索条件,[left,mid-1]
//                同理,当发现当前值大于target,有可能该值是第一个大于等于target的元素.hi取值mid-1会造成错过该元素吗?
//                其实不会,因为假设该mid是第一个大于等于target的元素,这样子lo会逐渐增大.
//                直到lo取值到mid-1时,重新计算mid取值为lo,接着nums[mid-1]小于target,然后lo取值mid+1,此时lo=hi=第一个
                hi=mid-1;
            }
        }
        lo=(lo==nums.length||nums[lo]!=target)?-1:lo;
        return lo;
    }


    public int findIndexLeft3(int[] nums,int target){
        int len=nums.length;
        int left=0,right=len-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                right=mid;
            }else if(nums[mid]<target){
                left=mid+1;
            }else if(nums[mid]>target){
                right=mid;
            }
        }
        return nums[right]==target?right:-1;
    }

    /**
     * 查找出最后一个target元素
     * @param nums
     * @param target
     * @return
     */
    public int findIndexRight(int[] nums,int target){
        int len=nums.length;
        int left=0,right=len;
//        先找出大于target的第一个元素
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
//                当mid==target,那么小于等于target的最后一个元素在此之后,区间修改为 [mid+1,hi)
//                这里是更新left指针,注意不能更新为left=mid,因为本来长度比较小的时候,left本来就会等于mid
//                而更新成mid+1会不会错误唯一的一个等于target的元素呢?
//                思考一下,当right更新成mid-1就不会出现这种情况

//                这里当mid元素等于target时,扩大left边界为mid+1,所以最终left指向肯定不是target
                left=mid+1;
            }else if(nums[mid]<target){
//                当mid小于target时,区间修改为[mid,hi)
                left=mid+1;
            }else if(nums[mid]>target){
//                当mid大于target时,
                right=mid;
            }
        }
        if(left==0){
            return -1;
        }
        left=(nums[left-1]!=target)?-1:left-1;
        return left;
    }

    /**
     * 查找出最后一个等于target的位置
     * 使用闭区间搜索
     * @param nums
     * @param target
     * @return
     */
    public int findIndexRight2(int[] nums,int target){
        int len=nums.length;
        int left=0,right=len-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                left=mid+1;
            }else if(nums[mid]<target){
                left=mid+1;
            }else if(nums[mid]>target){
                right=mid-1;
            }
        }
        if(left==0){
            return -1;
        }
        left=nums[left-1]==target?left-1:-1;
        return left;
    }

    public static void main(String[] args){
        BinSearch test=new BinSearch();
        int[] nums={5,7,7,8,8,10};
//        int[] nums={7,8};
        int target =8;

        int index = test.findIndex(nums, target);
        System.out.println(index);

        int index2 = test.findIndex2(nums, target);
        System.out.println(index2);

        int indexLeft = test.findIndexLeft(nums, target);
        System.out.println("第一个target角标:"+indexLeft);
        int indexLeft2 = test.findIndexLeft2(nums, target);
        System.out.println("第一个target角标:"+indexLeft2);
        int indexLeft3 = test.findIndexLeft3(nums, target);
        System.out.println("第一个target角标:"+indexLeft3);

        int indexRight = test.findIndexRight(nums, target);
        System.out.println(indexRight);

        int indexRight2 = test.findIndexRight2(nums, target);
        System.out.println(indexRight2);
    }
}
