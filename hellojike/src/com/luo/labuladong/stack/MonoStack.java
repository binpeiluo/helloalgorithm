package com.luo.labuladong.stack;

import com.luo.util.CommonUtil;

import java.util.Stack;

/**
 * 单调栈
 */
@SuppressWarnings("Duplicates")
public class MonoStack {

    /**
     * 获取比当前元素更大的元素
     * @param nums
     * @return
     */
    int[] nextGreaterElement(int[] nums){
        int len=nums.length;
        int[] result=new int[len];
        Stack<Integer> stack=new Stack<>();
//        首先单调斩是指斩内元素大小有序 从小到大或者从大到小
//        1 那在这道题中如何考虑是使用单调递减栈还是单调递增栈？
//              因为如果栈顶为最大的话，那么遍历的时候每个元素只能看到最大的栈顶了。所以是单调递减栈
//        2 那么在这道题中如何遍历？
//              如果从前往后遍历，那根本无法从单调栈中获取比当前元素更大的元素。所以需要从后往前遍历
//        3 单调栈内储存什么？
//              储存元素本身是否可行？由于是获取更大大元素，不需要考虑位置，所以不需要储存元素角标
//        4 该算法的复杂度为多少？
//              for+while 看起来是O(n^2)
//              但是每个元素最多会压入栈一次 弹出一次 所以复杂度为O(n)
        for (int i = len-1; i >=0 ; i--) {
            int curr=nums[i];
//            循环找到第一个比当前元素大的元素
            while(!stack.isEmpty()&&curr>=stack.peek()){
                stack.pop();
            }
            result[i]=stack.isEmpty()?-1:stack.peek();
//           当前元素已经是最小的元素了 将当前元素压入栈 数组前边的元素可能能看到当前元素
            stack.push(curr);
        }
        return result;
    }

    /**
     * 变形 假设数组是环形的
     * 可以将数组扩大为两倍来计算
     * 更优雅的方式 可以使用 % 来模拟
     * @param nums
     * @return
     */
    int[] nextGreaterElement2(int[] nums){
        int len=nums.length;
        int[] result=new int[len];
        Stack<Integer> stack=new Stack<>();
        for (int i = len*2-1; i >=0 ; i--) {
            int curr=nums[i%len];
            while(!stack.isEmpty()&&curr>=stack.peek()){
                stack.pop();
            }
            result[i%len]=stack.isEmpty()?-1:stack.peek();
            stack.push(curr);
        }
        return result;
    }


    /**
     * 给你一个数组T，这个数组存放的是近几天的天气气温，你返回一个等长的数组，
     * 计算：对于每一天，你还要至少等多少天才能等到一个更暖和的气温；如果等不到那一天，填 0
     *
     * 其实就是前边的变形 将单调栈储存的元素从元素值修改成元素角标
     * @param nums
     * @return
     */
    int[] dailyTemperatures(int[] nums){
        int len=nums.length;
        int[] result=new int[len];
        Stack<Integer> stack=new Stack<>();
        for (int i = len-1; i >=0 ; i--) {
            int curr=nums[i];
            while(!stack.isEmpty()&&curr>=nums[stack.peek()]){
                stack.pop();
            }
            result[i]=stack.isEmpty()?0:(stack.peek()-i);
            stack.push(i);
        }
        return result;
    }



    public static void main(String[] args) {
        MonoStack test=new MonoStack();
        int[] nums = {2,1,2,4,3};

        int[] ints = test.nextGreaterElement(nums);
        CommonUtil.display(ints);

        int[] ints2 = test.nextGreaterElement2(nums);
        CommonUtil.display(ints2);

        int[] t={73,74,75,71,69,76};
        int[] ints1 = test.dailyTemperatures(t);
        CommonUtil.display(ints1);

    }
}
