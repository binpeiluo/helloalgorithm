package com.luo.algorithm.doublepointer.slidepointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 滑动窗口系列
 *
 * 给定两个字符串s,t 在s中找到所有t的所有字母异位词.并返回所有异位词的起始角标
 */
public class FindAnagrams {

    /**
     * 字母异位词,起始就是该字符串的全排列形式.
     * 滑动窗口,整!
     * @param s
     * @param t
     * @return
     */
    public List<Integer> findAnagrams(String s, String t){
        Map<Character,Integer> need,window;
        need=new HashMap<>();
        window=new HashMap<>();
        for(char c:t.toCharArray())
            need.put(c,need.getOrDefault(c,0)+1);

        List<Integer> result=new ArrayList<>();
        int left=0,right=0;
        int valid=0;

        while(right<s.length()){
            char c = s.charAt(right);
            right++;
            if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if(window.get(c)==need.get(c))
                    valid++;
            }

            while(right-left>t.length()){
                char d = s.charAt(left);
                left++;
                if(need.containsKey(d)){
                    if(window.get(d)==need.get(d))
                        valid--;
                    window.put(d,window.get(d)-1);
                }
            }
            if(valid==t.length())
                result.add(left);
        }
        return result;
    }

    public List<Integer> findAnagrams2(String s, String t){
        Map<Character,Integer> need,window;
        need=new HashMap<>();
        window=new HashMap<>();
        for(char c:t.toCharArray())
            need.put(c,need.getOrDefault(c,0)+1);

        List<Integer> result=new ArrayList<>();
        int left=0,right=0;
        int valid=0;

        while(right<s.length()){
            char c = s.charAt(right);
            right++;
            if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if(window.get(c)==need.get(c))
                    valid++;
            }

            while(right-left>=t.length()){
                if(valid==t.length())
                    result.add(left);
                char d = s.charAt(left);
                left++;
                if(need.containsKey(d)){
                    if(window.get(d)==need.get(d))
                        valid--;
                    window.put(d,window.get(d)-1);
                }
            }
        }
        return result;
    }



    public static void main(String[] args){
        FindAnagrams test=new FindAnagrams();
        String s="cbaebabacd";
        String t="abc";

        List<Integer> anagrams = test.findAnagrams(s, t);
        System.out.println(anagrams);

        List<Integer> anagrams2 = test.findAnagrams2(s, t);
        System.out.println(anagrams2);
    }
}
