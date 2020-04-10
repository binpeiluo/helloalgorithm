package com.luo.leetcode.string;

/**
 * 151. 翻转字符串里的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * 示例 1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括
 *
 */
public class No151_reverseWords {

    /**
     * 使用split分割
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] split = s.trim().split("\\s+");
        StringBuilder sb=new StringBuilder();
        for (int i = split.length-1; i >=0 ; i--) {
            if(i==0)
                sb.append(split[i]);
            else
                sb.append(split[i]+" ");
        }
        return sb.toString();
    }

    public static void main(String[] args){
        No151_reverseWords test=new No151_reverseWords();
        String str="the sky is blue";
        String s = test.reverseWords(str);
        System.out.println(s);
    }
}
