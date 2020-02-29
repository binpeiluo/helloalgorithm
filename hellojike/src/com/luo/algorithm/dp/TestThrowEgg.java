package com.luo.algorithm.dp;

/**
 * 高楼扔鸡蛋问题
 * 动态规划思想
 *
 * 题⽬是这样：你⾯前有⼀栋从 1 到 N 共 N 层的楼，然后给你 K 个鸡蛋
 * （ K ⾄少为 1）。现在确定这栋楼存在楼层 0 <= F <= N ，在这层楼将鸡
 * 蛋扔下去，鸡蛋恰好没摔碎（⾼于 F 的楼层都会碎，低于 F 的楼层都不
 * 会碎）。现在问你，最坏情况下，你⾄少要扔⼏次鸡蛋，才能确定这个楼层
 * F 呢？
 *
 * 最坏情况下,至少需要仍多少次鸡蛋?    如何理解
 * 在每层楼选择扔鸡蛋,扔的鸡蛋是否破碎的状态转移结果导致扔的次数不一样.这里需要考虑最坏的情况,
 * 然后在考虑至少需要扔多少次的情况
 */
public class TestThrowEgg {

    /**
     *
     * @param height 当前楼高
     * @param num   当前鸡蛋数量
     * @return
     */
    public int throwEgg(int height,int num){
//        思考是否符合动态规划(要素1 最优子结构,要素2 重叠子问题)
//            是否符合最优子结构,当前问题和子问题是否独立?
//              当前问题的最优解包含了子问题的最优解则说明该问题具最优子结构
//            是否具有重叠子元素
//              是否会重复计算子问题
//        思考这题目中,有什么状态.当前的状态有两个,第一是当前楼的高度,第二是当前鸡蛋数量
//        明确dp函数的定义，k=dp(h,n) 表示当楼层高h层，鸡蛋有n个时，最少需要k次才能确定楼高F
//        状态如何转移,在某一层楼高时,扔下鸡蛋,如果鸡蛋碎了,则说明楼高F小于当前楼高,鸡蛋-1.次数+1
//        加入鸡蛋没有碎,则说明楼高F大于等于F,鸡蛋数量不变.次数+1
//        思考base case。当鸡蛋=0时,不能再尝试，次数为0.当楼层数为0时不需要再尝试
        return dp(height,num);
    }

    private int dp(int height,int num){
        if(num==1)
            return height;
        if(height==0)
            return 0;
        int res=height;
        for (int i = 0; i < height; i++) {
//            假如鸡蛋碎了
            int v1 = dp(i, num - 1);
//            假如鸡蛋没碎
            int v2 = dp(height - i - 1, num);
            res=Math.min(res,Math.max(v1,v2)+1);
        }
        return res;
    }

    /**
     * 备忘录方式优化
     * @param height
     * @param num
     * @return
     */
    public int throwEgg2(int height,int num){
        int[][] buff=new int[height+1][num+1];
        for (int i = 0; i <= height; i++) {
            for (int j = 0; j <= num; j++) {
                buff[i][j]=-1;
            }
        }
        return dp2(buff,height,num);
    }

    private int dp2(int[][] buff,int height,int num){
        if(num==1)
            return height;
        if(height==0)
            return 0;
        if(buff[height][num]!=-1)
            return buff[height][num];
        int res=Integer.MAX_VALUE;
        for (int i = 0; i < height; i++) {
            res=Math.min(res,Math.max(
                    dp2(buff,i,num-1),//碎了
                    dp2(buff,height-i-1,num))//没碎
            );
        }
        if(res==-1)
            return -1;
        else{
            buff[height][num]=res+1;
            return res+1;
        }
    }

    /**
     * labuladong 思路
     *
     * @param k 鸡蛋个数
     * @param n 楼层高度
     * @return
     */
    public int throwEgg3(int k,int n){
        return dp3(k,n);
    }

    private int dp3(int k,int n){
//        base case
        if(k==1)
            return n;
        if(n==0)
            return 0;
        int res=Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
//            鸡蛋碎了
            int v1=dp3(k-1,i-1);
//            鸡蛋没碎
            int v2=dp3(k,n-i);
//            Math.min 计算是为了遍历发现至少的次数
//            Math.max 计算是为了考虑最坏情况
            res=Math.min(res,Math.max(v1,v2));
        }
        return res+1;
    }

    public static void main(String[] args){
        TestThrowEgg test=new TestThrowEgg();
        int num=2,height=100;

        int i1 = test.throwEgg2(height, num);
        System.out.println("my navie 优化:"+i1);
        int i2 = test.throwEgg3(num, height);
        System.out.println("labudalong method:"+i2);
        int i = test.throwEgg(height,num);
        System.out.println("navie method:"+i);

    }
}
