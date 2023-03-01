package com.luo.sevendays.day2.stack;


import java.util.Arrays;
import java.util.Stack;

/**
 * 逆波兰表达式求值
 */
public class EvalRPN {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack=new Stack<>();
        for(String str:tokens){
            boolean isNumber = str.matches("-?\\d+");
            if(isNumber)
                stack.push(Integer.valueOf(str));
            else{
                Integer b = stack.pop();
                Integer a = stack.pop();
                if("*".equals(str)){
                    stack.push(a*b);
                }else if("/".equals(str)){
                    stack.push(a/b);
                }else if("+".equals(str)){
                    stack.push(a+b);
                }else{
                    stack.push(a-b);
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args){
        String[] strs= (String[]) Arrays.asList("10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+").toArray();
        EvalRPN test=new EvalRPN();
        int i = test.evalRPN(strs);
        System.out.println("evalRPN=="+i);
    }
}
