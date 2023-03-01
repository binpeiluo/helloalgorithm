package com.luo.algorithm.dp;

/**
 * 最长递增子序列
 */
public class LISMain {
    public int lis(int[] data,int len){
//        重复子问题
//        最优子结构(子问题之间互相独立)
//        转移方程

//        明确dp数组的定义
//        value 表示 以index 为结尾的最长子序列长度
//        状态转移方程
//        f(n)    = 1 ,n=0
//                =1+max(f(i)|0<=i<=len) ,a[n]>a[i]

        int[] dp=new int[len];
        for(int i=0;i<len;i++){
            dp[i]=1;
        }
        for(int i=1;i<len;i++){
            for(int j=i;j>=0;j--){
                if(data[i]>data[j])
                    dp[i]=Math.max(dp[i],1+dp[j]);
            }
        }
        int res=1;
        for(int i=0;i<len;i++){
            res=Math.max(res,dp[i]);
        }
        return res;
    }

    public static void main(String[] ars){
        LISMain test=new LISMain();
        int[] data={10,9,2,5,3,7,101,18};
        int lis = test.lis(data, data.length);
        System.out.println("lis=="+lis);
    }
}
