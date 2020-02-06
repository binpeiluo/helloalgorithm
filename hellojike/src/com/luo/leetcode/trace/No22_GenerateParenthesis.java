package com.luo.leetcode.trace;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
    22. 括号生成
    给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
        例如，给出 n = 3，生成结果为：
        [
            "((()))",
            "(()())",
            "(())()",
            "()(())",
            "()()()"
        ]
*/
public class No22_GenerateParenthesis {

    /**
     * 思路:
     *      回溯算法
     *      开括号和闭括号最大个数都是n个,所以当开括号个数小于最大个数时,可以生成一个开括号.
     *      当闭括号个数小于开括号个数时,可以生成一个闭括号
     *
     *      结束条件,做出选择,撤销选择
     *      因为string传入方法中引用的对象不会被修改,所以这里只需要进行做出选择操作即可,不需撤销选择
     *
     *      算法复杂度有点难计算
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result=new ArrayList<>();
        helper(result,"",0,0,n);
        return result;
    }

    private void helper(List<String> result,String currStr,int open,int close,int max){
        if(currStr.length()==max*2){
            result.add(currStr);
            return;
        }
        if(open<max)
            helper(result,currStr+"(",open+1,close,max);
        if(close<open)
            helper(result,currStr+")",open,close+1,max);
    }

    public static void main(String[] args){
        No22_GenerateParenthesis test=new No22_GenerateParenthesis();
        List<String> strings = test.generateParenthesis(2);
        System.out.println(strings);
    }
}
