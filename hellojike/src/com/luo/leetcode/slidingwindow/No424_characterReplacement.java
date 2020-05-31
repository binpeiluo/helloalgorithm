package com.luo.leetcode.slidingwindow;
/*

424. 替换后的最长重复字符
给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
注意:字符串长度 和 k 不会超过 104。
示例 1:
输入:
s = "ABAB", k = 2
输出:4
解释:
用两个'A'替换为两个'B',反之亦然。
示例 2:
输入:
s = "AABABBA", k = 1
输出:4

解释:
将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
子串 "BBBB" 有最长重复字母, 答案为 4。
*/

public class No424_characterReplacement {

    /**
     * 滑动窗口法．
     *  维护滑动窗口长度与窗口之中最多字符的个数和k的关系
     *  当 len-max > k 时,说明替换k个字符无法凑成len个字符,需要将窗口缩小
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        int[] window=new int[26];
        int len=s.length();
        int charMaxLen=0;
        int left=0;
        int result=0;
        for (int right = 0; right < len; right++) {
            window[s.charAt(right)-'A']++;
            charMaxLen=Math.max(charMaxLen,window[s.charAt(right)-'A']);
            if(right-left+1-charMaxLen>k){
                window[s.charAt(left)-'A']--;
                left++;
            }
            result=Math.max(result,right-left+1);
        }
        return result;
    }

    public static void main(String[] args){
        No424_characterReplacement test=new No424_characterReplacement();
        String s="AABABBA";
        int k=1;
        int i = test.characterReplacement(s, k);
        System.out.println(i);
    }
}
