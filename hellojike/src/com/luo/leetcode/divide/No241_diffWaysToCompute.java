package com.luo.leetcode.divide;

import java.util.ArrayList;
import java.util.List;

/**
 * 241. 为运算表达式设计优先级
 * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。
 * 示例 1:
 * 输入: "2-1-1"
 * 输出: [0, 2]
 * 解释:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * 示例 2:
 * 输入: "2*3-4*5"
 * 输出: [-34, -14, -10, -10, 10]
 * 解释:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 *
 */
public class No241_diffWaysToCompute {

    /**
     * 分治思想
     * 思路:
     *      遍历word,当遇到运算符号时,将word切分为两部分.分别计算两部分的运算结果,然后对两边的运算结果取笛卡尔积
     *      当word只有数字时,即不能再分子问题
     *
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result=new ArrayList<>();
        int len=input.length();
        int num=0;
        boolean onlyNum=true;
        for (int i = 0; i < len; i++) {
            if(Character.isDigit(input.charAt(i))){
                num=num*10+(input.charAt(i)-'0');
//                需要注意 当word全部是数字时,返回只包含该数字的列表
                if(i==len-1&&onlyNum)
                    result.add(num);
            }else{
                num=0;
                onlyNum=false;
                String pre = input.substring(0, i);
                String suf = input.substring(i + 1, len);
                List<Integer> preList = diffWaysToCompute(pre);
                List<Integer> sufList = diffWaysToCompute(suf);
                for (int j = 0,preLen=preList.size(); j < preLen; j++) {
                    for (int k = 0,sufLen=sufList.size(); k <sufLen ; k++) {
                        char op=input.charAt(i);
                        int s=0;
                        if(op=='-')
                            s=preList.get(j)-sufList.get(k);
                        else if(op=='+')
                            s=preList.get(j)+sufList.get(k);
                        else if(op=='*')
                            s=preList.get(j)*sufList.get(k);
                        result.add(s);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        No241_diffWaysToCompute test=new No241_diffWaysToCompute();
//        String word="2-1-1";
        String word="2*3-4*5";
        List<Integer> list = test.diffWaysToCompute(word);
        System.out.println(list);
    }
}
