package com.luo.algorithm.binsearch;

public class MinEatingSpeed {
    public int minEatingSpeed(int[] piles, int H) {
        if(H<piles.length)
            return -1;
        int maxPile=0;
        for(int pile:piles){
            maxPile=Math.max(pile,maxPile);
        }
//        二分
        int min=1,max=maxPile;
        while(min<=max){
            int mid=min+(max-min)/2;
            if(mid<=0)
                break;
            int need=helper(piles,mid);
            if(need==H &&mid-1>0&& helper(piles,mid-1)>H)
                return mid;
            else if(need>H){
                min=mid+1;
            }else{
                max=mid-1;
            }
        }
//        为什么是min
        return min;
    }

    /**
     * 以speed速度吃需要多少小时
     * @param piles
     * @param speed
     * @return
     */
    private int helper(int[] piles,int speed){
        int res=0;
        for(int pile:piles){
            res+=(pile%speed==0?pile/speed:(pile/speed+1));
        }
        return res;
    }

    public static void main(String[] args){
        MinEatingSpeed test=new MinEatingSpeed();
        int[] piles={312884470};
        int H=968709470;
        int res=test.minEatingSpeed(piles,H);
        System.out.println("res="+res);
    }


}
