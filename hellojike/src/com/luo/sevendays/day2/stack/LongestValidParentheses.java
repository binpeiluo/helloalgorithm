package com.luo.sevendays.day2.stack;

import com.luo.util.CommonUtil;

import java.util.Stack;

/**
 * 求最长有效括号的长度
 */
public class LongestValidParentheses {
    /**
     * 暴力方式破解
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int max=0;
        for(int i=0;i<s.length()-1;i++){
            for(int j=i+2;j<=s.length();j+=2){
                if(valid(s.substring(i,j))){
                    max=Math.max(max,j-i);
                }
            }
        }
        return max;
    }

    private boolean valid(String str){
        Stack<Character> stack=new Stack<>();
        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);
            if(c==')'){
                if(stack.isEmpty())
                    return false;
                if(stack.pop().equals('('))
                    continue;
                return false;
            }else
                stack.push(c);
        }
        return stack.isEmpty();
    }

    /**
     * 动态规划
     * @param s
     * @return
     */
    public int longestValidParenthesesDp(String s){
        int maxans = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    /**
     * 动态规划 自己写
     * @param s
     * @return
     */
    public int longestValidParenthesesDp2(String s){
        int[] temp=new int[s.length()];
        int max=0;
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==')'){
                if(s.charAt(i-1)=='('){
                    temp[i]=(i>=2?temp[i-2]:0)+2;
                }else if(temp[i-1]>0&&s.charAt(i-1-temp[i-1])=='('){
                    temp[i]=temp[i-1]+2+((i-temp[i-1]>=2)?temp[i-temp[i-1]-2]:0);
                }
                max=Math.max(max,temp[i]);
            }
        }
        return max;
    }

    public int longestValidParenthesesStack(String s){
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
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
        LongestValidParentheses test=new LongestValidParentheses();
        String str="()()()((())(())(()((()))";
        int i = test.longestValidParentheses(str);
        System.out.println("LongestValidParentheses="+i);

        int i1 = test.longestValidParenthesesDp(str);
        System.out.println("longestValidParenthesesDp="+i1);

        int i2 = test.longestValidParenthesesDp2(str);
        System.out.println("longestValidParenthesesDp2="+i2);
    }
}
