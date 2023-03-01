package com.luo.leetcode.stack;

/*
394. 字符串解码
给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。
        注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，
        例如不会出现像 3a 或 2[4] 的输入。

示例:
s = "3[a]2[bc]", 返回 "aaabcbc".
s = "3[a2[c]]", 返回 "accaccacc".
s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
*/

import java.util.Stack;

public class NO394_decodeString {

    public String decodeString(String s) {
        int len = s.length();
        int num = 0;
        Stack<Integer> numstack=new Stack<>();
        Stack<String> strstack=new Stack<>();
        String cur = "";
        String result = "";
        for(int i=0; i<len; ++i) {
            if(s.charAt(i)>='0' && s.charAt(i)<='9')
                num = 10*num + s.charAt(i) - '0';
            else if(s.charAt(i) == '[') {
//                遇到左括号,则push入数字和字符串.
//                然后将当前的数字和字符串置空
                numstack.push(num);
                strstack.push(cur);
                num = 0;
                cur="";
            }else if((s.charAt(i)>='a' && s.charAt(i)<='z')
                    || (s.charAt(i)>='A' && s.charAt(i)<='Z'))
                cur += s.charAt(i);
            else if(s.charAt(i) == ']') {
                int k = numstack.pop();
                String pop = strstack.pop();
                for(int j=0; j<k; ++j)
                    pop += cur;
                cur = pop;
            }
        }
        result = result + cur;
        return result;
    }

    public String decodeString2(String s){
        Stack<String> stack=new Stack<String>();
        for(int i=0;i<s.length();i++) {
//             如果遇到 ']'，就一直在栈中找到 '['，将之间的字符连接起来
            if(s.charAt(i)==']') {
                String string="";
                while(!stack.peek().equals("[")) {
                    string=stack.pop()+string;
                }
                stack.pop();
                String countString="";
//                将 '['前面的数字连接起来作为出现次数再次压栈
                while((!stack.isEmpty())
                        &&(stack.peek().charAt(0)>='0'
                        &&stack.peek().charAt(0)<='9')) {
                    countString=stack.pop()+countString;
                }
                int count=Integer.parseInt(countString);
                String retString="";
                for(int j=0;j<count;j++) {
                    retString=retString+string;
                }
                stack.push(retString);
            }
//            遇到数字、字母、'['就直接压栈
            else {
                String str=""+s.charAt(i);
                stack.push(str);
            }
        }
//        最后将栈里的字符串弹出连接起来就ok了
        String aaa="";
        while(!stack.isEmpty()) {
            aaa=stack.pop()+aaa;
        }
        return aaa;
    }


    public static void main(String[] args){
        NO394_decodeString test=new NO394_decodeString();
//        String s = "3[a]2[bc]";
//        String s="3[a2[c]]";
        String s="2[abc]3[cd]ef";

        String s1 = test.decodeString(s);
        System.out.println(s1);

        String s2 = test.decodeString2(s);
        System.out.println(s2);


    }
}
