package com.luo.algorithm.string;

/**
 * 朴素的字符串匹配算法
 */
public class TestBF {
    public int bf(char[] a,int m,char[] b,int n){
        if(m==0||n==0)
            return -1;
        for(int i=0;i<=m-n;i++){
            int j=0;
            while(j<n&&a[i+j]==b[j])
                j++;
            if(j==n)
                return i;
        }
        return -1;
    }

    public static void main(String[] args){
        char[] a=new char[]{'G','C','T','T','C','T','G','C','G','G','C','T','T','C','T','G','C','G','G','C','T','T','C','T','G','C','G','G','C','T','T','C','T','G','C','G','G','C','T','T','C','T','G','C','G','G','C','T','T','C','T','G','C','G','G','C','T','T','C','T','G','C','G','G','C','T','T','C','T','G','C','G','G','C','T','T','C','T','G','C','G','G','C','T','T','C','T','G','C','G','G','C','T','T','C','T','G','C','G','G','C','T','T','C','T','G','C','G','G','C','T','T','C','T','G','C','G','C','T','T','C','T','G','C','G','C','T','T','C','T','G','C','G','C','T','T','C','T','G','C','G','C','T','T','C','T','G','C','G','C','T','T','C','T','G','C','G','C','T','T','C','T','G','C','T','A','C','C','T','T','T','T','G','C','G','C','G','C','G','C','G','C','G','G','A','A'};
        char[] b=new char[]{'C','C','T','T','T','T','G','C'};
        TestBF test=new TestBF();
        long startTime = System.currentTimeMillis();
        int bf = test.bf(a, a.length, b, b.length);
        long endTime = System.currentTimeMillis();
        System.out.println("bf=="+bf+", spend(ms):"+(endTime-startTime));
        System.out.println(String.valueOf(a,bf,b.length).equals(String.valueOf(b)));
    }
}
