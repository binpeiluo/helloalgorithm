package com.luo.leetcode.unionfind;

import java.util.*;

/**
 * 1202. 交换字符串中的元素
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，
 * 其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 *
 * 示例 1:
 * 输入：s = "dcab", pairs = [[0,3],[1,2]]
 * 输出："bacd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[1] 和 s[2], s = "bacd"
 *
 * 示例 2：
 * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * 输出："abcd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[0] 和 s[2], s = "acbd"
 * 交换 s[1] 和 s[2], s = "abcd"
 *
 * 示例 3：
 * 输入：s = "cba", pairs = [[0,1],[1,2]]
 * 输出："abc"
 * 解释：
 * 交换 s[0] 和 s[1], s = "bca"
 * 交换 s[1] 和 s[2], s = "bac"
 * 交换 s[0] 和 s[1], s = "abc"
 *  
 * 提示：
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s 中只含有小写英文字母
 *
 */
@SuppressWarnings("Duplicates")
public class No1202_smallestStringWithSwaps {

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
//        索引对的联通分量
        UnionFind un=new UnionFind(s.length());
        for (List<Integer> item:pairs){
            un.union(item.get(0),item.get(1));
        }

//        将联通分量对应的字符串保存起来,排序即可
        Map<Integer,List<Character>> map=new HashMap<>();
        for (int i = 0,len=s.length(); i <len; i++) {
            int root = un.root(i);
            if(map.containsKey(root)){
                map.get(root).add(s.charAt(i));
            }else{
                List<Character> orDefault = map.getOrDefault(root, new ArrayList<>());
                orDefault.add(s.charAt(i));
                map.put(root,orDefault);
            }
        }

//        排序
        for(Map.Entry<Integer,List<Character>> entry:map.entrySet()){
            entry.getValue().sort(Comparator.naturalOrder());
        }

        String result="";
//        输出
        for (int i = 0,len=s.length(); i < len; i++) {
            int root = un.root(s.charAt(i));
            Character remove = map.get(root).remove(0);
            result+=remove;
        }
        return result;
    }

    public static void main(String[] args) {
        No1202_smallestStringWithSwaps test=new No1202_smallestStringWithSwaps();

        String s = "dcab";
        List<List<Integer>> pairs = Arrays.asList(Arrays.asList(0,3),Arrays.asList(1,2));
        String s1 = test.smallestStringWithSwaps(s, pairs);
        System.out.println(s1);
    }

    static class UnionFind{
        int capicity;
        int[] parent;
        int[] rank;
        UnionFind(int capicity){
            this.capicity=capicity;
            this.parent=new int[capicity];
            this.rank=new int[capicity];
            Arrays.fill(parent,1);
            Arrays.fill(rank,1);
        }

        public void union(int m,int n){
            int rootM=root(m);
            int rootN=root(n);
            if(rootM==rootN){
                return;
            }
            if(rank[rootM]>rank[rootN]){
                parent[rootN]=rootM;
                rank[rootM]+=rank[rootN];
            }else{
                parent[rootM]=rootN;
                rank[rootN]+=rank[rootM];
            }
        }
        public boolean isConnect(int m,int n){
            return root(m)==root(n);
        }
        public int root(int m){
            while(parent[m]!=m){
                m=parent[m];
            }
            return m;
        }
    }
}
