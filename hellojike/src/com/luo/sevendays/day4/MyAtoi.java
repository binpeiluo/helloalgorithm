package com.luo.sevendays.day4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyAtoi {
    public int myAtoi(String str) {
        String pattern="^\\s*([+-]?\\d+)";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(str);
        if(matcher.find()){
            String group = matcher.group(1);
            try{
                Integer integer = Integer.valueOf(group);
                return integer;
            }catch (Exception e){
                return group.charAt(0)=='-'?Integer.MIN_VALUE:Integer.MAX_VALUE;
            }
        }
        return 0;
    }


    public int myAtoixx(String str) {
        String pattern="^\\s*[+-]?\\d+";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(str);
        if(matcher.find()){
            String group = matcher.group();
            System.out.println(group);
        }
        System.out.println("xxxx");
        return 0;
    }


    public static void main(String[] args){
        MyAtoi test=new MyAtoi();
        test.myAtoi("20000000000000000000");

    }
}
