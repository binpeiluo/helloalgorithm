package com.luo.leetcode.bfs;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

/**
 * 753. 破解保险箱
 * 有一个需要密码才能打开的保险箱。密码是 n 位数, 密码的每一位是 k 位序列 0, 1, ..., k-1 中的一个 。
 * 你可以随意输入密码，保险箱会自动记住最后 n 位输入，如果匹配，则能够打开保险箱。
 * 举个例子，假设密码是 "345"，你可以输入 "012345" 来打开它，只是你输入了 6 个字符.
 * 请返回一个能打开保险箱的最短字符串。
 *
 * 示例1:
 * 输入: n = 1, k = 2
 * 输出: "01"
 * 说明: "10"也可以打开保险箱。
 *
 * 示例2:
 * 输入: n = 2, k = 2
 * 输出: "00110"
 * 说明: "01100", "10011", "11001" 也能打开保险箱。
 *  
 * 提示：
 * n 的范围是 [1, 4]。
 * k 的范围是 [1, 10]。
 * k^n 最大可能为 4096。
 *
 */
public class No753_crackSafe {

    /**
     * Hierholzer算法(插入回路法)
     * 该算法的思想是一步步构造出回路，最终找到欧拉回路。
     * 由欧拉图的充要条件：
     *      G是欧拉图<==>G是若干个边不重的圈(环)的并，
     * 我们可以先找到一个环，而剩下的边一定还存在环，且这两个部分必有公共点，
     * 从而可以形成更大的环，这样直到包括所有边，即可找到欧拉回路。
     * 该算法时间复杂度为O(V+E)O(V+E)，非常高效。
     * 设置一个栈curPath和结果序列loop。算法过程：
     * （1）选择任一顶点为起点，入栈curPath，深度搜索访问顶点，
     *      将经过的边都删除，经过的顶点入栈curPath。
     * （2）如果当前顶点没有相邻边，则将该顶点从curPath出栈到loop。
     * （3）loop中的顶点逆序，就是从起点出发的欧拉回路。(当然顺序也是)
     *
     * 有点意思,似懂非懂
     * @param n
     * @param k
     * @return
     */
    public String crackSafeOther(int n, int k) {
        StringBuilder res;
        TreeSet<String> visited;
        if(n == 1 && k == 1) return "0";
        visited = new TreeSet<>();
        res = new StringBuilder();
        //  从顶点 00..0 开始
        String start = String.join("", Collections.nCopies(n-1, "0"));
        findEulerOther(res,visited,start, k);

        res.append(start); // 回路添加最后的end顶点，end 就是 start
        return res.toString(); // return new String(res);
    }

    private void findEulerOther(
            StringBuilder res,
            TreeSet<String> visited,
            String curv,
            int k){

//        visited 用于记录访问过的节点

        for(int i = 0; i < k; i ++){
            // 往顶点的 k 条出边检查，顶点加一条出边就是一种密码可能
            String nextv = curv + i;
//            检查所有相邻的边
//            如果没有访问过
            if(!visited.contains(nextv)){
//                当前被访问
                visited.add(nextv);
//                dfs 从一个节点访问其相邻的边
                findEulerOther(res,visited,nextv.substring(1), k);
//
                res.append(i);
            }
        }
    }


    public static void main(String[] args){
        No753_crackSafe test=new No753_crackSafe();
        int n=2,k=2;
        String s = test.crackSafeOther(n, k);
        System.out.println(s);

    }

}
