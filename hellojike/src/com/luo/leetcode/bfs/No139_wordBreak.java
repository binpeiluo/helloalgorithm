package com.luo.leetcode.bfs;

import java.util.*;

/**
 * 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 */
@SuppressWarnings("Duplicates")
public class No139_wordBreak {

    /**
     * dfs 想象一下二叉树的深度遍历
     * 没有优化会超时,
     *
     * 时间复杂度:   O(n^2)
     * 空间复杂度:   O(n^2)
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set=new HashSet<>(wordDict);
        Map<String, Boolean> valid=new HashMap<>();
        return helper(s,set,valid);
    }

    private boolean helper(String str, Set<String> wordDict,Map<String,Boolean> valid){
        if("".equals(str)||str==null) {
            return true;
        }
        if(valid.containsKey(str)){
            return valid.get(str);
        }
        int len=str.length();
        for (int i = 0; i <=len; i++) {
            if(wordDict.contains(str.substring(0,i))&&helper(str.substring(i,len),wordDict,valid)){
                valid.put(str,true);
                return true;
            }
        }
        valid.put(str,false);
        return false;
    }

    /**
     * 题目中求字符串按照字典的单词拆分.
     * 可以使用深度优先遍历以字符串单词做树遍历
     * 不剪枝的话,遇到类似 aaaaaa [aaaa,aaa] 会超时
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak3(String s, List<String> wordDict) {
        Set<String> set=new HashSet<>(wordDict);
        return helper(s,set);
    }

    private boolean helper(String s,Set<String> set){
        if("".equals(s)||s==null){
            return true;
        }
        int len=s.length();
        for (int i = 0; i <=len; i++) {
            if(set.contains(s.substring(0,i))&&helper(s.substring(i,len),set)){
                return true;
            }
        }
        return false;
    }

    /**
     * 既然能使用dfs,那么也能使用bfs
     * 想象一下二叉树的广度遍历过程
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        Queue<Integer> queue=new LinkedList<>();
        Set<String> set=new HashSet<>(wordDict);
        queue.offer(0);
        int len=s.length();
        boolean[] visited=new boolean[len+1];
        while(!queue.isEmpty()){
            Integer poll = queue.poll();
            if(poll==len){
                return true;
            }
            if(visited[poll]){
                continue;
            }
            visited[poll]=true;
            for (int i = poll; i <=len; i++) {
                if(wordDict.contains(s.substring(poll,i))){
                    queue.offer(i);
                }
            }
        }
        return false;
    }



    public static void main(String[] args){

        No139_wordBreak test=new No139_wordBreak();
//        String s = "leetcode";
//        List<String> wordDict = Arrays.asList("leet", "code");

        String s = "applepenapple";
        List<String> wordDict = Arrays.asList("apple", "pen");

//        String s = "catsandog";
//        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");

        boolean b = test.wordBreak(s, wordDict);
        System.out.println(b);

        boolean b1 = test.wordBreak2(s, wordDict);
        System.out.println(b1);

        boolean b2 = test.wordBreak3(s, wordDict);
        System.out.println(b2);
    }
}
