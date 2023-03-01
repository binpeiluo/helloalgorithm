package com.luo.leetcode.trace;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 89. 格雷编码
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
     * 00 - 0
     * 01 - 1
     * 11 - 3
     * 10 - 2
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
     * 00 - 0
     * 10 - 2
     * 11 - 3
     * 01 - 1
 * 示例 2:
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 *      给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
 *      因此，当 n = 0 时，其格雷编码序列为 [0]。
 *
 */
public class No89_grayCode {

    /**
     * 思路:
     *  使用n位数组代表当前值,然后修改当前数组中一个元素,其实就是回溯
     *
     *  时间复杂度:  O(2^n) 回溯过程
     *  空间复杂度:  O(n*2^n),辅助visited,set中每个值长度为n,个数为2^n
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> result=new ArrayList<>();
        result.add(0);
        if(n==0)
            return result;
        boolean[] curr=new boolean[n];
        Set<String> visited=new HashSet<>();
        visited.add(key(curr));
        while(result.size()<Math.pow(2,n)){
            boolean[] next = next(visited,curr);
            result.add(value(curr));
            curr=next;
        }
        return result;
    }

    private boolean[] next(Set<String> visited,boolean[] curr){
        int len=curr.length;
        for (int i = 0; i < len; i++) {
            curr[i]=!curr[i];
            if(visited.contains(key(curr))){
                curr[i]=!curr[i];
                continue;
            }
            visited.add(key(curr));
            return curr;
        }
        return null;
    }

    private int value(boolean[] curr){
        int res=0;
        int len=curr.length;
        for (int i = len-1; i >=0 ; i--) {
            res+=(curr[i]?1:0)*Math.pow(2,len-1-i);
        }
        return res;
    }

    private String key(boolean[] curr){
        String key=null;
        for (boolean b:curr)
            key+=b?"1":"0";
        return key;
    }

    /**
     * 列出n=1,2,3等格雷数时,能发现规律
     * @param n
     * @return
     */
    public List<Integer> grayCode2(int n) {
        List<Integer> result=new ArrayList<>();
        result.add(0);
        for (int i = 0; i <n; i++) {
            int size = result.size();
            int base= (int) Math.pow(2,i);
            for (int j = 0; j < size; j++) {
                result.add(base+result.get(size-1-j));
            }
        }
        return result;
    }

    public static void main(String[] args){
        No89_grayCode test=new No89_grayCode();
        int n=2;
        List<Integer> list = test.grayCode(n);
        System.out.println(list);

        List<Integer> list1 = test.grayCode2(n);
        System.out.println(list1);
    }

}
