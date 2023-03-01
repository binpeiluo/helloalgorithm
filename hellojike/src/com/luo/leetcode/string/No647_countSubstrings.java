package com.luo.leetcode.string;

/**
 * 647. 回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 * 示例 1：
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 *
 * 示例 2：
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 */
@SuppressWarnings("Duplicates")
public class No647_countSubstrings {

    /**
     * 最naive方法,暴力
     * 时间复杂度 O(n^3)
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int len=s.length();
        int res=0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if(valid(s.substring(i,j+1))){
                    res++;
                }
            }
        }
        return res;
    }

    private boolean valid(String str){
        if(str==null||str.length()==1){
            return true;
        }
        int len=str.length();
        for (int i = 0; i <= len / 2; i++) {
            if(str.charAt(i)!=str.charAt(len-1-i)){
                return false;
            }
        }
        return true;
    }

    /**
     * 通过遍历每一个字符,然后向两边扩散判断
     * @param s
     * @return
     */
    public int countSubstrings2(String s) {
        int len=s.length();
        int res=0;
//        遍历每个位置
        for (int i = 0; i < len; i++) {
//            最大步长
            int maxStep=Math.min(i+1,len-i);
//            以该位置为中心
            for (int j = 0; j <maxStep; j++) {
                boolean eq=s.charAt(i-j)==s.charAt(i+j);
                if(eq){
                    res++;
                }else{
                    break;
                }
            }

//            以该位置和后一个字符为中心
            maxStep=Math.min(i+1,len-i-1);
            for (int j = 0; j <maxStep; j++) {
                boolean eq=s.charAt(i-j)==s.charAt(i+1+j);
                if(eq){
                    res++;
                }else{
                    break;
                }
            }
        }
        return res;
    }


    /**
     * 官方的中心扩散
     * @param s
     * @return
     */
    public int countSubstrings3(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }


    public static void main(String[] args){
        No647_countSubstrings test=new No647_countSubstrings();
//        String str="abc";
        String str="aabaa";


        int i = test.countSubstrings(str);
        System.out.println(i);

        int i1 = test.countSubstrings2(str);
        System.out.println(i1);

        int i2 = test.countSubstrings3(str);
        System.out.println(i2);


    }
}
