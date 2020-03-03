package com.luo.labuladong.highfrequence;

/**
 *  高频题目
 *  二分查找问题
 */
public class TestShipWithDays {

    /**
     * 跟koko吃香蕉很类似,都是找符合要求的最小值
     * @param weights
     * @param D
     * @return
     */
    public int shipWithinDays(int[] weights, int D){
        if(weights.length<=1)
            return weights[0];
        int min=1,max=Integer.MAX_VALUE;
        for (int i = 0; i < weights.length; i++) {
            min=Math.max(min,weights[i]);
        }
        while(min<max){
            int mid=min+(max-min)/2;
            if(canFinish(weights,mid,D)){
                max=mid;
            }else{
                min=mid+1;
            }
        }
        return max;
    }

    private boolean canFinish(int[] weights,int speed, int D){
        int curr=0;
        int res=0;
        for (int i = 0; i < weights.length; i++) {
            if(curr+weights[i]<=speed){
                curr+=weights[i];
                continue;
            }
            else{
                res++;
                curr=weights[i];
            }
        }
        if(curr!=0)
            res++;
        return res<=D;
    }

    public static void main(String[] args){
        TestShipWithDays test=new TestShipWithDays();
//        int[] weights={1,2,3,4,5,6,7,8,9,10};
//        int D=5;
        int[] weights={1,2,3,1,1};
        int D=4;
        int i = test.shipWithinDays(weights, D);
        System.out.println(i);

        boolean b = test.canFinish(weights, 20, D);
        System.out.println(b);
    }
}
