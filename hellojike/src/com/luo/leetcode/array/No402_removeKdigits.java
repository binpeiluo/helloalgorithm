package com.luo.leetcode.array;

/**
 * 402. 移掉K位数字
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *
 * 注意:
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 *
 * 示例 1 :
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 *
 * 示例 2 :
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 *
 * 示例 3 :
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 *
 */
public class No402_removeKdigits {

    /**
     * 这种数字题就是根据逻辑找到规律
     * 判断第一位数字和第二位数字大小,如果第一位数字大于第二位数字,那么肯定是删除第一位的数字.
     *      如果是第一位小于第二位数字,那么就找到大于第一位数字的最大数字
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        StringBuilder sb=new StringBuilder();
        sb.append(num);
        for (int i = 0; i < k; i++) {
            int idx=0;
            for (int j = 1; j < sb.length()&& sb.charAt(j)>=sb.charAt(j-1); j++) {
                idx=j;
            }
            sb.deleteCharAt(idx);
        }
        while(sb.length()>0&&sb.charAt(0)=='0'){
            sb.deleteCharAt(0);
        }
        if(sb.length()==0){
            sb.append("0");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        No402_removeKdigits test=new No402_removeKdigits();

//        String num = "1432219";
//        int k = 3;

//        String num = "10200";
//        int k = 1;

        String num = "112";
        int k = 1;

        String s = test.removeKdigits(num, k);
        System.out.println(s);

    }
}
