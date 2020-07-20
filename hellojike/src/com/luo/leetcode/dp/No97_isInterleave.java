package com.luo.leetcode.dp;

/**
 * 97. 交错字符串
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 *
 * 示例 1:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 *
 */
@SuppressWarnings("Duplicates")
public class No97_isInterleave {

    /**
     * naive想法 双指针
     * 思路不正确
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1=s1.length();
        int len2=s2.length();
        int len3=s3.length();
        if(len1+len2!=len3){
            return false;
        }
        int p=0,q=0;
        boolean result=true;
        for (int i = 0; i < len3; i++) {
            if(p==len1){
                result=s3.charAt(i)==s2.charAt(q);
                q++;
            }else if(q==len2){
                result=s3.charAt(i)==s1.charAt(p);
                p++;
            }else{
                if(s3.charAt(i)==s1.charAt(p)||s3.charAt(i)==s2.charAt(q)){
                    if(s1.charAt(p)==s2.charAt(q) ){
                        if(len1-p<len2-q){
                            q++;
                        }else{
                            q++;
                        }
                    }else{
                        if(s3.charAt(i)==s1.charAt(p)){
                            p++;
                        }else if(s3.charAt(i)==s2.charAt(q)){
                            q++;
                        }else{
                            result=false;
                        }
                    }
                }else{
                    return false;
                }
            }
            if(!result){
                return false;
            }
        }
        return true;
    }

    /**
     * 既然双指针并不能得到正确的解.另辟蹊径
     * 使用dfs,没有使用优化很容易超时
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        int len1=s1.length();
        int len2=s2.length();
        int len3=s3.length();
        if(len1+len2!=len3){
            return false;
        }
        return helper2(s1,0,s2,0,s3,0);
    }

    private boolean helper2(String s1,int i1, String s2,int i2, String s3,int i3){
        if(i3==s3.length()){
            return true;
        }
        boolean result=false;
        if(i1<s1.length() && s3.charAt(i3)==s1.charAt(i1)){
            result=helper2(s1,i1+1,s2,i2,s3,i3+1);
        }
        if(i2<s2.length() && s3.charAt(i3)==s2.charAt(i2)){
            result|=helper2(s1,i1,s2,i2+1,s3,i3+1);
        }
        return result;
    }

    /**
     * dfs 没有优化很容易超时
     * 加入缓存
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave3(String s1, String s2, String s3) {
        int len1=s1.length();
        int len2=s2.length();
        int len3=s3.length();
        if(len1+len2!=len3){
            return false;
        }
        Boolean[][] cache=new Boolean[len1+1][len2+1];
        return helper3(cache,s1,0,s2,0,s3,0);
    }

    private boolean helper3(Boolean[][] cache,String s1,int i1, String s2,int i2, String s3,int i3){
        if(i3==s3.length()){
            return true;
        }
        if(cache[i1][i2]!=null){
            return cache[i1][i2];
        }
        boolean result=false;
        if(i1<s1.length() && s1.charAt(i1)==s3.charAt(i3)){
            result=helper3(cache,s1,i1+1,s2,i2,s3,i3+1);
        }
        if(i2<s2.length() && s2.charAt(i2)==s3.charAt(i3)){
            result|=helper3(cache,s1,i1,s2,i2+1,s3,i3+1);
        }
        cache[i1][i2]=result;
        return result;
    }

    /**
     * dfs回溯法可以看出递推公式.
     * 试一下动态规划
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave4(String s1, String s2, String s3) {
        int len1=s1.length();
        int len2=s2.length();
        int len3=s3.length();
        if(len1+len2!=len3){
            return false;
        }
//        定义 dp[i][j]表示使用s1的前i个字符和s2的前j个字符能否拼出s3的前i+j个字符
//        状态转移 假如 s1[0,i) ,s2[0,j) 能拼凑出 s3[0,i+j)
//                  那么s3最后一个字符s3[i+j-1]肯定等于s1[i-1]或者s2[j-1]
        boolean[][] dp=new boolean[len1+1][len2+1];
//        base case
//        注意 base case 不是dp[i][0]=true,dp[0][j]=true
        dp[0][0]=true;
        for (int i = 0; i <=len1; i++) {
            for (int j = 0; j <=len2; j++) {
                if(i>0 && s3.charAt(i+j-1)==s1.charAt(i-1)){
                    dp[i][j]=dp[i-1][j];
                }
//                这里是或的关系,所以需要使用|操作符
                if(j>0 && s3.charAt(i+j-1)==s2.charAt(j-1)){
                    dp[i][j]|=dp[i][j-1];
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args){
        No97_isInterleave test=new No97_isInterleave();
//        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
//        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc";
        String s1="aabc" ,s2="abad" ,s3="aabcabad";

        boolean interleave = test.isInterleave(s1, s2, s3);
        System.out.println(interleave);

        boolean interleave2 = test.isInterleave2(s1, s2, s3);
        System.out.println(interleave2);

        boolean interleave3 = test.isInterleave3(s1, s2, s3);
        System.out.println(interleave3);

        boolean interleave4 = test.isInterleave4(s1, s2, s3);
        System.out.println(interleave4);
    }
}
