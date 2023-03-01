package com.luo.leetcode.array;

import java.util.Stack;

/**
 * 两个二进制数数组相加
 */
public class ArraySum {

    public static int[] sum(int[] a,int[] b){
        int lena=a.length;
        int lenb=b.length;
        int carry=0;
        int index=0;
        Stack<Integer> stack=new Stack();
//        将三个while循环缩减为一个
        while(index<Math.max(lena,lenb)){
            int m=index<lena?a[lena-1-index]:0;
            int n=index<lenb?b[lenb-1-index]:0;
            int s=m+n+carry;
            carry=s>>1;
//
            stack.push(s%2);
            index++;
        }
//        进位处理
        if(carry>0){
            stack.push(carry);
        }
        int[] result=new int[stack.size()];
        for (int i=0;i<result.length;i++){
            result[i]=stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {

        int[] a={1,0,0};
        int[] b={1,1};
        int[] sum = sum(a, b);
        for (int i:sum){
            System.out.print(i);
        }

    }
}
