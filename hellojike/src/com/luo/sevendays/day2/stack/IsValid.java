package com.luo.sevendays.day2.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 判断有效的括号问题
 */
public class IsValid {

    public boolean isValid(String s) {
        Map<Character,Character> tmp=new HashMap<>();
        tmp.put(')','(');
        tmp.put('}','{');
        tmp.put(']','[');
        Stack<Character> stack=new Stack<>();
        for(int i=0,len=s.length();i<len;i++){
            char c = s.charAt(i);
            if(!tmp.containsKey(c)){
                stack.push(c);
            }else{
                if(stack.isEmpty())
                    return false;
                Character pop = stack.pop();
                if(pop.equals(tmp.get(c)))
                    continue;
                else
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args){
        IsValid test=new IsValid();
        boolean valid = test.isValid("[]]");
        System.out.println("valid="+valid);
    }
}
