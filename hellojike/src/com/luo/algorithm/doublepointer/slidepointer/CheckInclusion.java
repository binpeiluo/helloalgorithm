package com.luo.algorithm.doublepointer.slidepointer;

import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口系列
 * Leetcode 567
 * 给定两个字符串s1和s2,写一个函数来判断s2是否包含s1的排列.
 */
public class CheckInclusion {

    public boolean checkInclusion(String s,String t){
//        构造need,window
        Map<Character,Integer> need,window;
        window=new HashMap<>();
        need=new HashMap<>();
        for (char c: t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }
//        创建指针
        int valid=0;
        int left=0,right=0;
//        移动右指针
        while(right<s.length()){
            char c = s.charAt(right);
            right++;
            if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if(window.get(c)==need.get(c))
                    valid++;
            }
//            滑动左指针
            while(right-left>t.length()){
                char d = s.charAt(left);
                left++;
                if(need.containsKey(d)){
                    if(window.get(d)==need.get(d))
                        valid--;
                    window.put(d,window.getOrDefault(d,0)-1);
                }
            }
            if(valid==t.length())
                return true;
        }
        return false;
    }


    public boolean checkInclusion2(String s,String t){
//        构造need,window
        Map<Character,Integer> need,window;
        window=new HashMap<>();
        need=new HashMap<>();
        for (char c: t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }
//        创建指针
        int valid=0;
        int left=0,right=0;
//        移动右指针
        while(right<s.length()){
            char c = s.charAt(right);
            right++;
            if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if(window.get(c)==need.get(c))
                    valid++;
            }
//            滑动左指针
            while(right-left>=t.length()){
                if(valid==t.length())
                    return true;
                char d = s.charAt(left);
                left++;
                if(need.containsKey(d)){
                    if(window.get(d)==need.get(d))
                        valid--;
                    window.put(d,window.getOrDefault(d,0)-1);
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        CheckInclusion test=new CheckInclusion();
        String s="eidboaoo";
        String t="ab";
        boolean b = test.checkInclusion(s, t);
        System.out.println(b);

        boolean b1 = test.checkInclusion2(s, t);
        System.out.println(b1);
    }
}
