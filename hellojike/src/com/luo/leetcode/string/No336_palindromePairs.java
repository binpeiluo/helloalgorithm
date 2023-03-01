package com.luo.leetcode.string;


import java.util.*;

/**
 * 336. 回文对
 * 给定一组 互不相同 的单词， 找出所有不同 的索引对(i, j)，
 * 使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 * 示例 1：
 * 输入：["abcd","dcba","lls","s","sssll"]
 * 输出：[[0,1],[1,0],[3,2],[2,4]]
 * 解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 *
 * 示例 2：
 * 输入：["bat","tab","cat"]
 * 输出：[[0,1],[1,0]]
 * 解释：可拼接成的回文串为 ["battab","tabbat"]
 *
 */
@SuppressWarnings("Duplicates")
public class No336_palindromePairs {

    class Node {
        int[] ch = new int[26];
        int flag;

        public Node() {
            flag = -1;
        }
    }
    private List<Node> tree = new ArrayList<Node>();

    /**
     * 使用Trie树
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        tree.add(new Node());
        int n = words.length;
        for (int i = 0; i < n; i++) {
            insert(words[i], i);
        }
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            int m = words[i].length();
            for (int j = 0; j <= m; j++) {
//                字符串words[i]的后缀开始是回文串
                if (isPalindrome(words[i], j, m - 1)) {
//                  那么判断字符串words[i]的第j个字符之前的子字符串的逆序是否存在
                    int leftId = findWord(words[i], 0, j - 1);
                    if (leftId != -1 && leftId != i) {
                        ret.add(Arrays.asList(i, leftId));
                    }
                }
//                字符串words[i]的前缀开始是回文串
                if (j != 0 && isPalindrome(words[i], 0, j - 1)) {
                    int rightId = findWord(words[i], j, m - 1);
                    if (rightId != -1 && rightId != i) {
                        ret.add(Arrays.asList(rightId, i));
                    }
                }
            }
        }
        return ret;
    }

    private void insert(String s, int id) {
        int len = s.length(), add = 0;
        for (int i = 0; i < len; i++) {
            int x = s.charAt(i) - 'a';
//            tree.get(add).ch[x]==0代表还没有对应trie节点
            if (tree.get(add).ch[x] == 0) {
                tree.add(new Node());
//                tree.get(add).ch[x] 指向的Node节点在list的位置
                tree.get(add).ch[x] = tree.size() - 1;
            }
//            每个字母对应一个trie节点,add代表遍历的字符位置
            add = tree.get(add).ch[x];
        }
//        tree.get(add).flag 代表该trie节点结尾的字符串对应哪个位置
        tree.get(add).flag = id;
    }

    /**
     * 判断字符串s的left字符到右边是否是回文
     * @param s
     * @param left
     * @param right
     * @return
     */
    private boolean isPalindrome(String s, int left, int right) {
        int len = right - left + 1;
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(left + i) != s.charAt(right - i)) {
                return false;
            }
        }
        return true;
    }

    private int findWord(String s, int left, int right) {
        int add = 0;
        for (int i = right; i >= left; i--) {
            int x = s.charAt(i) - 'a';
            if (tree.get(add).ch[x] == 0) {
                return -1;
            }
            add = tree.get(add).ch[x];
        }
        return tree.get(add).flag;
    }

//
    List<String> wordsRev = new ArrayList<String>();
    Map<String, Integer> indices = new HashMap<String, Integer>();

    /**
     * 使用hashmap
     * 边界处理得太好了
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs2(String[] words) {
        int n = words.length;
        for (String word: words) {
            wordsRev.add(new StringBuffer(word).reverse().toString());
        }
        for (int i = 0; i < n; ++i) {
            indices.put(wordsRev.get(i), i);
        }

        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int m = words[i].length();
            if (m == 0) {
                continue;
            }
            for (int j = 0; j <= m; j++) {
                if (isPalindrome2(word, j, m - 1)) {
                    int leftId = findWord2(word, 0, j - 1);
                    if (leftId != -1 && leftId != i) {
                        ret.add(Arrays.asList(i, leftId));
                    }
                }
                if (j != 0 && isPalindrome2(word, 0, j - 1)) {
                    int rightId = findWord2(word, j, m - 1);
                    if (rightId != -1 && rightId != i) {
                        ret.add(Arrays.asList(rightId, i));
                    }
                }
            }
        }
        return ret;
    }

    private boolean isPalindrome2(String s, int left, int right) {
        int len = right - left + 1;
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(left + i) != s.charAt(right - i)) {
                return false;
            }
        }
        return true;
    }

    private int findWord2(String s, int left, int right) {
        return indices.getOrDefault(s.substring(left, right + 1), -1);
    }

    /**
     * 重点在于利用回文串的性质
     * 两个字符串拼起来能组成回文串,那么这两个字符串是具有一定性质的
     * 其中一个字符串前缀或者后缀是回文串,剩下的部分和另一个字符串是逆序
     * 判断是否存在子串的逆序,可以放置在hashmap,也可以放置在字典树
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs3(String[] words) {
        List<List<Integer>> result=new ArrayList<>();
        int length = words.length;

        Map<String,Integer> map=new HashMap<>();
        for (int i = 0; i < length; i++) {
            map.put(new StringBuilder(words[i]).reverse().toString(),i);
        }
        for (int i = 0; i < length; i++) {
            String word = words[i];
            int len=word.length();
            for (int j = 0; j <=len; j++) {
//                后缀是回文串
                if(isPalindrome3(word,j,len-1)){
//                    判断剩下的字符串是否在map中存在
                    Integer index = map.getOrDefault(word.substring(0, j), -1);
                    if(index!=-1 && index!=i){
                        result.add(Arrays.asList(i,index));
                    }
                }
//                前缀是回文串
                if(j!=0&&isPalindrome3(word,0,j-1)){
//                    substring(start,end) 当start>end会越界
//                    System.out.println(i+","+j+","+len+","+word);
                    Integer index = map.getOrDefault(word.substring(j, len - 1+1), -1);
                    if(index!=-1 && index!=i){
                        result.add(Arrays.asList(index,i));
                    }
                }
            }
        }
        return result;
    }

    private boolean isPalindrome3(String str,int left,int right){
        int step =(right-left+1)/2;
        for (int i = 0; i <step; i++) {
            if(str.charAt(left+i)!=str.charAt(right-i)){
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args){
        No336_palindromePairs test=new No336_palindromePairs();
        String[] words={"abcd","dcba","lls","s","sssll"};

        List<List<Integer>> lists = test.palindromePairs(words);
        System.out.println(lists);

        List<List<Integer>> lists1 = test.palindromePairs3(words);
        System.out.println(lists1);
    }

}
