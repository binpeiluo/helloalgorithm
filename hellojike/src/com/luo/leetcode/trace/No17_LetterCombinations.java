package com.luo.leetcode.trace;

import java.util.*;

/*
    17. 电话号码的字母组合
    给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
        给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
        示例:
        输入："23"
        输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
        说明:
        尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
*/
public class No17_LetterCombinations {

    /**
     * 思路:
     *      很明显这种问题类似于树的遍历问题.使用回溯思想解决,参见labuladong关于回溯套路的文章
     *      回溯思想主要涉及到三个方面:结束条件,做出选择,撤销选择
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if(digits==null||"".equals(digits))
            return Collections.emptyList();

        Map<Integer,List<String>> numberMap=new HashMap<>();
        numberMap.put(2, Arrays.asList("a","b","c"));
        numberMap.put(3, Arrays.asList("d","e","f"));
        numberMap.put(4, Arrays.asList("g","h","i"));
        numberMap.put(5, Arrays.asList("j","k","l"));
        numberMap.put(6, Arrays.asList("m","n","o"));
        numberMap.put(7, Arrays.asList("p","q","r","s"));
        numberMap.put(8, Arrays.asList("t","u","v"));
        numberMap.put(9, Arrays.asList("w","x","y","z"));

        List<String> result=new ArrayList<>();
        helper(numberMap,result,digits,"");
        return result;

    }

    private void helper(Map<Integer,List<String>> numberMap,List<String> result,String digits,String currStr){
        if(currStr.length()==digits.length()){
            result.add(currStr);
            return;
        }
        Integer integer = Integer.valueOf(digits.charAt(currStr.length()) + "");
        for(int i=0,len=numberMap.get(integer).size();i<len;i++){
            helper(numberMap,result,digits,currStr+numberMap.get(integer).get(i));
        }
    }

    public static void main(String[] args){
        No17_LetterCombinations test=new No17_LetterCombinations();
        String digits="23";
        List<String> strings = test.letterCombinations(digits);
        System.out.println(strings);
    }
}
