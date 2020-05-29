package com.luo.leetcode.doublepointer;

/*
76. 最小覆盖子串
给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
示例：
输入: S = "ADOBECODEBANC", T = "ABC"
输出: "BANC"
说明：
如果 S 中不存这样的子串，则返回空字符串 ""。
如果 S 中存在这样的子串，我们保证它是唯一的答案。
*/

import java.util.HashMap;
import java.util.Map;

public class No76_minWindow {

    /**
     * 思路
     *      使用滑动窗口法.
     *      统计需要的字符个数,以及滑动窗口的字符个数
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
//        使用map统计需要的字符以及个数
        Map<Character,Integer> need=new HashMap<>();
//        统计滑动的窗口包含的字符以及个数
        Map<Character,Integer> window=new HashMap<>();
        for (char c:t.toCharArray())
            need.put(c,need.getOrDefault(c,0)+1);
//        使用valid统计滑动窗口里边已经达到了need字符的个数
        int valid=0;
//        使用两个指针代表窗口
        int left=0,right=0;
        String result="";
        boolean found=false;
        while(right<s.length()){
//            移动右指针,更新窗口内的元素统计信息
            char c = s.charAt(right);
            right++;
//            只有在当前字符在need里边才进行更新,不然没有意义
            if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
//            假如窗口内的某个元素个数达到need的字符个数,那么该字符达成.valid+1
//                Integer进行比较时,需要特别注意.当Integer.valuaOf()在-128~127之间是使用缓存,不然会new一个新的对象,此时再使用==比较就会出错!
                if(window.get(c).compareTo(need.get(c))==0)
                    valid++;
            }
//            题需要找到最小的字符串,故当窗口内字符符合need需要的元素时,逐渐增加left以缩小窗口
            while(valid==need.size()){
                char d = s.charAt(left);
//                当left指向元素是need需要的元素时,需要将window窗口的字符数量-1
//                当当前window中包含该字符的个数与need中包含该字符的个数相等时,valid--

//                这里将left+1移动到while最后部分会导致输出不正确值
                left++;
                if(need.containsKey(d)){
                    if(window.get(d).compareTo(need.get(d))==0)
                        valid--;
                    window.put(d,window.getOrDefault(d,0)-1);
                }
                if(!found){
                    found=true;
                    result=s.substring(left-1,right);
                }else{
                    if(right-left<result.length())
                        result=s.substring(left-1,right);
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        No76_minWindow test=new No76_minWindow();
//        String S = "ADOBECODEBANC", T = "ABC";
        String S = "AB", T = "B";

        String s = test.minWindow(S, T);
        System.out.println(s);
    }
}
