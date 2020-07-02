package com.luo.leetcode.dp;

/**
 * 718. 最长重复子数组
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * 示例：
 * 输入：
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出：3
 * 解释：
 * 长度最长的公共子数组是 [3, 2, 1] 。
 * 提示：
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 *
 */
public class No718_findLength {

    /**
     * 想一想就是dp,动态规划.类似于求字符串的最长公共子串长度
     * @param A
     * @param B
     * @return
     */
    public int findLength(int[] A, int[] B) {
//        明确状态,数组A的前i个元素和数组B的前j个元素
//        明确dp数组定义 dp[i][j] 表示数组A的第i个元素结尾和数组B的第j个元素结尾的公共子串长度
//        状态转移 dp[i][j]=dp[i-1][j-1]+1 if A[i]==B[j]
//                      =0
//        base case dp[0][j]=0,dp[i][0]=0

        int lenA=A.length,lenB=B.length;
        int[][] dp=new int[lenA+1][lenB+1];
        int result=0;
        for (int i = 1; i < lenA+1; i++) {
            for (int j = 1; j < lenB+1; j++) {
                if(A[i-1]==B[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                    result=Math.max(result,dp[i][j]);
                }
            }
        }
        return result;
    }


    public int findLength2(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int ret = 0;
//        固定B数组,移动A数组
        for (int i = 0; i < n; i++) {
            int len = Math.min(m, n - i);
            int maxlen = maxLength2(A, B, i, 0, len);
            ret = Math.max(ret, maxlen);
        }
        for (int i = 0; i < m; i++) {
            int len = Math.min(n, m - i);
            int maxlen = maxLength2(A, B, 0, i, len);
            ret = Math.max(ret, maxlen);
        }
        return ret;
    }

    /**
     * 从A数组的startA位置和B数组的startB位置对齐,计算公共长度
     * 使用len防止越界
     * @param A
     * @param B
     * @param startA
     * @param startB
     * @param len
     * @return
     */
    private int maxLength2(int[] A, int[] B, int startA, int startB, int len) {
        int ret = 0, k = 0;
        for (int i = 0; i < len; i++) {
            if (A[startA + i] == B[startB + i]) {
                k++;
            } else {
                k = 0;
            }
            ret = Math.max(ret, k);
        }
        return ret;
    }

    public static void main(String[] args){
        No718_findLength test=new No718_findLength();
//        int[] A={1,2,3,2,1};
//        int[] B={1,2,3,2,1,4,7};

        int[] A={1,2,3,2,1};
        int[] B={4,3,2,1,5};

        int length = test.findLength(A, B);
        System.out.println(length);

        int length2 = test.findLength2(A, B);
        System.out.println(length2);
    }
}
