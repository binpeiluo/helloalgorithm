package com.luo.leetcode.bfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 17.13. 恢复空格
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，
 * 并且大写也弄成了小写。像句子"I reset the computer.
 * It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。
 * 在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，
 * 不过，有些词没在词典里。
 * 假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 * 注意：本题相对原题稍作改动，只需返回未识别的字符数
 * 示例：
 *
 * 输入：
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * 输出： 7
 * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
 * 提示：
 *
 * 0 <= len(sentence) <= 1000
 * dictionary中总字符数不超过 150000。
 * 你可以认为dictionary和sentence中只包含小写字母。
 *
 */
public class No_m_17_13_respace {

    /**
     * 别人的解法
     * 自己想使用类似No139 深度优先遍历方式.但No139题是判断单词是否能被完整切分
     * 这题如果使用深度优先遍历的话,对于不能匹配的字符需要跳过
     * @param dictionary
     * @param sentence
     * @return
     */
    public int respace(String[] dictionary, String sentence) {
        Set<String> dict = new HashSet<>(Arrays.asList(dictionary));
        int n = sentence.length();
//        dp[i] 表示字符串的前 i 个字符的最少未匹配数。
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
//            第 i 个字符未匹配，则 dp[i] = dp[i-1] + 1，即不匹配数加 1;
            dp[i] = dp[i - 1] + 1;
//            遍历前 i-1 个字符，若以其中某一个下标 idx 为开头、以第 i 个字符为结尾的字符串正好在词典里，
//            则 dp[i] = min(dp[i], dp[idx]) 更新 dp[i]
            for (int idx = 0; idx < i; idx++) {
                if (dict.contains(sentence.substring(idx, i))) {
                    dp[i] = Math.min(dp[i], dp[idx]);
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args){
        No_m_17_13_respace test=new No_m_17_13_respace();
//        String[] dictionary = {"looked","just","like","her","brother"};
//        String sentence = "jesslookedjustliketimherbrother";

        String[] dictionary = {"abcd", "ab", "def"};
        String sentence = "abcdef";

        int respace = test.respace(dictionary, sentence);
        System.out.println(respace);
    }

}
