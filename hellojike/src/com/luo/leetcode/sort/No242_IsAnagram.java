package com.luo.leetcode.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    242. 有效的字母异位词
    给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
        示例 1:
        输入: s = "anagram", t = "nagaram"
        输出: true
        示例 2:
        输入: s = "rat", t = "car"
        输出: false
        说明:
        你可以假设字符串只包含小写字母。
        进阶:
        如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
*/
public class No242_IsAnagram {
    /**
     * 思路:
     *      使用list容器储存其中一个字符串的字符,在遍历另一个字符串删除每一个字符
     *      时间复杂度为:O(n)
     *      空间复杂度为:O(n)
     *      在leetcode执行耗时接近300ms,
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
//        if((s==null||s.length()==0)||(t==null||t.length()==0)){
//            if((s==null||s.length()==0)&&(t==null||t.length()==0))
//                return true;
//            else
//                return false;
//        }
        List<Character> all=new ArrayList<>();
        for(int i=0;i<s.length();i++){
            all.add(s.charAt(i));
        }
        for(int i=0;i<t.length();i++){
            boolean remove = all.remove(Character.valueOf(t.charAt(i)));
            if(!remove)
                return false;
        }
        return all.isEmpty();
    }


    /**
     * 思路:
     *      排序法.
     *      将两个字符串转为字符数组然后排序,接着比较两个数组
     *      时间复杂度为:O(nlogn)
     *      空间复杂度为:O(n)     Arrays.sort具体实现为复制一遍数组
     *
     *      在leetcode执行时间竟然为 4ms
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram2(String s, String t) {
        char[] charss= s.toCharArray();
        char[] charst = t.toCharArray();
        Arrays.sort(charss);
        Arrays.sort(charst);
        return Arrays.equals(charss,charst);
    }



    public static void main(String[] args){
        No242_IsAnagram test=new No242_IsAnagram();
        String s = "anagram";
        String t = "nagaram";
        boolean anagram = test.isAnagram(s, t);
        System.out.println(anagram);
    }
}
