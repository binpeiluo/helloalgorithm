package com.luo.leetcode.array;

/*
    1011. 在 D 天内送达包裹的能力
    传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
        传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
        返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
        示例 1：
            输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
            输出：15
            解释：
            船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
            第 1 天：1, 2, 3, 4, 5
            第 2 天：6, 7
            第 3 天：8
            第 4 天：9
            第 5 天：10
            请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
        示例 2：
            输入：weights = [3,2,2,4,1,4], D = 3
            输出：6
            解释：
            船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
            第 1 天：3, 2
            第 2 天：2, 4
            第 3 天：1, 4
        示例 3：
            输入：weights = [1,2,3,1,1], D = 4
            输出：3
            解释：
            第 1 天：1
            第 2 天：2
            第 3 天：3
            第 4 天：1, 1
        提示：
        1 <= D <= weights.length <= 50000
        1 <= weights[i] <= 500
*/
public class No1011_ShipWithinDays {

    /**
     * 思路:
     *      这道题跟875 koko吃香蕉类似,可以使用二分法.初始上下线分别为[max(weights),sum(weights)]
     *      使用双指针逼近最小的载重.
     *
     *      思路很清晰,却栽在计算 spendDays 函数上
     * @param weights
     * @param D
     * @return
     */
    public int shipWithinDays(int[] weights, int D) {
        if(weights.length<1)
            return 0;
        int left=0,right=0;
        for(int weight:weights){
            left=Math.max(left,weight);
            right+=weight;
        }
        right++;
        while(left<right){
            int mid=left+((right-left)>>1);
            int days = spendDays(weights, mid);
            if(days==D)
                right=mid;
            else if(days<D)
                right=mid;
            else if(days>D)
                left=mid+1;
        }
        return left;
    }

    private int spendDays(int[] weights,int playload){
        int days=0;
        int tmp=0;

        for(int weight:weights){
            if(tmp+weight>=playload){
                if(tmp+weight==playload){
                    tmp=0;
                    days++;
                    continue;
                }
                tmp=0;
                days++;
            }
            tmp+=weight;
        }
        return tmp==0?days:days+1;
    }

    public static void main(String[] args){
        No1011_ShipWithinDays test=new No1011_ShipWithinDays();
        int[] weights={1,2,3,1,1};
        int D=4;
        int i = test.shipWithinDays(weights, D);
        System.out.println(i);
    }
}
