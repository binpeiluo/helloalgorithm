package com.luo.algorithm.doublepointer.slidepointer;

import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口系列
 */
public class MinimumSubString {

/*
    滑动窗口框架
    *//* 滑动窗口算法框架 *//*
    void slidingWindow(string s, string t) {
        unordered_map<char, int> need, window;
        for (char c : t) need[c]++;

        int left = 0, right = 0;
        int valid = 0;
        while (right < s.size()) {
            // c 是将移入窗口的字符
            char c = s[right];
            // 右移窗口
            right++;
            // 进行窗口内数据的一系列更新
        ...

            *//*** debug 输出的位置 ***//*
            printf("window: [%d, %d)\n", left, right);
            *//********************//*

            // 判断左侧窗口是否要收缩
            while (window needs shrink) {
                // d 是将移出窗口的字符
                char d = s[left];
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新
            ...
            }
        }
    }


滑动窗口算法的思路是这样：
    1、我们在字符串 S 中使用双指针中的左右指针技巧，初始化 left = right = 0，把索引左闭右开区间 [left, right) 称为一个「窗口」。
    2、我们先不断地增加 right 指针扩大窗口 [left, right)，直到窗口中的字符串符合要求（包含了 T 中的所有字符）。
    3、此时，我们停止增加 right，转而不断增加 left 指针缩小窗口 [left, right)，直到窗口中的字符串不再符合要求（不包含 T 中的所有字符了）。同时，每次增加 left，我们都要更新一轮结果。
    4、重复第 2 和第 3 步，直到 right 到达字符串 S 的尽头。
这个思路其实也不难，第 2 步相当于在寻找一个「可行解」，
然后第 3 步在优化这个「可行解」，最终找到最优解，也就是最短的覆盖子串。
左右指针轮流前进，窗口大小增增减减，窗口不断向右滑动，这就是「滑动窗口」这个名字的来历。
    */

    /**
     * 在字符串s中找出包含t所有字符的最小子串
     * @param s
     * @param t
     * @return
     */
    public String minimumSubString(String s,String t){
//        首先，初始化 window 和 need 两个哈希表，记录窗口中的字符和需要凑齐的字符
        Map<Character,Integer> need,window;
        need=new HashMap<>();
        window=new HashMap<>();
        for (char c:t.toCharArray()){
            need.put(c,need.getOrDefault(c,0)+1);
        }
        int left=0,right=0;
//        valid 变量表示窗口中满足 need 条件的字符个数
        int valid=0;

        // 记录最小覆盖子串的起始索引及长度
        int start = 0, len = Integer.MAX_VALUE;

//        现在开始套模板，只需要思考以下四个问题：
//        1、当移动 right 扩大窗口，即加入字符时，应该更新哪些数据？
//        2、什么条件下，窗口应该暂停扩大，开始移动 left 缩小窗口？
//        3、当移动 left 缩小窗口，即移出字符时，应该更新哪些数据？
//        4、我们要的结果应该在扩大窗口时还是缩小窗口时进行更新？

//        如果一个字符进入窗口，应该增加 window 计数器；
//        如果一个字符将移出窗口的时候，应该减少 window 计数器；
//        当 valid 满足 need 时应该收缩窗口；
//        应该在收缩窗口的时候更新最终结果。
        while(right<s.length()){
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            // 右移窗口
            right++;
            // 进行窗口内数据的一系列更新
            if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if(window.get(c)==need.get(c))
                    valid++;
            }

            // 判断左侧窗口是否要收缩
            while(valid==need.size()){
                // 在这里更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新
                if(need.containsKey(d)){
                    if(window.get(d)==need.get(d))
                        valid--;
                    window.put(d,window.getOrDefault(d,0)-1);
                }
            }
        }
        // 返回最小覆盖子串
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start+len);
    }


    public static void main(String[] args){
        MinimumSubString test=new MinimumSubString();
        String s="ADOBECODEBANC";
        String t="ABC";
        String s1 = test.minimumSubString(s, t);
        System.out.println("result="+s1);
    }
}
