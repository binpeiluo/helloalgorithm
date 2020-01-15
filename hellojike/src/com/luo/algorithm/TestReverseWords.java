package com.luo.algorithm;

import java.util.Arrays;

public class TestReverseWords {
    public String reverseWords(String s) {
        String[] split=s.trim().split("\\s+");
        if(split==null||split.length==1)
            return s.trim();
        StringBuilder sb=new StringBuilder();
        for(int i=split.length-1;i>=0;i--){
            if(i==0)
                sb.append(split[i]);
            else
                sb.append(split[i]+" ");
        }
        return sb.toString();
    }

    public static void main(String[] args){
        TestReverseWords test=new TestReverseWords();
        String s = test.reverseWords(" ");
        System.out.println(s);
    }
}
