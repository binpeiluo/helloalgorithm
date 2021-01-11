package com.luo.leetcode.unionfind;

import java.util.Arrays;
import java.util.List;

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
        UnionFind un=new UnionFind(pairs.size());
        for (List<Integer> item:pairs){
            un.union(item.get(0),item.get(1));
        }

        return "";



    }

    public static void main(String[] args) {

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
            return m;
        }
    }
}
