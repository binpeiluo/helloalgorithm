package com.luo.zhinan.stack_queue;

import com.luo.util.CommonUtil;

import java.util.Stack;

/**
 * 求最大子矩阵的大小, 一个M*N的矩阵, 数值都是0/1, 求全部是1的矩阵的大小
 */
public class MaxRectSize {

    /**
     * 没想到这个居然还和栈有关系
     *
     * 遍历每行数组, 记录数组每个元素之上都是1的个数, 然后在每个数组计算矩形的面积
     * 重点在于第二个步骤, 给定一个数组如何快速计算对应矩形的最大面积?
     *
     * 对于每个元素, 可以向左扩展多少格和向右扩展多少格, 取决于左边和右边第一个小于此值的元素位置. 遍历每个元素即可以计算矩形最大面积
     * @param rect
     * @return
     */
    public int maxRectSize(int[][] rect){
        int m=rect.length;
        int n=rect[0].length;

        int[] help=new int[n];
        int result=0;
        // 遍历每一行, 计算每个位置上边有多少个1, 储存与辅助数组
        for (int i = 0; i < m; i++) {
            int[] a= rect[i];
            for (int j = 0; j < n; j++) {
                if(a[j]==1){
                    help[j]++;
                }else{
                    help[j]=0;
                }
            }
            // 遍历辅助数组, 在每一行计算矩形的面积
            // 对于某个元素, 其扩展的面积取决于两侧比它小于元素的位置, 由此计算矩形面积
            // 直接通过向左和向右遍历知道第一个小于此的元素位置, 这样的时间复杂度为 O(M*N*N)

            // 使用栈优化? 如此时间复杂度可以优化到O(M*N)
            // 对于左侧需要找到第一个小于此元素的索引, 那么需要的辅助栈是单调递增栈
            // 当遍历到元素时, 需要将单调递增栈顶的元素弹出, 直到该元素处于栈顶
            Stack<Integer> leftStack=new Stack<>(), rightStack=new Stack<>();
            int[] leftA=new int[n], rightA=new int[n];
            for (int j = 0; j < n; j++) {
                while(!leftStack.isEmpty()&&help[j]<=help[leftStack.peek()]){
                    leftStack.pop();
                }
                // 如果在左侧, 当前元素是最小的, 那么可以扩展到最左侧
                leftA[j]=leftStack.isEmpty()?-1:leftStack.peek();
                leftStack.push(j);

                while(!rightStack.isEmpty()&&help[n-1-j]<=help[rightStack.peek()]){
                    rightStack.pop();
                }
                // 如果再右侧, 当前元素是最小的, 那么可以拓展到最右侧
                rightA[n-1-j]=rightStack.isEmpty()?n:rightStack.peek();
                rightStack.push(n-1-j);
            }

            // 注意这里计算两边扩展的长度
            for (int j = 0; j < n; j++) {
                int left=leftA[j], right=rightA[j];
                int len=1;
                // 左边扩展的长度等于当前元素到left位置的距离, 需要排序left
                len += j-left-1;
                len += right-j-1;
                result=Math.max(result, help[j]*len);
            }
        }
        return result;
    }

    public int maxRectSizeV2(int[][] rect){
        if(rect==null||rect.length==0||rect[0].length==0){
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[rect[0].length];
        for (int i = 0; i < rect.length; i++) {
            for (int j = 0; j < rect[0].length; j++) {
                height[j] = rect[i][j]==0?0:height[j]+1;
            }
            CommonUtil.display(height);
            int curr=maxRectFromBottom(height);
            System.out.println("curr rect is "+curr);
            System.out.println();
            maxArea=Math.max(maxArea, curr);
        }
        return maxArea;
    }

    /**
     * 计算矩形形成的最大面积
     * @param height
     * @return
     */
    private int maxRectFromBottom(int[] height){
        if(height==null||height.length==0){
            return 0;
        }
        int maxArea=0;
        Stack<Integer> stack=new Stack<>();
        // 遍历height数组, 每个元素的角标都会入栈
        for (int i = 0; i < height.length; i++) {
            // 如果当前元素小于等于栈顶元素, 那么将栈顶元素弹出来, 并计算其能形成的面积
            while(!stack.isEmpty()&&height[i]<=height[stack.peek()]){
                // 栈顶元素弹出
                int j=stack.pop();
                // 新的栈顶元素 没有的话就是-1
                int k=stack.isEmpty()?-1:stack.peek();
                // 弹出的栈顶元素能形成的面积, 如果新元素等于栈顶元素的话, 那么右边可以扩展到i, 如果小于的话, 可以扩展到i-1
                // 左边不可能有相同的元素, 所以左边最多扩展到新的栈顶元素的位置, 是k, 已经没有新的栈顶元素了, 那么补个-1代表
                int currArea=(i-1-k)*height[j];
                maxArea=Math.max(maxArea, currArea);
                System.out.printf("area=%d, i=%d, j=%d, k=%d\n", currArea, i, j, k);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int j=stack.pop();
            int k=stack.isEmpty()?-1:stack.peek();
            int currArea=(height.length-k-1)*height[j];
            maxArea=Math.max(maxArea, currArea);
        }
        return maxArea;
    }


    public static void main(String[] args) {
        MaxRectSize service =new MaxRectSize();
        int[][] rect={
                {1,0,1,1},
                {1,1,1,1},
                {1,1,1,0},
        };
        int result = service.maxRectSize(rect);
        System.out.println("result=="+result);
        int result2 = service.maxRectSizeV2(rect);
        System.out.println("result2=="+result2);
    }
}
