package com.luo.sevendays.day3;

import java.math.BigInteger;

/**
 * 计算x的平凡根
 */
public class MySqrt {
    public int mySqrt(int x) {
        int lo=1,hi=x;
        while(lo<=hi){
            int mid=lo+(hi-lo)/2;
            BigInteger temp=BigInteger.valueOf(mid);
            temp= temp.multiply(temp);
            int compareTo = temp.compareTo(BigInteger.valueOf(x));
            if(compareTo>0)
                hi=mid-1;
            else if(compareTo<0)
                lo=mid+1;
            else
                return mid;
        }
        return hi;
    }

    public static void main(String[] args){
        MySqrt test=new MySqrt();
        int i = test.mySqrt(2147395599);
        System.out.println("i=="+i);
    }
}
