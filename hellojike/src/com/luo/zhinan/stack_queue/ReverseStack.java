package com.luo.zhinan.stack_queue;

import java.util.Stack;

/**
 * 如何仅用递归函数以及栈操作逆序一个栈
 * 例如将1~5压入栈中, 从栈顶到栈底是5~1, 那么逆序后的栈从栈顶到栈底是1~5, 只能使用递归以及栈操作
 */
public class ReverseStack {

    /**
     * 递归思想, 如果拿到栈顶的元素, 那么将这个元素和剩下的栈互换位置, 剩下的工作就是对剩下的栈进行逆序
     * @param s
     */
    public void reverseStack(Stack<Integer> s){
        if(s.isEmpty()){
            return;
        }
        // 取出栈顶元素
        Integer pop = s.pop();
        // 将剩下的栈进行逆序
        reverseStack(s);
        // 交换pop和剩下栈的顺序

        s.push(pop);


        // 新思路: 获取到栈底元素并且移除, 接着将剩下的栈逆序, 接着将之前的栈底元素压入
    }

    public void reverseStackV2(Stack<Integer> s){
        if(s.isEmpty()){
            return;
        }
        Integer floor = removeAndGetStackFloor(s);
        reverseStackV2(s);
        s.push(floor);
    }

    /**
     * 获取并删除栈底元素
     * @param s
     * @return
     */
    private Integer removeAndGetStackFloor(Stack<Integer> s){
        // 将当前栈顶弹出
        Integer pop = s.pop();
        if(s.isEmpty()){
            return pop;
        }
        // 获取并删除栈底元素
        Integer floor = removeAndGetStackFloor(s);
        // 将栈顶元素重新压入
        s.push(pop);
        return floor;
    }


    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        int[] a = {1, 2, 3, 4, 5};
        for(int i : a){
            s.push(i);
        }
        System.out.println(s);
        ReverseStack instance = new ReverseStack();
        instance.reverseStackV2(s);
        System.out.println(s);
    }
}
