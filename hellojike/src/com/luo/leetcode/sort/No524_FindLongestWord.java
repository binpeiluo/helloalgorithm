package com.luo.leetcode.sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*
    524. 通过删除字母匹配到字典里最长单词
    给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。
    如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
        示例 1:
        输入:
        s = "abpcplea", d = ["ale","apple","monkey","plea"]
        输出:
        "apple"
        示例 2:
        输入:
        s = "abpcplea", d = ["a","b","c"]
        输出:
        "a"
        说明:
        所有输入的字符串只包含小写字母。
        字典的大小不会超过 1000。
        所有输入的字符串长度不会超过 1000。
*/
public class No524_FindLongestWord {

    /**
     * 思路:
     *      使用两个指针,分别指向原字符串字符角标i与待匹配字符串的角标j.
     *      判断两个角标指向的字符大小,当两字符相等时,往后移动.
     *      当当不相等时,只有角标i移动.
     *
     *      当j角标等于该字符串的长度时,则说明该字符串可以由原字符串删除特定字符得到.
     *      由于需要返回长度最长的字符串或者字典序列前的字符串,故需另外判断
     *
     *      时间复杂度:  O(mn),m为d字符串列表长度,n为原字符串长度
     *      空间复杂度:  O(1)
     * @param s
     * @param d
     * @return
     */
    public String findLongestWord(String s, List<String> d) {
        String res="";
        for(String ds:d){
            for(int i=0,j=0;i<s.length()&&j<ds.length();i++){
                if(s.charAt(i)==ds.charAt(j))
                    j++;
                if(j==ds.length()){
                    if(ds.length()>res.length()||(ds.length()==res.length()&&ds.compareTo(res)<1))
                        res=ds;
                }
            }
        }
        return res;
    }

    /**
     * 思路:
     *      使用一个set储存由str减去特定字符组成的字符串列表.
     *      接着判断d列表是否含有该字符串列表中的元素.
     *
     *      由str减去若干个字符生成的字符串与由str若干个字符生成的字符串可以理解为一个含义.
     *      使用递归思想完成此部分的函数,递归函数的终止条件在于当角标已经超过str的长度.
     *
     *      时间复杂度:  O(2^n)
     *      空间复杂度:  O(2^n)
     * @param s
     * @param d
     * @return
     */
    public String findLongestWord2(String s, List<String> d) {
        String res="";
        HashSet<String> fullStrs=new HashSet<>();
        generate(s,"",0,fullStrs);
        for(String str:fullStrs){
            if(d.contains(str)&&(str.length()>res.length()||(str.length()==res.length()&&str.compareTo(res)<1)))
                res=str;
        }
        return res;
    }
    private void generate(String s,String subStr,int index,HashSet<String> fullStrs){
        if(index==s.length()){
            fullStrs.add(subStr);
        }else{
            generate(s,subStr,index+1,fullStrs);
            generate(s,subStr+s.charAt(index),index+1,fullStrs);
        }

    }


    public static void main(String[] args){
        No524_FindLongestWord test=new No524_FindLongestWord();
        String s="abpcplea";
        List<String> d = Arrays.asList("ale","apple","monkey","plea");
        String longestWord = test.findLongestWord2(s, d);
        System.out.println(longestWord);
    }
}
