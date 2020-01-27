package com.luo.leetcode.array;

/*
4. 寻找两个有序数组的中位数
给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
        请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
        你可以假设 nums1 和 nums2 不会同时为空。
        示例 1:
        nums1 = [1, 3]
        nums2 = [2]
        则中位数是 2.0
        示例 2:
        nums1 = [1, 2]
        nums2 = [3, 4]
        则中位数是 (2 + 3)/2 = 2.5


该题需要使用 O(log(m+n))  典型的二分法实现的复杂度
*/
public class No4_FindMedianSortedArrays {

    /**
     * 思路:
     *  最容易想到的就是使用合并排序将数组合并成一个数组,然后寻找中位数
     *
     *  时间复杂度:      O(n)
     *  空间复杂度:      O(n)
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merge = merge(nums1, nums2);
        return merge.length%2==1?merge[merge.length/2]:(merge[merge.length/2-1]+merge[merge.length/2])/2.0;
    }

    private int[] merge(int[] nums1, int[] nums2){
        if(nums1==null||nums1.length==0)
            return nums2;
        if(nums2==null||nums2.length==0)
            return nums1;
        int[] result=new int[nums1.length+nums2.length];
        int p=0,q=0;
        for(int i=0;i<result.length;i++){
            if(p==nums1.length)
                result[i]=nums2[q++];
            else if(q==nums2.length)
                result[i]=nums1[p++];
            else{
                if(nums2[q]<nums1[p])
                    result[i]=nums2[q++];
                else
                    result[i]=nums1[p++];
            }
        }
        return result;
    }


    /**
     * 牛皮牛皮
     * @param A
     * @param B
     * @return
     */
    public double findMedianSortedArrays2(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0;//A 数组折半查找左边界
        int iMax = m;//A 数组折半查找右边界
//         halfLen 的作用就是中点坐标，当 A 数组中折半查找向右移动时，B 数组以 halfLen 为相对点向左移动，以保持始终均分
        int halfLen = (m + n + 1) / 2;

        //情况一: A 数组为空，中位数在 B 数组
        //情况二: A 数组较短
        //  1) A 数组元素都较小，中位数在B数组
        //  2) A 数组元素都较大，中位数在B数组
        //  3) A、B 元素大小分布基本相当，中位数为被分割的两数组左半部分较大的那一个和右半部分较小的那一个之和的一半
        //情况三: A、B 等长
        //  1) A 数组元素都比B数组元素小，中位数为 A 数组尾元素和B数组首元素之和的一半
        //  2) B 数组元素都比A数组元素小，中位数为 B 数组尾元素和A数组首元素之和的一半
        //  3) A、B 元素大小分布基本相当，中位数为被分割的两数组左半部分较大的那一个和右半部分较小的那一个之和的一半

        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;//A数组中分割点
            int j = halfLen - i;//B数组中分割点
            //数组 A 分割点相邻左边那个元素比数组 B 分割点相邻右边那个元素大，则应该将数组 A 分割点向右移，数组 B 分割点向左移
            //数组 A 分割点有向左移趋势，需检查左边界
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            //数组 A 分割点相邻右边那个元素比数组 B 分割点相邻左边那个元素大，则应该将数组 A 分割点向左移，数组 B 分割点向右移
            //数组 A 分割点有向右移趋势，需检查右边界
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            //得出结果
            else { // i is perfect
                int maxLeft = 0;
                //情况一,情况二2，情况三2
                if (i == 0) { maxLeft = B[j-1]; }
                //情况三1
                else if (j == 0) { maxLeft = A[i-1]; }
                //情况二1,情况二3,情况三3
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                //元素个数总和为奇数
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                //情况一: A 数组为空，中位数在 B 数组
                //情况二: A 数组较短
                //  1) A 数组元素都较小，中位数在B数组
                //  2) A 数组元素都较大，中位数在B数组
                //  3) A、B 元素大小分布基本相当，中位数为被分割的两数组左半部分较大的那一个和右半部分较小的那一个之和的一半
                //情况三: A、B 等长
                //  1) A 数组元素都比B数组元素小，中位数为 A 数组尾元素和B数组首元素之和的一半
                //  2) B 数组元素都比A数组元素小，中位数为 B 数组尾元素和A数组首元素之和的一半
                //  3) A、B 元素大小分布基本相当，中位数为被分割的两数组左半部分较大的那一个和右半部分较小的那一个之和的一半

                //元素个数总和为偶数
                int minRight = 0;
                //情况一,情况二1
                if (i == m) { minRight = B[j]; }
                //情况三2
                else if (j == n) { minRight = A[i]; }
                //情况二2、3，情况三1、3
                else { minRight = Math.min(B[j], A[i]); }
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    public static void main(String[] args){
        No4_FindMedianSortedArrays test=new No4_FindMedianSortedArrays();
        int[] nums1={1,3};
        int[] nums2={2,4};
        double medianSortedArrays = test.findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }
}
