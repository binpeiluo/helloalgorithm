package com.luo.labuladong.highfrequence;

import java.util.Stack;

/**
 * 高频问题
 *      校验括号合法性
 */
public class TestValid {

    public boolean isValid(String str){
        Stack<Character> stack=new Stack();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c=='['||c=='{'||c=='(')
                stack.push(c);
            else{
                if(stack.isEmpty())
                    return false;
                Character pop = stack.pop();
                if((pop=='('&&c==')')||(pop=='['&&c==']')||(pop=='{'&&c=='}'))
                    continue;
                else
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args){
        TestValid test=new TestValid();
        String str=")({{[()]}}";
        boolean valid = test.isValid(str);
        System.out.println(valid);
    }
}
