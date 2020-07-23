package com.luo.leetcode.trace;


/**
 * 312. 戳气球
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 
 * 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 *
 * 求所能获得硬币的最大数量。
 *
 * 说明:
 *
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 *
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 */
@SuppressWarnings("Duplicates")
public class No312_maxCoins {

    /**
     * 没有好想法,直接回溯一把梭哈
     * 回溯时间复杂度爆表 O(n!)
     *
     * 遇到时间复杂度为n!的问题时 可以使用分治思维解决.
     * 比如戳破一个气球时,将数组分为两部分.nums[0,n]在位置i戳破气球,nums[0,i-1]和nums[i+1,n]两部分
     * 但是! 两个逻辑上相连的元素却被隔开,如此计算将会变得复杂
     *
     * 可以另外换种分治方式. 我们不戳破 k 元素,避免给分出来的两个数组造成计算复杂
     * 对于 nums[i,j]先求出nums[i+1,k-1]和nums[k+1,j-1]这两部分的最大值.如此在戳破元素k,与元素k相邻的元素分别是nums[i]和nums[j]
     *
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        trace(0,nums,0);
        return max;
    }
    int max=Integer.MIN_VALUE;

    private void trace(int result,int[] nums,int count){
//        结束递归条件
        int len=nums.length;
        if(count==len){
            max=Math.max(max,result);
            return;
        }
//        选择列表
        for (int i = 0; i < len; i++) {
            if(nums[i]==-1){
                continue;
            }
//            做出选择
            int currNum=nums[i];
            nums[i]=-1;
//            找到该位置的前一个没有戳破的元素
            int leftIndex=i-1;
            while(leftIndex>=0&&nums[leftIndex]==-1){
                leftIndex--;
            }
            int rightIndex=i+1;
            while(rightIndex<len&&nums[rightIndex]==-1){
                rightIndex++;
            }
            int currMutil=(leftIndex==-1?1:nums[leftIndex])*currNum*(rightIndex==len?1:nums[rightIndex]);
            trace(result+currMutil,nums,count+1);
//            撤销选择
            nums[i]=currNum;
        }
    }

    /**
     * 这里使用分治思想解决
     * 分治重要在于如何用子问题来表示原来的问题
     * 如果考虑戳破元素k,将数组分为两个子数组. nums[i,k-1] 和 nums[k+1,j]
     * 戳破元素k后,k-1和k+1元素将变成相邻元素.此时难以计算
     *
     * 既然两个子问题都依赖k和两个边界,那我们在划分子问题时,k和两个边界我们都不戳破.
     * 求出 i+1,k-1和k+1,j-1 之间的解.这样子两个子问题就不会被依赖.
     * 并且如此划分后,只剩下元素k和两个边界没有被戳破.那我们可以使用两个子问题的解加上戳破k元素k额求出最大值
     *
     * 我们定义 def(i,j)表示不戳破i元素和j元素时,能获取到的最大值
     * 那么 def(i,j)=def(i,k)+def(k,j)+nums[i][j][k]  ,i+1<=k<=j-1
     *
     * 为了验证正确性,我们向上推演一次. def(i,i+2)=def(i,i+1)+def(i+1,i+2)+nums[i][i+1][i+2]
     * 所以最小子问题 def(i,i+1)应该等于0
     * @param nums
     * @return
     */
    public int maxCoins2(int[] nums) {
        int[] temp=new int[nums.length+2];
        System.arraycopy(nums,0,temp,1,nums.length);
        temp[0]=1;
        temp[nums.length+1]=1;
        return divide2(temp,0,temp.length-1);
    }

    private int divide2(int[] nums,int start,int end){
        if(start==end-1){
            return 0;
        }
//        if(start==end){
//            return nums[start];
//        }
        int result=0;
        for (int i = start+1; i <end; i++) {
            int left=divide2(nums,start,i);
            int right=divide2(nums,i,end);
            int curr=nums[start]*nums[i]*nums[end];
            result=Math.max(result,left+right+curr);
        }
        return result;
    }

