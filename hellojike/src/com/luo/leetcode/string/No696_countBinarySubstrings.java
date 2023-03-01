package com.luo.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 696. 计数二进制子串
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，
 * 并且这些子字符串中的所有0和所有1都是组合在一起的。
 * 重复出现的子串要计算它们出现的次数。
 *
 * 示例 1 :
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：
 * “0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 *
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 *
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 * 示例 2 :
 * 输入: "10101"
 * 输出: 4
 * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 * 注意：
 *
 * s.length 在1到50,000之间。
 * s 只包含“0”或“1”字符。
 *
 */
public class No696_countBinarySubstrings {

    /**
     * 0和1,相同数值要求组合在一起
     * 使用列表储存相同的数字的连续位数
     * 这个列表中的相邻数值的较小值即是这两部分数组能组成和的答案的值
     * @param s
     * @return
     */
    public int countBinarySubstrings(String s) {
        List<Integer> list=new ArrayList<>();
        int cnt=1;
        for (int i = 1,len=s.length(); i < len; i++) {
            if(s.charAt(i)!=s.charAt(i-1)){
                list.add(cnt);
                cnt=1;
            }else{
                cnt++;
            }
            if(i==len-1){
                list.add(cnt);
            }
        }
        int result=0;
        for (int i = 1; i < list.size(); i++) {
            result+=Math.min(list.get(i-1),list.get(i));
        }
        return result;
    }

    public static void main(String[] args){
        No696_countBinarySubstrings test=new No696_countBinarySubstrings();
//        String s="00110011";
        String s="00110";

        int i = test.countBinarySubstrings(s);
        System.out.println(i);
    }
}
