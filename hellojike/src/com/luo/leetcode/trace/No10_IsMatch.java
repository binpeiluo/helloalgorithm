package com.luo.leetcode.trace;

/*
    10. 正则表达式匹配
    给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
        '.' 匹配任意单个字符
        '*' 匹配零个或多个前面的那一个元素
        所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
        说明:
            s 可能为空，且只包含从 a-z 的小写字母。
            p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
        示例 1:
            输入:
            s = "aa"
            p = "a"
            输出: false
            解释: "a" 无法匹配 "aa" 整个字符串。
        示例 2:
            输入:
            s = "aa"
            p = "a*"
            输出: true
            解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
        示例 3:
            输入:
            s = "ab"
            p = ".*"
            输出: true
            解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
        示例 4:
            输入:
            s = "aab"
            p = "c*a*b"
            输出: true
            解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
        示例 5:
            输入:
            s = "mississippi"
            p = "mis*is*p*."
            输出: false
*/

public class No10_IsMatch {
    /**
     * 思路:
     *      利用回溯算法如何思考此题
     *      难点在于 '*' 符号的处理,取前边的字符,可以出现0~无数次
     *      每次可以比较两个字符串前边第一个的字符,然后判断第二个字符是否为*,是的话则有两种情况
     *          原字符串中并没有出现该x*的字符,此时可以跳过这两位模式串
     *          原字符串中出现了一次x,那么继续原字符串接下来的部分和模式串(原字符串后边还可以会在再出现x*)
     *
     *      算法复杂度计算有点复杂
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if(p.isEmpty())
            return s.isEmpty();
        boolean firstMatch=!s.isEmpty()&&(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.');
        if(p.length()>=2&&p.charAt(1)=='*'){
            return isMatch(s,p.substring(2))
                    || (firstMatch && isMatch(s.substring(1),p));
        }else{
            return firstMatch&&isMatch(s.substring(1),p.substring(1));
        }
    }

    public static void main(String[] args){
        No10_IsMatch test=new No10_IsMatch();
        String s="aa";
        String p="a*";
        boolean match = test.isMatch(s, p);
        System.out.println(match);

    }
}
