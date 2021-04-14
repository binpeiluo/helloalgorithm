package com.luo.leetcode.dataconstruct;

/**
 * 208. 实现 Trie (前缀树)
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 */
public class No208_Trie {

    static class Node{
        private Node[] nodes;
        private boolean end;

        public Node() {
            this.nodes = new Node[26];
            this.end=false;
        }
        public void setEnd(){
            this.end=true;
        }
        public boolean getEnd(){
            return end;
        }
    }

    static class Trie {

        Node root=null;

        /** Initialize your data structure here. */
        public Trie() {
            root=new Node();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Node ref=root;
            for (int i = 0; i < word.length(); i++) {
                int position=word.charAt(i)-'a';
                if(ref.nodes[position]==null){
                    ref.nodes[position]=new Node();
                }
                ref=ref.nodes[position];
            }
            ref.setEnd();
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node ref = prefix(word);
            return ref==null?false:ref.getEnd();
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Node ref = prefix(prefix);
            return ref!=null;
        }

        private Node prefix(String word){
            Node ref=root;
            for (int i = 0; i < word.length(); i++) {
                int position=word.charAt(i)-'a';
                if(ref.nodes[position]==null){
                    return null;
                }
                ref=ref.nodes[position];
            }
            return ref;
        }
    }


    public static void main(String[] args) {
        Trie trie=new Trie();

        trie.insert("apple");
        boolean apple = trie.search("apple");
        System.out.println(apple);

        boolean app = trie.search("app");
        System.out.println(app);

        boolean app1 = trie.startsWith("app");
        System.out.println(app1);

        trie.insert("app");
        boolean app2 = trie.search("app");
        System.out.println(app2);
    }
}
