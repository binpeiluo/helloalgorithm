package com.luo.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1248. 统计「优美子数组」
 * 给你一个整数数组 nums 和一个整数 k。
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * 请返回这个数组中「优美子数组」的数目。
 * 示例 1：
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 * 示例 2：
 * 输入：nums = [2,4,6], k = 1
 * 输出：0
 * 解释：数列中不包含任何奇数，所以不存在优美子数组。
 * 示例 3：
 * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * 输出：16
 */
public class No1248_numberOfSubarrays {

    /**
     * 思路一
     *      暴力算法,两个指针,加上判断是否包含k个奇数.时间复杂度为   O(n^3)
     *
     * 思路二
     *      动态规划  使用两个状态保存,但是超出内存限制了... 需要空间复杂度 O(n^2)
     *
     * 思路三
     *      动态规划  优化状态,  没想到如何优化
     *
     * 思路四
     *      双指针,使用双指针指向两个奇数,两个指针之间的奇数个数为k.
     *      然后计算这两个指针两边的偶数个数,然后累计计算
     *
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays(int[] nums, int k) {
//        确定状态,两个指针
//        明确dp数组定义,dp[i][j]表示以nums[i]起始,以nums[j]为结束的子数组中包含奇数的个数
//        状态转移方程
//          dp[i][j]=dp[i+1][j] + 0 / 1
//                  =dp[i][j-1] + 0 / 1
//        base case
        int len=nums.length;
        int[][] dp=new int[len][len];
        for (int i = 0; i < len; i++) {
            if(nums[i]%2==1)
                dp[i][i]=1;
        }
        for (int i = len-1; i >=0; i--) {
            for (int j = i; j < len; j++) {
                if(i+1<len){
                    dp[i][j]=dp[i+1][j]+nums[i]%2;
                }
                if(j-1>=0){
                    dp[i][j]=dp[i][j-1]+nums[j]%2;
                }
            }
        }
        int res=0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if(dp[i][j]==k)
                    res++;
            }
        }
        return res;
    }

    /**
     * 使用思路四    双指针实现
     * wtf 超时了
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays2(int[] nums, int k) {
        int len=nums.length;
//        双指针,两个指针指向两个奇数,内部包含k个奇数
        int left=0;
        while(left<len&&nums[left]%2==0)
            left++;
        int right=len-1;
        while(right>=0&&(nums[right]%2==0 || countOdd(nums,left,right)>k)){
            right--;
        }
        int countOdd=countOdd(nums,left,right);
        if(countOdd<k)
            return 0;
        int res=0;
        while(right<len){
            int leftCount=1;
            int leftTmp=left;
            while(leftTmp>0&&nums[--leftTmp]%2==0){
                leftCount++;
            }
            int rightCount=1;
            int rightTmp=right;
            while(rightTmp<len-1&&nums[++rightTmp]%2==0){
                rightCount++;
            }
            res+=leftCount*rightCount;
            left++;
            while(left<len&&nums[left]%2==0)
                left++;
            right++;
            while(right<len&&nums[right]%2==0)
                right++;
        }
        return res;
    }

    private int countOdd(int[] nums,int start,int end){
        int res=0;
        for (int i = start; i <=end; i++) {
            if(nums[i]%2==1)
                res++;
        }
        return res;
    }

    /**
     * 别人的双指针实现
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays3(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) return 0;
        // 双指针
        int left = 0, right = 0;
        int count = 0; // 连续子数组中奇数的个数
        int res = 0;
        int preEven = 0; // 记录第一个奇数前面的偶数个数
        while (right < nums.length){
            // 连续子数组中奇数个数不够
            if (count < k){
                if (nums[right] % 2 != 0) count++;
                right++; // 移动右侧指针
            }
            // 连续子数组中奇数个数够了，看第一个奇数前面有多少个偶数
            if (count == k) {
                preEven = 0;
                while (count == k){
                    res++;
                    if (nums[left] % 2 != 0) count--;
                    left++;
                    preEven++;
                }
            }
            // 每次遇到 right 为偶数的时候就进行累加 相当于区间前面偶数个数 * 后面偶数个数
            else res += preEven;
        }
        return res;
    }

    /**
     * 另一种双指针
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays3_1(int[] nums, int k) {
        int left = 0, right = 0, oddCnt = 0, res = 0;
        while (right < nums.length) {
            // 右指针先走，每遇到一个奇数则 oddCnt++。
            if ((nums[right++] & 1) == 1) {
                oddCnt++;
            }
            //  若当前滑动窗口 [left, right) 中有 k 个奇数了，进入此分支统计当前滑动窗口中的优美子数组个数。
            if (oddCnt == k) {
                // 先将滑动窗口的右边界向右拓展，直到遇到下一个奇数（或出界）
                // rightEvenCnt 即为第 k 个奇数右边的偶数的个数
                int tmp = right;
                while (right < nums.length && (nums[right] & 1) == 0) {
                    right++;
                }
                int rightEvenCnt = right - tmp;
                // leftEvenCnt 即为第 1 个奇数左边的偶数的个数
                int leftEvenCnt = 0;
                while ((nums[left] & 1) == 0) {
                    leftEvenCnt++;
                    left++;
                }
                // 第 1 个奇数左边的 leftEvenCnt 个偶数都可以作为优美子数组的起点
                // (因为第1个奇数左边可以1个偶数都不取，所以起点的选择有 leftEvenCnt + 1 种）
                // 第 k 个奇数右边的 rightEvenCnt 个偶数都可以作为优美子数组的终点
                // (因为第k个奇数右边可以1个偶数都不取，所以终点的选择有 rightEvenCnt + 1 种）
                // 所以该滑动窗口中，优美子数组左右起点的选择组合数为 (leftEvenCnt + 1) * (rightEvenCnt + 1)
                res += (leftEvenCnt + 1) * (rightEvenCnt + 1);

                // 此时 left 指向的是第 1 个奇数，因为该区间已经统计完了，因此 left 右移一位，oddCnt--
                left++;
                oddCnt--;
            }
        }
        return res;
    }

    /**
     * 双指针自己再次实现
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays3_2(int[] nums, int k) {
        int len=nums.length;
        int left=0,right=0,oddCount=0;
        int res=0;
        while(right<len){
//            找到区间内个数为k的右节点.此时right指向最后一个奇数的右边一个值
            if((nums[right++] &1) ==1)
                oddCount++;
//            当发现奇数数量等于k,那么进入统计
            if(oddCount==k){
//                查找right右边有多少偶数
                int rightTmp=right;
                while(right<len&&(nums[right] &1)==0){
                    right++;
                }
                int rightCount=right-rightTmp+1;
                int leftTmp=left;
                while((nums[left] &1)==0){
                    left++;
                }
                int leftCount=left-leftTmp+1;
                res+=leftCount*rightCount;
//                此时right指向最后一个奇数的右边,不需要移动.
//                需要移动 left
                left++;
                oddCount--;
            }
        }
        return res;
    }

    /**
     * 别人的动态规划实现
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays4(int[] nums, int k) {
        int n = nums.length;
//        表示 dp[i]表示0~i元素中有多少个奇数
//        状态转移 dp[i] 与 dp[i-1] 的关系为： dp[i] = dp[i-1] + (nums[i] & 1)
        int[] counts=new int[n+1];
        Arrays.fill(counts,0);
        counts[0]=1;
        int oddCount = 0, result = 0;
        for (int i = 0; i < n; i++) {
            oddCount += nums[i] & 1;
            result += oddCount >= k ? counts[oddCount - k] : 0;
            counts[oddCount] += 1;
        }
        return result;
    }

    public static void main(String[] args){
        No1248_numberOfSubarrays test=new No1248_numberOfSubarrays();
//        int[] nums={1,1,2,1,1};
//        int k=3;

//        int[] nums={2,4,6};
//        int k=1;

//        int[] nums={2,2,2,1,2,2,1,2,2,2};
//        int k=2;

        int[] nums={2,4,6,1,8,10,1,12,14,16};
        int k=2;
        int i = test.numberOfSubarrays(nums, k);
        System.out.println(i);

        int i1 = test.numberOfSubarrays2(nums, k);
        System.out.println(i1);

        int i2 = test.numberOfSubarrays3(nums, k);
        System.out.println(i2);

        int i4 = test.numberOfSubarrays3_1(nums, k);
        System.out.println(i4);
        int i5 = test.numberOfSubarrays3_2(nums, k);
        System.out.println(i5);

        int i3 = test.numberOfSubarrays4(nums, k);
        System.out.println(i3);
    }
}
