package com.luo.labuladong.dataconstruct;

public class TrieTreeTest {
    class TrieTree{
        TrieTree[] children;
        boolean flag;
        TrieTree(){
            children=new TrieTree[26];
            flag=false;
        }
    }
    TrieTree root=new TrieTree();

    public void addToTrie(String str){
        int length = str.length();
        TrieTree node=root;
        for (int i = 0; i < length; i++) {
            int c=str.charAt(i)-'a';
            if(node.children[c]==null){
                TrieTree chlid=new TrieTree();
                node.children[c]=chlid;
            }
            node=node.children[c];
        }
        node.flag=true;
    }

    public boolean existsInTrie(String str){
        TrieTree node=root;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            int c=str.charAt(i)-'a';
            if(node.children[c]==null){
                return false;
            }
            node=node.children[c];
        }
        return node.flag;
    }

    public static void main(String[] args){
        TrieTreeTest test=new TrieTreeTest();

        test.addToTrie("hello");
        boolean exists = test.existsInTrie("hell");
        System.out.println(exists);
        exists = test.existsInTrie("hello");
        System.out.println(exists);
        exists = test.existsInTrie("helloo");
        System.out.println(exists);

    }


}
