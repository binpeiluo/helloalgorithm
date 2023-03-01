package com.luo.leetcode.trace;

import java.util.Stack;
import java.util.stream.Collectors;

/*
    60. 第k个排列
    给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
        按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
        "123"
        "132"
        "213"
        "231"
        "312"
        "321"
        给定 n 和 k，返回第 k 个排列。
        说明：
        给定 n 的范围是 [1, 9]。
        给定 k 的范围是[1,  n!]。
        示例 1:
        输入: n = 3, k = 3
        输出: "213"
        示例 2:
        输入: n = 4, k = 9
        输出: "2314"
*/
public class No60_GetPermutation {

    private int curr=0;
    private Stack<Integer> result;
    private boolean isFound=false;

    /**
     * 思路:
     *      最简单的思路是选择出全排列中第k个排列.可以在生成全排列的回溯算法中控制.
     *      但是该算法复杂度爆炸,差点超时
     *
     *      时间复杂度:  O(n^k)
     *      空间复杂度:  O(n^k)
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        Stack<Integer> stack=new Stack<>();
        helper(n,k,stack);
        return result.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private void helper(int n,int k, Stack<Integer> stack){
        if(isFound)
            return;
        if(stack.size()==n){
            curr++;
            if(curr==k){
                result=stack;
                isFound=true;
            }
            return;
        }
        for(int i=1,len=n;i<=len;i++){
            if(!stack.contains(i)){
                stack.push(i);
                helper(n,k,stack);
                if(!isFound)
                    stack.pop();
            }
        }
    }


    //---------------------------------------------------------------------

    /**
     * 思路:
     *      假设当前n=4,k=9
     *      我们可以将全排列分为4组,每组(4-1)!=6种情况.
     *      k=9,很明显.该排列会出现在第2组,再计算偏移量 9%6=3
     *
     *      时间复杂度:  O()
     *      空间复杂度:  O()
     * @param n
     * @param k
     * @return
     */
    public String getPermutation2(int n, int k) {
        boolean[] visited = new boolean[n];
        // 将 n! 种排列分为：n 组，每组有 (n - 1)! 种排列
        return recursive(n, factorial(n - 1), k, visited);
    }

    /**
     * @param n 剩余的数字个数，递减
     * @param f 每组的排列个数
     */
    private String recursive(int n, int f, int k, boolean[]visited){
        int offset = k%f;// 组内偏移量,数值从0~f-1
        int groupIndex = k/f + (offset > 0 ? 1 : 0);// 第几组,数值从1~n
        // 在没有被访问的数字里找第 groupIndex 个数字
        int i = 0;//实际上当前groupIndex指向的数组,数值 1~n
        for(; i < visited.length && groupIndex > 0; i++){
            if(!visited[i]){
                groupIndex--;
            }
        }
//        由于for循环最后是i+1,故这里需要将i-1,才能代表当前索引
        visited[i-1] = true;// 标记为已访问
        if(n - 1 > 0){
            // offset = 0 时，则取第 i 组的第 f 个排列，否则取第 i 组的第 offset 个排列
            return String.valueOf(i) + recursive(n-1, f/(n - 1), offset == 0 ? f : offset, visited);
        }else{
            // 最后一数字
            return String.valueOf(i);
        }
    }

    /**
     * 求 n!
     */
    private int factorial(int n){
        int res = 1;
        for(int i = n; i > 1; i--){
            res *= i;
        }
        return res;
    }
    //---------------------------------------------------------------------


    //--自己尝试--------------------------------------------------------------------------------

    public String getPermutation3(int n,int k){
//        需要使用辅助数组记录哪些数字已经被使用
        boolean[] visited=new boolean[n];
//        将n!个组合分成n个(n-1)!组合
        return helper3(n,facotrial3(n-1),k,visited);
    }

    private String helper3(int n,int f,int k,boolean[] visited){
//        组内偏移量
        int offset=k%f;
//        哪一组,这里 == 判断符的优先级很低,需要使用括号
        int groupIndex=k/f+(offset==0?0:1);
//        有的数字可能已经使用过了
        int index=0;
        for(;index<visited.length&&groupIndex>0;index++){
            if(!visited[index])
                groupIndex--;
        }
        visited[index-1]=true;
        if(n-1>0)
            return String.valueOf(index)+helper3(n-1,f/(n-1),offset==0?f:offset,visited);
        else
            return String.valueOf(index);
    }
    private int facotrial3(int n){
        int res=1;
        while (n>1){
            res*=n;
            n--;
        }
        return res;
    }

    //--自己尝试--------------------------------------------------------------------------------


    public static void main(String[] args){
        No60_GetPermutation test=new No60_GetPermutation();
        String permutation = test.getPermutation(4, 9);
        System.out.println(permutation);

        String permutation2 = test.getPermutation2(4, 9);
        System.out.println(permutation2);

        String permutation3 = test.getPermutation3(4, 9);
        System.out.println(permutation3);
    }
}
