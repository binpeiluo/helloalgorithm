package com.luo.labuladong.highfrequence;

/**
 * 高频题目 计算koko吃香蕉的最慢速度
 *
 *     珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
 *         珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。
 *         如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
 *         珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *         返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 *         示例 1：
 *         输入: piles = [3,6,7,11], H = 8
 *         输出: 4
 *         示例 2：
 *         输入: piles = [30,11,23,4,20], H = 5
 *         输出: 30
 *         示例 3：
 *         输入: piles = [30,11,23,4,20], H = 6
 *         输出: 23
 *         提示：
 *         1 <= piles.length <= 10^4
 *         piles.length <= H <= 10^9
 *         1 <= piles[i] <= 10^9
 *
 *
 *   这类题目很典型,就是使用二分算法减低遍历的复杂度
 */
public class TestEatingSpeed {

    /**
     * 二分查找思路简单,但是细节比较多
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles,int h){
        if(h<piles.length)
            return -1;
        int maxCnt=0;
        for (int i = 0; i < piles.length; i++) {
            maxCnt=Math.max(maxCnt,piles[i]);
        }
//        这里关于最低和最大需要注意取值合理
        int min=1,max=maxCnt;
//        这里需要注意,min不可以等于max,不然可能出现死循环,当min=max时,且canEat(min)那么max指针不会移动
        while(min<max){
            int mid=min+(max-min)/2;
            if(canEat(piles,mid,h)){
//                当能吃完时,则吃的速度值<=mid
                max=mid;
            }else{
//                不能吃完时,吃的速度肯定需要>mid
                min=mid+1;
            }
        }
        return max;
    }

    private boolean canEat(int[] piles,int speed,int hour){
        int res=0;
        for (int i = 0; i < piles.length; i++) {
            res+=(piles[i]%speed==0)?piles[i]/speed:piles[i]/speed+1;
        }
        return res<=hour;
    }


}
