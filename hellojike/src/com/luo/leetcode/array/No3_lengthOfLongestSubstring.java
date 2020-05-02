package com.luo.leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class No3_lengthOfLongestSubstring {

    /**
     * navie想法是暴力计算,两个指针，然后通过set判断有无重复的
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度；O(n)
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int len=s.length();
        Set<Character> set=new HashSet<>();
        int res=0;
        for (int i = 0; i < len; i++) {
            set.clear();
            int num=0;
            for (int j = i; j < len; j++) {
                if(set.contains(s.charAt(j)))
                    break;
                set.add(s.charAt(j));
                num++;
            }
            res=Math.max(res,num);
        }
        return res;
    }

    /**
     * 优化。滑动窗口法，两个指针指向两边，中间使用set保存字符，与naive解法的不同之处在于，右指针是尽量放大的。
     * 当两个指针指向的中间字符有重复时，移动左指针，同时再判断是否可以移动右指针。
     * naive方法是指定了一前一后两个指针，每次出现重复元素之后就移动左指针同时将右指针指向左指针
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s){
        int len=s.length();
        int right=-1;
        int res=0;
        Set<Character> set=new HashSet<>();
        for (int i = 0; i < len; i++) {
            if(i>0)
                set.remove(s.charAt(i-1));
            while(right+1<len&&!set.contains(s.charAt(right+1))){
                right++;
                set.add(s.charAt(right));
            }
            res=Math.max(res,right-i+1);
        }
        return res;
    }

    public static void main(String[] args){
        No3_lengthOfLongestSubstring test=new No3_lengthOfLongestSubstring();
//        String str="";
        String str="abcabcbb";
        int i = test.lengthOfLongestSubstring(str);
        System.out.println(i);

        int i1 = test.lengthOfLongestSubstring2(str);
        System.out.println(i1);
    }
}
