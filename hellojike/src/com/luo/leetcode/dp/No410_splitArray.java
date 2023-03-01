package com.luo.leetcode.dp;


import com.luo.util.CommonUtil;

import java.util.Arrays;

/**
 * 410. 分割数组的最大值
 * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。
 * 设计一个算法使得这 m 个子数组各自和的最大值最小。
 * 注意:
 * 数组长度 n 满足以下条件:
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * 示例:
 *
 * 输入:
 * nums = [7,2,5,10,8]
 * m = 2
 * 输出:
 * 18
 *
 * 解释:
 * 一共有四种方法将nums分割为2个子数组。
 * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 *
 */
@SuppressWarnings("Duplicates")
public class No410_splitArray {

    /**
     * 将数组分割成m段,求... 动态规划的味道
     * 令dp[i][j] 表示数组前i个元素分割成j段形成的j个数组和的最小数
     * 在进行状态转移时,我们可以考虑第j段的具体范围,我们可以枚举k,其中前k个元素被划分为j-1个数组,而第k+1到j个树则是第j段
     * 此时这j段子数组和的最大值,就等于 dp[k][j-1]与 sub(k+1,i) 中的较大数
     *
     * 故有状态转移方程 dp[i][j]=min( max(dp[k][j-1],sub(k+1,i)) ) 其中k>0 且 k<i
     * 由于不能出现空数组,所以合法状态必须有 i>=j,对于不合法的值我们设置成一个很大的数,
     * 那么当一旦尝试从不合法的状态转移时,那么max()将会是一个很大的值,就不会对外层的min产生影响
     *
     * 在上述的状态转移方程中,假如j==1,那么唯一的可能就是前边i个树被分成了一段
     *
     * @param nums
     * @param m
     * @return
     */
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[n + 1][m + 1];
//        设置无效值
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
//        方便计算
        int[] sub = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }
        dp[0][0] = 0;

//        dp[i][j]= min(max(dp[k][j-1],sub(k+1,i)))  0<k<i,i>=j

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
//                这里k的取值
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(
                            dp[i][j],
                            Math.max(
                                    dp[k][j - 1],
                                    sub[i] - sub[k]
                            )
                    );
                }
            }
        }

        CommonUtil.display(dp);
        return dp[n][m];
    }


    /**
     * 使...尽量小,二分搜索的味道
     * 我们选出一个指定值x,在线性时间内可以验证是否存在切分分案使得子数组的和都小于x
     * @param nums
     * @param m
     * @return
     */
    public int splitArray2(int[] nums, int m) {
        int len=nums.length;
        int left=0,right=0;
//        二分查找左边界等于数组中的最大值,右边界等于数组的和
        for (int i = 0; i < len; i++) {
            right+=nums[i];
            left=Math.max(left,nums[i]);
        }
        while(left<right){
            int mid=left+(right-left)/2;
            if(binseatch2(nums,mid,m)){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left;

    }

    private boolean binseatch2(int[] nums,int x,int m){
        int currSum=0;
        int splitCnt=0;
        for (int i = 0; i < nums.length; i++) {
//            假如按照数组和x分割出来的子数组个数已经等于m,那么x不满足
            if(splitCnt==m){
                return false;
            }
            if(currSum+nums[i]>x){
//                当前分割的数组和已经大于指定值
                splitCnt++;
                currSum=nums[i];
            }else {
//                当前合格的数组和还小于指定值
                currSum+=nums[i];
            }
            if(i==nums.length-1){
                splitCnt++;
            }
        }
        return splitCnt<=m;
    }

    /**
     * 动态规划
     * 思考如何使用动态规划,转移方程
     * 设定 dp[i][j] 表示数组的前i个数切分为j个数组后每个子数组和的最小值
     * dp[i][k] =min(dp[i-j][k-1],sub(j,i)), j<i
     * 依赖关系 dp[i][k] 依赖 dp[i-j][k-1]
     * base case dp[][0]=0
     * @param nums
     * @param m
     * @return
     */
    public int splitArray3(int[] nums, int m) {
        int len=nums.length;
        int[][] dp=new int[len+1][m+1];
        for (int i = 0; i <=len; i++) {
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        dp[0][0]=0;
//        前缀和
        int[] preSum=new int[len+1];
        for (int i = 1; i <=len; i++) {
            preSum[i]+=preSum[i-1]+nums[i-1];
        }
        for (int i = 1; i <=len; i++) {
            for (int j = 1; j <=Math.min(i,m); j++) {
//                为什么这里必须取到 k==i, k==0有没取到没关系
                for (int k = 0; k<=i; k++) {
                    dp[i][j]=Math.min(
                            dp[i][j],
                            Math.max(
                                    dp[i-k][j-1],
                                    preSum[i]-preSum[i-k]
                            )
                    );
                }

//                for (int k = 0; k < i; k++) {
//                    dp[i][j]=Math.min(
//                            dp[i][j],
//                            Math.max(
//                                    dp[k][j-1],
//                                    preSum[i]-preSum[k]
//                            )
//                    );
//                }
            }
        }
        return dp[len][m];
    }

    public static void main(String[] args){
        No410_splitArray test=new No410_splitArray();
        int[] nums = {7,2,5,10,8};
        int m=2;

//        int[] nums={1,2147483646};
//        int m=1;

        int i = test.splitArray(nums, m);
        System.out.println(i);

        int i1 = test.splitArray2(nums, m);
        System.out.println(i1);

        int i2 = test.splitArray3(nums, m);
        System.out.println(i2);
    }

}
