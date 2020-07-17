package com.luo.leetcode.bfs;

import javafx.util.Pair;

import java.util.*;

/**
 * 127. 单词接龙
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 *  每次转换只能改变一个字母。
 *  转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *  如果不存在这样的转换序列，返回 0。
 *  所有单词具有相同的长度。
 *  所有单词只由小写字母组成。
 *  字典中不存在重复的单词。
 *  你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出: 5
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 */
@SuppressWarnings("Duplicates")
public class No127_ladderLength {

    /**
     * 使用朴素的bfs算法实现,在单词列表寻找与当前单词只有一个字母之差的单词,判断是否等于endWord,然后入队
     *
     * 时间复杂度:    O(n*m) n为单词列表长度,m为单词字符串长度
     * 空间复杂度:    O(n)
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord))
            return 0;
        Queue<String> queue=new LinkedList<>();
//        使用set保存那些单词被访问过
        Set<String> visited=new HashSet<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        int count=0;
        while(!queue.isEmpty()){
            int size = queue.size();
            count++;
//            count 表示遍历到poll时的层数
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                for (int j = 0,wordsCount=wordList.size(); j < wordsCount ; j++) {
                    String s = wordList.get(j);
                    if(visited.contains(s)||!canConvert(poll,s))
                        continue;
//                    当前poll还需要在修改一次才能变成 endWord,所以需要+1
                    if(endWord.equals(s))
                        return count+1;
                    queue.offer(s);
                    visited.add(s);
                }
            }
        }
        return 0;
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

    /**
     * 优化版本1 ,将版本1的set数据结构修改成boolean数组
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord))
            return 0;
        Queue<String> queue=new LinkedList<>();
        int wordCount=wordList.size();
        boolean[] visited=new boolean[wordList.size()];
        queue.offer(beginWord);
        for (int i = 0; i < wordCount; i++) {
            if(beginWord.equals(wordList.get(i)))
                visited[i]=true;
        }
        int res=0;
        while(!queue.isEmpty()){
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                for (int j = 0; j < wordCount; j++) {
                    if(visited[j])
                        continue;
                    String s = wordList.get(j);
                    if(!canConvert(poll,s))
                        continue;
                    if(endWord.equals(s))
                        return res+1;
                    queue.offer(s);
                    visited[j]=true;
                }
            }
        }
        return 0;
    }

    /**
     * 使用双向bfs遍历
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        int end = wordList.indexOf(endWord);
        if (end == -1) {
            return 0;
        }
        wordList.add(beginWord);
        int start = wordList.size() - 1;
        // 用于BFS遍历的队列
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        // 用于保存已访问的单词
        Set<Integer> visited1 = new HashSet<>();
        Set<Integer> visited2 = new HashSet<>();
        queue1.offer(start);
        queue2.offer(end);
        visited1.add(start);
        visited2.add(end);
        int count1 = 0;
        int count2 = 0;
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            count1++;
            int size1 = queue1.size();
            while (size1-- > 0) {
                String s = wordList.get(queue1.poll());
                for (int i = 0; i < wordList.size(); ++i) {
                    if (visited1.contains(i)) {
                        continue;
                    }
                    if (!canConvert(s, wordList.get(i))) {
                        continue;
                    }
                    if (visited2.contains(i)) {
                        return count1 + count2 + 1;
                    }
                    visited1.add(i);
                    queue1.offer(i);
                }
            }
            count2++;
            int size2 = queue2.size();
            while (size2-- > 0) {
                String s = wordList.get(queue2.poll());
                for (int i = 0; i < wordList.size(); ++i) {
                    if (visited2.contains(i)) {
                        continue;
                    }
                    if (!canConvert(s, wordList.get(i))) {
                        continue;
                    }
                    if (visited1.contains(i)) {
                        return count1 + count2 + 1;
                    }
                    visited2.add(i);
                    queue2.offer(i);
                }
            }
        }
        return 0;
    }

    /**
     * 双向bfs遍历优化
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength4(String beginWord, String endWord, List<String> wordList) {
        int end = wordList.indexOf(endWord);
        if (end == -1) {
            return 0;
        }
        wordList.add(beginWord);
        int start = wordList.size() - 1;
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        Set<Integer> visited1 = new HashSet<>();
        Set<Integer> visited2 = new HashSet<>();
        queue1.offer(start);
        queue2.offer(end);
        visited1.add(start);
        visited2.add(end);
        int count = 0;
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            count++;
//            优化点,从每层节点较少的队列遍历
            if (queue1.size() > queue2.size()) {
                Queue<Integer> tmp = queue1;
                queue1 = queue2;
                queue2 = tmp;
                Set<Integer> t = visited1;
                visited1 = visited2;
                visited2 = t;
            }
            int size1 = queue1.size();
            while (size1-- > 0) {
                String s = wordList.get(queue1.poll());
                for (int i = 0; i < wordList.size(); ++i) {
                    if (visited1.contains(i)) {
                        continue;
                    }
                    if (!canConvert(s, wordList.get(i))) {
                        continue;
                    }
                    if (visited2.contains(i)) {
                        return count + 1;
                    }
                    visited1.add(i);
                    queue1.offer(i);
                }
            }
        }
        return 0;
    }

    public int ladderLength5(String beginWord, String endWord, List<String> wordList) {
        int end = wordList.indexOf(endWord);
        if (end == -1) {
            return 0;
        }
        wordList.add(beginWord);

        // 从两端BFS遍历要用的队列
        Queue<String> queue1 = new LinkedList<>();
        Queue<String> queue2 = new LinkedList<>();
        // 两端已经遍历过的节点
        Set<String> visited1 = new HashSet<>();
        Set<String> visited2 = new HashSet<>();
        queue1.offer(beginWord);
        queue2.offer(endWord);
        visited1.add(beginWord);
        visited2.add(endWord);

        int count = 0;
        Set<String> allWordSet = new HashSet<>(wordList);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            count++;
            if (queue1.size() > queue2.size()) {
                Queue<String> tmp = queue1;
                queue1 = queue2;
                queue2 = tmp;
                Set<String> t = visited1;
                visited1 = visited2;
                visited2 = t;
            }
            int size1 = queue1.size();
            while (size1-- > 0) {
                String s = queue1.poll();
                char[] chars = s.toCharArray();
                for (int j = 0; j < s.length(); ++j) {
                    // 保存第j位的原始字符
                    char c0 = chars[j];
                    for (char c = 'a'; c <= 'z'; ++c) {
                        chars[j] = c;
                        String newString = new String(chars);
                        // 已经访问过了，跳过
                        if (visited1.contains(newString)) {
                            continue;
                        }
                        // 两端遍历相遇，结束遍历，返回count
                        if (visited2.contains(newString)) {
                            return count + 1;
                        }
                        // 如果单词在列表中存在，将其添加到队列，并标记为已访问
                        if (allWordSet.contains(newString)) {
                            queue1.offer(newString);
                            visited1.add(newString);
                        }
                    }
                    // 恢复第j位的原始字符
                    chars[j] = c0;
                }
            }
        }
        return 0;
    }

    /**
     * 单词转换判断的优化
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength6(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord))
            return 0;
        Queue<String> queue=new LinkedList<>();
        Set<String> visted=new HashSet<>();
        queue.offer(beginWord);
        visted.add(beginWord);
        int res=0;
        while(!queue.isEmpty()){
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++) {
                char[] chars = queue.poll().toCharArray();
//            判断转换判断
                for (int z = 0,len=chars.length; z <len ; z++) {
                    char c0=chars[z];
                    for (char j = 'a'; j <='z' ; j++) {
                        chars[z]=j;
                        String newString=String.copyValueOf(chars);
                        if(visted.contains(newString))
                            continue;
                        if(!wordList.contains(newString))
                            continue;
                        if(endWord.equals(newString))
                            return res+1;
                        queue.offer(newString);
                        visted.add(newString);
                    }
                    chars[z]=c0;
                }
            }
        }
        return 0;
    }

    /**
     * 预处理方法的bfs
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength10(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord))
            return 0;
        int strLen=beginWord.length();
        Map<String,List<String>> allCompoMap=new HashMap<>();
        wordList.stream().forEach(
                word->{
                    for (int i = 0; i < strLen; i++) {
                        String compo=word.substring(0,i)+"*"+word.substring(i+1,strLen);
                        List<String> orDefault = allCompoMap.getOrDefault(compo, new ArrayList<>());
                        orDefault.add(word);
                        allCompoMap.put(compo,orDefault);
                    }
                }
        );
        Queue<Pair<String,Integer>> queue=new LinkedList<>();
        Map<String,Boolean> visited=new HashMap<>();
        queue.offer(new Pair<>(beginWord,1));
        visited.put(beginWord,true);
        while(!queue.isEmpty()){
            Pair<String, Integer> poll = queue.poll();
            String word = poll.getKey();
            Integer level = poll.getValue();
            for (int i = 0; i < strLen; i++) {
                String newWord=word.substring(0,i)+"*"+word.substring(i+1,strLen);
                for(String adjustWord:allCompoMap.getOrDefault(newWord,new ArrayList<>())){
                    if(adjustWord.equals(endWord))
                        return level+1;
                    if(!visited.containsKey(adjustWord)){
                        queue.offer(new Pair<>(adjustWord,level+1));
                        visited.put(adjustWord,true);
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 再来挑战
     * 由于需要返回最短长度,使用bfs
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength11(String beginWord, String endWord, List<String> wordList) {
        Set<String> set=new HashSet<>(wordList);
        if(!set.contains(endWord)){
            return 0;
        }
        Set<String> visited=new HashSet<>();
        Queue<String> queue=new LinkedList<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        int result=0;
        while(!queue.isEmpty()){
            int len=queue.size();
            result++;
            for (int i = 0; i < len; i++) {
                String poll = queue.poll();
//            找到相差一个字符的字符串
                for(String word:wordList){
                    if(visited.contains(word)||!canConvert11(poll,word)){
                        continue;
                    }
                    if(word.equals(endWord)){
                        return result+1;
                    }
                    queue.offer(word);
                    visited.add(word);
                }
            }
        }
        return 0;
    }

    private boolean canConvert11(String wordA,String wordB){
        int diff=0;
        int len=wordA.length();
        for (int i = 0; i < len; i++) {
            if(wordA.charAt(i)!=wordB.charAt(i)){
                diff++;
                if(diff>1){
                    return false;
                }
            }
        }
        return diff==1;
    }




    public static void main(String[] args){
        No127_ladderLength test=new No127_ladderLength();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        int i = test.ladderLength(beginWord, endWord, wordList);
        System.out.println(i);

        int i1 = test.ladderLength2(beginWord, endWord, wordList);
        System.out.println(i1);

        int i2 = test.ladderLength6(beginWord, endWord, wordList);
        System.out.println(i2);

        int i3 = test.ladderLength10(beginWord, endWord, wordList);
        System.out.println(i3);

        int i4 = test.ladderLength11(beginWord, endWord, wordList);
        System.out.println(i4);
    }

}
