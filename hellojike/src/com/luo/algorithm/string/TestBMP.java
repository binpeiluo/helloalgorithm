package com.luo.algorithm.string;

/**
 * 字符串匹配
 */
public class TestBMP {

    private int[][] dp;
    private String pat;
    private int M;

    public TestBMP(String pat){
        this.M=pat.length();
        this.pat=pat;
        dp=new int[M][256];
        dp[0][pat.charAt(0)]=1;
        int X=0;
        for (int j = 1; j < M; j++) {
            for (int c = 0; c < 256; c++) {
                dp[j][c]=dp[X][c];
            }
            dp[j][pat.charAt(j)]=j+1;
            X=dp[X][pat.charAt(j)];
        }
    }

    public int search(String txt){
        for (int i = 0,j=0; i < txt.length(); i++) {
            j=dp[j][txt.charAt(i)];
            if(j==M)
                return i-M+1;
        }
        return -1;
    }

    public static void main(String[] args){
        TestBMP test=new TestBMP("abcd");
        String txt="aaaabcfffd";
        int search = test.search(txt);
        System.out.println(search);
    }
}
