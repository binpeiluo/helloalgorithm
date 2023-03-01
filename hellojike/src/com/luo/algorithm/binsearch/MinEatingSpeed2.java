package com.luo.algorithm.binsearch;

public class MinEatingSpeed2 {
    public int minEatingSpeed(int[] piles, int H){
        int min=1,max=1_000_000_000;
        while(min<max){
//            mid需要尽量小,所以不可以在判断条件中返回
//            当达到min>=max时结束循环
            int mid=min+(max-min)/2;
            if(helper(piles,H,mid))
                max=mid-1;
            else
                min=mid+1;
        }
        //这里只能返回min
        return min;
    }

    private boolean helper(int[] piles, int H,int speed){
        int res=0;
        for(int pile:piles){
//            通过这种方式巧妙计算
            res+=(pile-1)/speed+1;
        }
        return res<=H;
    }

    public static void main(String[] args){
        MinEatingSpeed2 test=new MinEatingSpeed2();
        int[] piles={312884470};
        int H=968709470;
        int res=test.minEatingSpeed(piles,H);
        System.out.println("res="+res);
    }
}