    public int maxCoins_other(int[] nums) {
        //空数组处理
        if (nums == null) {
            return 0;
        }
        //加虚拟边界
        int length = nums.length;
        int[] nums2=new int[length+2];
        System.arraycopy(nums,0,nums2,1,length);
        nums2[0]=1;
        nums2[length+1]=1;
        length=nums2.length;
        //创建缓存数组
        int[][] cache=new int[length][length];
        //调用分治函数
        return maxCoins_other(nums2, length,cache);
    }

    private int maxCoins_other(int[] nums, int length,int[][] cache) {
        int max = maxCoins_other(nums, length, 0, length - 1,cache);
        return max;
    }
    private static int maxCoins_other(int[] nums, int length, int begin, int end,int[][] cache) {
        //回归条件，问题分解到最小子问题
        if (begin == end - 1) {
            return 0;
        }
//        //缓存，避免重复计算
//        if(cache[begin][end]!=0){
//            return cache[begin][end];
//        }
        //维护一个最大值
        int max = 0;
        //状态转移方程 def( i, j ) = max { def( i , k ) + def( k , j )+nums[ i ][ j ][ k ] } | i<k<j
        for (int i = begin + 1; i < end; i++) {
            int temp = maxCoins_other(nums, length, begin, i,cache)
                    + maxCoins_other(nums, length, i, end,cache)
                    + nums[begin] * nums[i] * nums[end];
            if (temp > max) {
                max = temp;
            }
        }
//        //缓存，避免重复计算
//        cache[begin][end]=max;
        return max;
    }

    /**
     * 动态规划
     * 分治处理已经能看出动态规划的样子了
     * @param nums
     * @return
     */
    public int maxCoins3(int[] nums) {
//        明确状态,起始位置i,结束位置j
//        明确dp定义,dp[i][j]表示不戳破i和j能得到的最大值
//        状态转移 dp[i][j]=max(dp[i][k]+dp[k][j]=nums[i][k][j]) ,i<k<j
//          得知依赖原则,dp[i][j] 依赖 dp[i][k]和dp[k][j] ,i<k<j
//        base case dp[i][i+1]=0
        int len=nums.length;
        int[] temp=new int[len+2];
        System.arraycopy(nums,0,temp,1,len);
        temp[0]=1;
        temp[len+1]=1;
        int[][] dp=new int[len+2][len+2];
        for (int i = len+1; i >=0; i--) {
            for (int j = i+1; j <len+2; j++) {
                int res=0;
                for (int k = i+1; k < j; k++) {
                    res=Math.max(res,dp[i][k]+dp[k][j]+temp[i]*temp[k]*temp[j]);
                }
                dp[i][j]=res;
            }
        }
        return dp[0][len+1];
    }


    public static void main(String[] args){
        No312_maxCoins test=new No312_maxCoins();
//        int[] nums={3,1,5,8};
        int[] nums={3,4,5,6,7,5,7,8,5,3,2,5};

        long start = System.currentTimeMillis();
        int i = test.maxCoins(nums);
        long end = System.currentTimeMillis();
        System.out.println(i);
        System.out.println("耗时:"+(end-start)+" ms");

        start = System.currentTimeMillis();
        int i1 = test.maxCoins2(nums);
        end = System.currentTimeMillis();
        System.out.println(i1);
        System.out.println("耗时:"+(end-start)+" ms");

        start = System.currentTimeMillis();
        int i2 = test.maxCoins_other(nums);
        end = System.currentTimeMillis();
        System.out.println(i2);
        System.out.println("耗时:"+(end-start)+" ms");

        start = System.currentTimeMillis();
        int i3 = test.maxCoins3(nums);
        end = System.currentTimeMillis();
        System.out.println(i3);
        System.out.println("耗时:"+(end-start)+" ms");
    }
}
