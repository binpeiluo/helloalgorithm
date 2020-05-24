package com.luo.algorithm.doublepointer.slidepointer;

import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口系列
 * 给定一个字符串,请找出不包含重复字符的最长子串的长度
 */
public class LengthOfLongestSubstring {

    /**
     * 子串问题,滑动窗口.整!
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s){

        Map<Character,Integer> window=new HashMap<>();

        int left=0,right=0;
        int result=0;

        while(right<s.length()){
            char c = s.charAt(right);
            right++;
            window.put(c,window.getOrDefault(c,0)+1);

            while(window.get(c)>1){
                char d = s.charAt(left);
                left++;
                window.put(d,window.get(d)-1);
            }

            result=Math.max(result,right-left);
        }
        return result;
    }


    public int lengthOfLongestSubstring2(String s){
        Map<Character,Integer> window=new HashMap<>();
        int result=0;
        int left=0,right=0;
        while(right<s.length()){
            char c = s.charAt(right);
            window.put(c,window.getOrDefault(c,0)+1);
            right++;
            while(window.get(c)>1){
                char d = s.charAt(left);
                window.put(d,window.getOrDefault(d,0)-1);
                left++;
            }
            result=Math.max(result,(right-left));
        }
        return result;
    }

    public static void main(String[] args){
        LengthOfLongestSubstring test=new LengthOfLongestSubstring();
        String s="abcabcbb";

        int i = test.lengthOfLongestSubstring(s);
        System.out.println(i);

        int i1 = test.lengthOfLongestSubstring2(s);
        System.out.println(i1);
    }
}
