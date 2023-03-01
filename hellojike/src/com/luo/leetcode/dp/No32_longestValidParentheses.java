package com.luo.leetcode.dp;

import java.util.Stack;

/**
 * 32. 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 */
@SuppressWarnings("Duplicates")
public class No32_longestValidParentheses {

    /**
     * 动态规划特性
     *  重叠子问题+最优子结构
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
//        明确状态
//        明确dp定义,dp[i]表示以字符串角标i字符为结尾的字符串的有效括号长度
//        状态转移 有效括号一定是以)结尾的,只需要关注)即可
//          if s[i]==')' and s[i-1]=='(' ==> dp[i]=dp[i-2]+2    dp[i-2]表示前边也有有效括号,这部分可以连接起来
//          if s[i]==')' and s[i-1]==')' and s[i-dp[i-1]-1]=='(' ==> dp[i]=dp[i-1]+2+dp[i-dp[i-1]-1-1]
//        base case

        int len=s.length();
        int[] dp=new int[len];
        int result=0;
        for (int i = 1; i < len; i++) {
            if(s.charAt(i)==')' && s.charAt(i-1)=='('){
//                这里的 () 可能是更长有效括号的一部分,需要判断越界
                dp[i]=2+(i-2>=0?dp[i-2]:0);
            }else if(s.charAt(i)==')' && s.charAt(i-1)==')'){
                if(i-dp[i-1]-1>=0&&s.charAt(i-dp[i-1]-1)=='('){
//                    这里同样需要判断越界
                    dp[i]=dp[i-1]+2+(i-dp[i-1]-2>=0?dp[i-dp[i-1]-2]:0);
                }
            }
            result=Math.max(result,dp[i]);
        }
        return result;
    }

    public int longestValidParentheses2(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
//        始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」
//        描述不对呀  感觉是最后一个没有被匹配的括号
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    public static void main(String[] args){
        No32_longestValidParentheses test=new No32_longestValidParentheses();
//        String s="(()";
//        String s=")()())";
//        String s="())";
        String s="(()())";
        int i = test.longestValidParentheses(s);
        System.out.println(i);
    }
}
