package com.luo.leetcode.trace;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 93. 复原IP地址
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 * 示例:
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 */
public class No93_restoreIpAddresses {

    /**
     * 使用回溯
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result=new ArrayList<>();
        trace(result,new ArrayList<>(),s);
        return result;
    }

    private void trace(List<String> result,List<String> curr,String s){
        if("".equals(s) || curr.size()==4){
            if("".equals(s) && curr.size()==4){
                result.add(curr.stream().collect(Collectors.joining(".")));
            }
            return;
        }
        for (int i = 1; i <=3 && i<=s.length(); i++) {
            if(s.charAt(0)=='0'&&i>1){
                return;
            }
            String t = s.substring(0, i);
            if(Integer.parseInt(t)<=255){
                curr.add(t);
                trace(result,curr,s.substring(i));
                curr.remove(curr.size()-1);
            }else{
                break;
            }
        }
    }

    public static void main(String[] args){
        No93_restoreIpAddresses test=new No93_restoreIpAddresses();
//        String str="25525511135";
        String str="010010";

        List<String> strings = test.restoreIpAddresses(str);
        System.out.println(strings);
    }
}
