package com.luo.leetcode.stack;

import com.luo.util.CommonUtil;

import java.util.Stack;

/*

739. 每日温度
请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：
        要想观测到更高的气温，至少需要等待的天数。
        如果气温在这之后都不会升高，请在该位置用 0 来代替。

例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
        你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

提示：气温 列表长度的范围是 [1, 30000]。
        每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
*/
public class No739_dailyTemperatures {

    /**
     * 暴力法每次循环能计算出一个位置的值,但是每次循环能否保留有用的信息以供后边循环使用
     * 在当前值后寻找比该值大的第一个值,这种可以使用单调栈思想
     * 维护一个单调栈,栈内保存数组的索引,维护栈的大小顺序
     * 当寻找比该值大的值时,可以使用单调递减栈
     * @param temps
     * @return
     */
    public int[] dailyTemperatures(int[] temps) {
        int len=temps.length;
        int[] result=new int[len];
        Stack<Integer> stack=new Stack<>();
        for (int i = 0; i < len; i++) {
            if(stack.isEmpty()||temps[stack.peek()]>temps[i]){
                stack.push(i);
            }else{
                while(!stack.isEmpty()&&temps[stack.peek()]<temps[i]){
                    result[stack.peek()]=i-stack.peek();
                    stack.pop();
                }
                stack.push(i);
            }
        }
        return result;
    }

    public static void main(String[] args){
        No739_dailyTemperatures test=new No739_dailyTemperatures();
        int[] temps={73, 74, 75, 71, 69, 71, 76, 73};
        int[] ints = test.dailyTemperatures(temps);
        CommonUtil.display(ints);
    }


}
