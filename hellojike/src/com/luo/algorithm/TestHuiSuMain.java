package com.luo.algorithm;

public class TestHuiSuMain {

    static int maxW=Integer.MIN_VALUE;

    public static void main(String[] args){
        int[] items=new int[]{4,5,3,2,8};
        f(0,0,items,items.length,10);
        System.out.println("最大质量为,"+maxW);
    }

    /**
     *
     * @param i 当前考察角标
     * @param cw    当前背包重量
     * @param items 物品重量数组
     * @param n     物品个数
     * @param w     背包最大质量
     */
    static void f(int i,int cw,int[] items,int n,int w){
        if(cw==w||i==n){
            System.out.println("当前:[cw,n]="+cw+","+n+" ,质量="+cw);
            if(cw>maxW){
                System.out.println("当前[cw,maxW] ="+cw+","+maxW);
                maxW=cw;
            }
            return;
        }
        f(i+1,cw,items,n,w);
        if(cw+items[i]<=w){
            f(i+1,cw+items[i],items,n,w);
        }
    }
}
