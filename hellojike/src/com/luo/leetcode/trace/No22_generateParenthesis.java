package com.luo.leetcode.trace;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *  
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 */
public class No22_generateParenthesis {

    /**
     * navie方法
     * 想到的方法是回溯生成,
     * 回溯思想:
     *      trace(选择列表)
     *          if(结束条件) 结束
     *          for(选择列表)
     *              做出选择
     *              trace()
     *              撤销选择
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        if(n==0)
            return new ArrayList<>();
        boolean[] visited=new boolean[2*n];
        Set<String> result =new HashSet<>();
        helper(visited,result,new ArrayList<>());
        return new ArrayList<>(result);
    }

    /**
     * 使用这种方式会因为访问顺序的不同导致重复的数据,使用set避免
     * @param visited
     * @param result
     * @param currList
     */
    private void helper(boolean[] visited,Set<String> result,List<String> currList){
        if(currList.size()==visited.length && valid(currList) ){
            result.add(currList.stream().collect(Collectors.joining()));
            return;
        }
        for (int i = 0; i < visited.length; i++) {
            if(visited[i])
                continue;
            visited[i]=true;
            int size = currList.size();
            currList.add(size,i<visited.length/2?"(":")");
            helper(visited,result,currList);
            currList.remove(size);
            visited[i]=false;
        }
    }

    private boolean valid(List<String> currList){
        Stack<String> stack=new Stack<>();
        for (String c:currList){
            if(c.equals(")")){
                if(stack.isEmpty() || !stack.pop().equals("(") )
                    return false;
            }else
                stack.push(c);
        }
        return stack.isEmpty();
    }

    /**
     * 回溯的另一种实现,使用两个变量控制左右括号使用
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        List<String> result=new ArrayList<>();
        helper2(result,"",0,0,n);
        return result;
    }

    private void helper2(List<String> result,String curr,int open,int close,int max){
        if(open==close && open==max){
            result.add(curr);
            return;
        }
        if(open<max)
            helper2(result,curr+'(',open+1,close,max);
        if(close<open)
            helper2(result,curr+')',open,close+1,max);
    }


    public static void main(String[] args){
        No22_generateParenthesis test=new No22_generateParenthesis();
        int num=3;
        List<String> strings = test.generateParenthesis(num);
        System.out.println(strings);

        List<String> strings1 = test.generateParenthesis2(num);
        System.out.println(strings1);
    }
}
