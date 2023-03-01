package com.luo.leetcode.bfs;

import java.util.*;

/**
 * 126. 单词接龙 II
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *
 * 如果不存在这样的转换序列，返回一个空列表。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 *
 */
public class No126_findLadders {
    /**
     * 要将所有最短的路径都找出来,最简单的想法是回溯,将全部解找出来
     * 使用dfs递归方式
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result=new ArrayList<>();
        if(!wordList.contains(endWord))
            return result;
        minCount=Integer.MAX_VALUE;
        List<String> curr = new ArrayList<>();
        curr.add(beginWord);
        helper(result,curr,beginWord,endWord,wordList);
        return result;
    }

    private int minCount=Integer.MAX_VALUE;
    private void helper(List<List<String>> result,List<String> curr,
                        String begin,String endWord,List<String> wordList){
        if(begin.equals(endWord)){
            if(curr.size()<minCount){
                result.clear();
                result.add(new ArrayList<>(curr));
                minCount=curr.size();
            }else if(curr.size()==minCount)
                result.add(new ArrayList<>(curr));
            return;
        }
        if(curr.size()>=minCount)
            return;
        for (int i = 0; i < wordList.size(); i++) {
//            判断条件,做出选择
            String select=wordList.get(i);
            if(curr.contains(select))
                continue;
            if(!canConvert(begin,select))
                continue;
            curr.add(select);
            helper(result,curr,select,endWord,wordList);
//            撤销选择
            curr.remove(select);
        }
    }

    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result=new ArrayList<>();
        minCount=Integer.MAX_VALUE;
        if(!wordList.contains(endWord))
            return result;
        List<String> curr = new ArrayList<>();
        curr.add(beginWord);
        helper2(result,curr,beginWord,endWord,wordList);
        return result;
    }

    private void helper2(List<List<String>> result, List<String> curr,
                         String beginWord, String endWord, List<String> wordList) {
        if(beginWord.equals(endWord)){
            if(curr.size()<minCount){
                result.clear();
                result.add(new ArrayList<>(curr));
                minCount=curr.size();
            }else if(curr.size()==minCount)
                result.add(new ArrayList<>(curr));
            return;
        }
        if(curr.size()>=minCount)
            return;
        List<String> neighbors=getNeighbors(beginWord,wordList);
        for (int i = 0; i < neighbors.size(); i++) {
            String neighbor = neighbors.get(i);
            if(curr.contains(neighbor))
                continue;
            curr.add(neighbor);
            helper2(result,curr,neighbor,endWord,wordList);
            curr.remove(neighbor);
        }
    }

    private List<String> getNeighbors(String beginWord, List<String> wordList) {
        List<String> result=new ArrayList<>();
        int len=beginWord.length();
        char[] chars = beginWord.toCharArray();
        for (int i = 0; i < len; i++) {
            char old = chars[i];
            for (char j = 'a'; j <='z'; j++) {
                chars[i]=j;
                String s = String.valueOf(chars);
                if(wordList.contains(s))
                    result.add(s);
            }
            chars[i]=old;
        }
        return result;
    }


    public List<List<String>> findLaddersTmp(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result=new ArrayList<>();
        if(wordList.contains(endWord))
            return result;
        Queue<String> queue=new LinkedList<>();
        queue.offer(beginWord);
        int wordCount=wordList.size();
        boolean[] visited=new boolean[wordCount];
        for (int i = 0; i < wordCount; i++) {
            if(wordList.get(i).equals(beginWord))
                visited[i]=true;
        }
        List<String> select=new ArrayList<>();
        select.add(beginWord);
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                for (int j = 0; j < wordCount; j++) {
                    if(visited[j])
                        continue;
                    String word = wordList.get(j);
                    if(word.equals(endWord)){

                    }
                    if(!canConvert(poll,word))
                        continue;
                    queue.offer(word);
                    visited[i]=true;
                }
            }
        }
        return result;

    }




    /**
     * 判断两个单词只有一个字符不同
     * @param source
     * @param target
     * @return
     */
    private boolean canConvert(String source,String target){
        int sLen=source.length(),tLen=target.length();
        int diff=0;
        for (int i = 0,j=0; i < sLen; i++) {
            if(source.charAt(i)!=target.charAt(j)){
                diff++;
                if(diff>1)
                    return false;
            }
            j++;
        }
        return diff==1;
    }


    public static void main(String[] args){
        No126_findLadders test=new No126_findLadders();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        List<List<String>> ladders = test.findLadders(beginWord,endWord,wordList);
        System.out.println(ladders);

        List<List<String>> ladders2 = test.findLadders2(beginWord, endWord, wordList);
        System.out.println(ladders2);
    }


}
