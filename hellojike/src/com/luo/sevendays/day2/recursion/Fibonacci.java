package com.luo.sevendays.day2.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现斐波那契数列
 */
public class Fibonacci {
//    fibonacci 45,1836311903  耗时(ms):8081
    int fibonacci(int n){
        if(n==1||n==2)
            return n;
        return fibonacci(n-1)+fibonacci(n-2);
    }

    Map<Integer,Integer> map=new HashMap<>();
    int fibonacciCache(int n){
        if(n==1||n==2)
            return n;
        if(map.containsKey(n))
            return map.get(n);
        int res=fibonacciCache(n-1)+fibonacciCache(n-2);
        map.put(n,res);
        return res;
    }

    public static void main(String[] args){
        Fibonacci test=new Fibonacci();
        for(int i=3,len=100;i<len;i++){
            int n=i;
            long startTime = System.currentTimeMillis();
//            int fibonacci = test.fibonacci(n);
            int fibonacci = test.fibonacciCache(n);
            long endTime = System.currentTimeMillis();
            System.out.println("fibonacci "+n+","+fibonacci+"  耗时(ms):"+(endTime-startTime));
        }
    }
}
