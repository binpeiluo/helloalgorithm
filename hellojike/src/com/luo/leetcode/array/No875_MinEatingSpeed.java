package com.luo.leetcode.array;

/*
    875. 爱吃香蕉的珂珂
    珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
        珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。
        如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
        珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
        返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
        示例 1：
        输入: piles = [3,6,7,11], H = 8
        输出: 4
        示例 2：
        输入: piles = [30,11,23,4,20], H = 5
        输出: 30
        示例 3：
        输入: piles = [30,11,23,4,20], H = 6
        输出: 23
        提示：
        1 <= piles.length <= 10^4
        piles.length <= H <= 10^9
        1 <= piles[i] <= 10^9
*/
public class No875_MinEatingSpeed {
    public int minEatingSpeed(int[] piles, int H) {
        if(piles.length<1)
            return 0;
        int max=piles[0];
        for(int i=1;i<piles.length;i++){
            max=Math.max(max,piles[i]);
        }
        int left=1;
        int right=max;
        while(left<right){
            int mid=left+(right-left)/2;
            int hours = spendHour(piles, mid);
            if(hours<H){
//                这里究竟是right=mid还是right=mid-1呢
//                这里必须取right=mid,因为当前取mid符合条件,但是取mid-1juice有可能不符合条件了
                right=mid;
            }else if(hours>H){
                left=mid+1;
            }else{
                //这里求的是最小数度,所以应该取speed=mid-1 判断是否大于H,大于H则说明speed=mid是符合的最小值
//                不大于H则说明mid并不是符合条件的最小值,取mid-1继续循环
                if(mid==1||spendHour(piles,mid-1)>H)
                    return mid;
                else
                    right=mid-1;
            }
        }
        return left;
    }

    private int spendHour(int[] piles,int speed){
        int result=0;
        for(int pile:piles)
            result+=pile%speed==0?pile/speed:pile/speed+1;
        return result;
    }

    public int minEatingSpeed2(int[] piles,int H){
        if(piles.length<1) return 0;
        int max=piles[0];
        for(int pile:piles){
            max=Math.max(max,pile);
        }
        int left=1;
        int right=max+1;
        while(left<right){
            int mid=left+(right-left)/2;
            int hours = spendHour(piles, mid);
            if(hours<H){
                right=mid;
            }else if(hours>H){
                left=mid+1;
            }else if(hours==H){
                right=mid;
            }
        }
        return left;

    }


    public static void main(String[] args){
        No875_MinEatingSpeed test=new No875_MinEatingSpeed();

        int[] piles={332484035, 524908576, 855865114, 632922376, 222257295, 690155293, 112677673, 679580077, 337406589, 290818316, 877337160, 901728858, 679284947, 688210097, 692137887, 718203285, 629455728, 941802184};
        int target=823855818;

//        int[] piles={30,11,23,4,20};
//        int target=5;

        int i = test.minEatingSpeed(piles, target);
        System.out.println(i);

        int i1 = test.minEatingSpeed2(piles, target);
        System.out.println(i1);
    }
}
