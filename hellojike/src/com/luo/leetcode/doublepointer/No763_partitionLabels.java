package com.luo.leetcode.doublepointer;

import java.util.ArrayList;
import java.util.List;

/**
 * 763. 划分字母区间
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 *
 * 示例 1：
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *  
 * 提示：
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 */
@SuppressWarnings("Duplicates")
public class No763_partitionLabels {

    /**
     * 切分字符串,同一个字母只能出现在该片段中出现一次.
     * 以此为突破口,从左到右遍历字符串.比较当天位置与扫描过的最大的最后出现的字符的位置
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> result=new ArrayList<>();
        int[] last=new int[26];
        int len=s.length();
//        记录每个字符最后出现的位置
        for (int i = 0; i < len; i++) {
            int index=s.charAt(i)-'a';
            last[index]=i;
        }
//        双指针
        int left,right,temp;
        left=right=temp=0;
        temp=last[s.charAt(left)-'a'];
        while(right<len){
            if(right==temp){
//                到达了分段终点
                result.add(right-left+1);
                left=right=right+1;
                if(right==len){
//                    到了末尾
                    break;
                }else{
//                    还没到末尾,准备下一次双指针遍历
                    temp=last[s.charAt(left)-'a'];
                }
            }else{
//                还没到分段末尾
                temp=Math.max(temp,last[s.charAt(right)-'a']);
                right++;
            }
        }
        return result;
    }

    public List<Integer> partitionLabels2(String s) {
        List<Integer> result=new ArrayList<>();
        int[] last=new int[26];
        int len=s.length();
//        记录每个字符最后出现的位置
        for (int i = 0; i < len; i++) {
            int index=s.charAt(i)-'a';
            last[index]=i;
        }
//        双指针
        int left,right;
        left=right=0;
//        循环遍历
        for (int i=0; i < len; i++) {
//            更新右指针
           right=Math.max(right,last[s.charAt(i)-'a']);
//           判断当前是否到达右指针,到达了则找到了一个片段
           if(i==right){
               result.add(right-left+1);
               left=right=i+1;
           }
        }
        return result;
    }



    public static void main(String[] args) {
        No763_partitionLabels test=new No763_partitionLabels();
//        String s="ababcbacadefegdehijhklij";

        String s="abcdd";

        List<Integer> list = test.partitionLabels(s);
        System.out.println(list);

        List<Integer> list1 = test.partitionLabels2(s);
        System.out.println(list1);
    }
}
