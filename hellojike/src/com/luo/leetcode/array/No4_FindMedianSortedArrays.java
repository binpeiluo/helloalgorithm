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
//         halfLen 的作用就是中点坐标，当 A 数组中折半查找向右移动时，
//         B 数组以 halfLen 为相对点向左移动，以保持始终均分
        int halfLen = (m + n + 1) / 2;

        //情况一: A 数组为空，中位数在 B 数组
        //情况二: A 数组较短
        //  1) A 数组元素都较小，中位数在B数组
        //  2) A 数组元素都较大，中位数在B数组
        //  3) A、B 元素大小分布基本相当，
        //      中位数为被分割的两数组左半部分较大的那一个和右半部分较小的那一个之和的一半
        //情况三: A、B 等长
        //  1) A 数组元素都比B数组元素小，中位数为 A 数组尾元素和B数组首元素之和的一半
        //  2) B 数组元素都比A数组元素小，中位数为 B 数组尾元素和A数组首元素之和的一半
        //  3) A、B 元素大小分布基本相当，中位数为被分割的两数组左半部分较大的那一个和右半部分较小的那一个之和的一半

        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;//A数组中分割点
            int j = halfLen - i;//B数组中分割点
            //数组 A 分割点相邻左边那个元素比数组 B 分割点相邻右边那个元素大，
            //  则应该将数组 A 分割点向右移，数组 B 分割点向左移
            //数组 A 分割点有向左移趋势，需检查左边界
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            //数组 A 分割点相邻右边那个元素比数组 B 分割点相邻左边那个元素大，
            //  则应该将数组 A 分割点向左移，数组 B 分割点向右移
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


    /**
     * 合并数组找出中位数的优化版本,不需要排序合并两个数组
     * 两个数组长度和为len,那么可以进行len/2次遍历,通过两个移动指向a数组和b数组的指针来找到中位数
     * 当长度和为奇数,那么遍历到len/2即可.但当长度和为偶数时,需要计算len/2和len/2-1的平均
     * @param A
     * @param B
     * @return
     */
    public double findMedianSortedArrays3(int[] A, int[] B) {
        int aLen=A.length,bLen=B.length;
        int len=aLen+bLen;
        int aIndex=0,bIndex=0;
        int pre=-1,curr=-1;
        for (int i = 0; i <=len/2; i++) {
            pre=curr;
            if(aIndex<aLen&&(bIndex==bLen||A[aIndex]<B[bIndex]))
                curr=A[aIndex++];
            else
                curr=B[bIndex++];
        }
        if(len%2==1)
            return curr;
        else
            return (curr+pre)/2.0;
    }

    /**
     * 另外一种log(m+n)实现
     * 思路是对解法3的优化,解法3一次遍历排除一个元素.这是可以优化的
     * 由于两个数组有序,求第k个最小元素,可以每次在两个数组比较第k/2个元素,
     *  通过这种方式每次可以排除 k-1(还是k-2???)个元素
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left)
                + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)
        ) * 0.5;
    }

    /**
     * 在nums1的start1~end1和nums2的start2~end2寻找第k个元素
     * @param nums1
     * @param start1
     * @param end1
     * @param nums2
     * @param start2
     * @param end2
     * @param k
     * @return
     */
    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }


    public static void main(String[] args){
        No4_FindMedianSortedArrays test=new No4_FindMedianSortedArrays();
        int[] nums1={1,3};
        int[] nums2={2,4};
        double medianSortedArrays = test.findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);

        double medianSortedArrays3 = test.findMedianSortedArrays3(nums1, nums2);
        System.out.println(medianSortedArrays3);
    }
}
