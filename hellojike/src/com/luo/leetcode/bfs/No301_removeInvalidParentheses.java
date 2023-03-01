package com.luo.leetcode.bfs;

import java.util.*;

/**
 * 301. 删除无效的括号
 * 删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。
 *
 * 说明: 输入可能包含了除 ( 和 ) 以外的字符。
 *
 * 示例 1:
 *
 * 输入: "()())()"
 * 输出: ["()()()", "(())()"]
 *
 */
public class No301_removeInvalidParentheses {

    /**
     * 抽象问题的能力,并且相信自己的想法
     * 使用bfs实现,每次在s中删除一个符号,然后判断是否有效.如过无效接着bfs
     *
     * 时间复杂度:   O(n!)
     * 空间复杂读:   O(n*n)
     *
     * 没有使用辅助set减少不必要的计算前timeout,使用后勉强ac
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        Set<String> resultSet=new HashSet<>();
//        没有优化会超时,使用一个辅助set
        Set<String> visited=new HashSet<>();
        if(valid(s)){
            resultSet.add(s);
            return new ArrayList<>(resultSet);
        }
        Queue<String> queue=new LinkedList<>();
        queue.offer(s);
        visited.add(s);
        boolean found=false;
        while(!queue.isEmpty()&&!found){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                int length = poll.length();
                for (int j = 0; j < length; j++) {
                    String sub=poll.substring(0,j)+poll.substring(j+1,length);
                    if(valid(sub)){
                        resultSet.add(sub);
                        found=true;
                    }else{
                        if(!visited.contains(sub)){
                            queue.offer(sub);
                            visited.add(sub);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(resultSet);
    }

    private boolean valid(String s){
        Stack<Character> stack=new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)=='(')
                stack.push('(');
            else if(s.charAt(i)==')'){
                if(stack.isEmpty())
                    return false;
                if( stack.pop() !='(')
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args){
        No301_removeInvalidParentheses test=new No301_removeInvalidParentheses();
//        String s="()())()";
//        String s="(a)())()";
//        String s=")(";
        String s="()((((((()l(";
        List<String> strings = test.removeInvalidParentheses(s);
        System.out.println(strings);
    }
}
