package com.luo.labuladong.highfrequence;

/**
 * 第四章高频之接雨水
 */
public class TestTrap {

    /**
     * 接雨水 navie解法
     *      一行一行计算  分别在左右边找到高于等于这行的列,然后计算每一行即可储存多少
     *      一列一列计算  分别在每一列找到左右边找到其最高值,这一列能储存的值取决于其较小值-自身高度
     *
     * 时间复杂度:   O(N^2)
     * 空间复杂度:   O(1)
     * @param heights
     * @return
     */
    public int trapNavie(int[] heights){
        int len=heights.length;
        if(len<3)
            return 0;
        int res=0;
//        计算每一列
        for (int i = 1; i < len-1; i++) {
//            寻找左边最大值
            int leftMax=0;
            for(int j=i-1;j>=0;j--){
                leftMax=Math.max(leftMax,heights[j]);
            }
//            寻找右边最大值
            int rightMax=0;
            for(int j=i+1;j<len;j++){
                rightMax=Math.max(rightMax,heights[j]);
            }
            int curr=Math.min(leftMax,rightMax)-heights[i];
            res+=curr>0?curr:0;
        }
        return res;
    }

    /**
     * 根据navie方法,每次计算一列需要向左右获取最高值,这部分可以提前计算
     *
     * 时间复杂度:   O(n)
     * 空间复杂度:   O(n)
     * @param heights
     * @return
     */
    public int trapMem(int[] heights){
        int len=heights.length;
        if(len<3)
            return 0;
        int res=0;
        int[] leftMax=new int[len];
        int max=0;
        for (int i = 0; i < len; i++) {
            leftMax[i]=Math.max(max,heights[i]);
        }
        int[] rightMax=new int[len];
        max=0;
        for (int i = len-1; i >=0; i--) {
            rightMax[i]=Math.max(max,heights[i]);
        }
        for (int i = 1; i < len - 1; i++) {
            int leftM=leftMax[i-1];
            int rightM=rightMax[i+1];
            int curr=Math.min(leftM,rightM)-heights[i];
            res+=curr>0?curr:0;
        }
        return res;
    }

    /**
     * 再次优化,前边提前计算,需要使用大小为n的数组储存结果
     *
     * @param heights
     * @return
     */
    public int trapOptimize(int[] heights){
        if (heights.length<3) return 0;
        int len = heights.length;
        int leftMax=0,rightMax=0;
        int left=0,right=len-1;
        int res=0;
        while(left<=right){
            leftMax=Math.max(leftMax,heights[left]);
            rightMax=Math.max(rightMax,heights[right]);
            if(leftMax>rightMax){
                int curr=rightMax-heights[right];
                res+=curr>0?curr:0;
                right--;
            }else{
                int curr=leftMax-heights[left];
                res+=curr>0?curr:0;
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args){
        TestTrap test=new TestTrap();
        int[] heights={0,1,0,2,1,0,1,3,2,1,2,1};
        int trap = test.trapNavie(heights);
        System.out.println(trap);

        int i = test.trapNavie(heights);
        System.out.println(i);

        int i1 = test.trapOptimize(heights);
        System.out.println(i1);
    }
}
