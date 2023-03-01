package com.luo.zhinan.stack_queue;

import java.util.Stack;

/**
 * 使用一个栈实现另一个栈的排序
 * 一个栈元素为整型, 向让其实现从栈顶到栈底从大到小的顺序. 可以借助另外一个栈, 不允许使用其他结构
 */
public class StackSort {

    /**
     * 使用一个辅助栈, 维持单调栈的特性, 然后将元素再次弹出到原栈中
     * 那么辅助栈自栈顶到栈底是从小到大的顺序, 将之弹出到原栈即是从大到小的顺序
     *
     * 从原栈弹出元素, 判断是辅助栈栈顶的大小关系,
     *      如果小于或者等于, 那么直接压入
     *      如果大于, 那么将辅助栈元素弹出并压入原栈, 知道元素小于辅助栈栈顶元素
     * @param s
     */
    public void stackSorted(Stack<Integer> s){
        // 自己写的如此丑陋
        Stack<Integer> help = new Stack<>();
        while(!s.isEmpty()){
            Integer pop = s.pop();
            if(help.isEmpty()) {
                help.push(pop);
            }else{
                Integer peek = help.peek();
                if(pop <= peek){
                    help.push(pop);
                }else{
                    while(pop > peek){
                        s.push(help.pop());
                        if (help.isEmpty()){
                            break;
                        }
                        peek = help.peek();
                    }
                    help.push(pop);
                }
            }
        }
        while(!help.isEmpty()){
            s.push(help.pop());
        }
    }

    public void stackSortedV2(Stack<Integer> s){
        Stack<Integer> help = new Stack<>();
        while(!s.isEmpty()){
            Integer pop = s.pop();
            while(!help.isEmpty()&&pop>help.peek()){
                s.push(help.pop());
            }
            help.push(pop);
        }
        while(!help.isEmpty()){
            s.push(help.pop());
        }
    }

    public static void main(String[] args) {
        StackSort sort = new StackSort();
        Stack<Integer> s = new Stack<>();
        s.push(5);
        s.push(3);
        s.push(1);
        s.push(2);
        s.push(0);
        s.push(4);
        System.out.println(s);
//        sort.stackSorted(s);
        sort.stackSortedV2(s);
        System.out.println(s);
    }
}
