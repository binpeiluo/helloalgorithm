package com.luo.leetcode.sort;

import java.util.Arrays;

/*
    274. H指数
    给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。

        h 指数的定义: “h 代表“高引用次数”（high citations），
        一名科研人员的 h 指数是指他（她）的 （N 篇论文中）至多有 h 篇论文分别被引用了至少 h 次。
        （其余的 N - h 篇论文每篇被引用次数不多于 h 次。）”
        示例:
        输入: citations = [3,0,6,1,5]
        输出: 3
        解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
             由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。
        说明: 如果 h 有多种可能的值，h 指数是其中最大的那个。
*/
public class No274_HIndex {

    /**
     * 思路:
     *      想象一个直方图
     *          其中x轴表示w文章,y轴表示文章被引用的次数.引用次数以降序排列
     *          在排序完的citations数组中,如果 citations[i]>i
     *          则表示从第0~i这i+1篇文章中至少都有i+1次引用.
     *          因此我们只要找到满足 citations[i]>i 的 最大i值.h即是i+1
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        Integer[] nums=new Integer[citations.length];
        for(int i=0;i<citations.length;i++){
            nums[i]=citations[i];
        }
        Arrays.sort(nums, (a,b)->b-a);
        int i=0;
        while(i<nums.length&&nums[i]>i){
            i++;
        }
        return i;
    }

    public int hIndex2(int[] citations){
        Arrays.sort(citations);
        int i=0;
        while(i<citations.length&&citations[citations.length-1-i]>i){
            i++;
        }
        return i;
    }

    /**
     * 基于自己理解实现基于计数排序
     * @param citations
     * @return
     */
    public int hIndex3(int[] citations){
        int n=citations.length;
        int[] tmp=new int[n+1];
        for(int i=0;i<n;i++){
            tmp[Math.min(n,citations[i])]++;
        }
//        k为代表引用为k次,tmp[k] 代表引用大于等于k的论文篇数
        for(int i=n-1;i>=0;i--){
            tmp[i]=tmp[i]+tmp[i+1];
        }
//        至少有n篇引用超过n,即求 tmp[n]>=n 的最大n值
        int i=n;
        while(i>=0&&i>tmp[i]){
            i--;
        }
        return i;
    }

    public int hIndex4(int[] citations){
        int n = citations.length;
        int[] papers = new int[n + 1];
        // 计数
        for (int c: citations)
            papers[Math.min(n, c)]++;
        // 找出最大的 k
        int k = n;
        for (int s = papers[n]; k > s; s += papers[k])
            k--;
        return k;
    }

    public static void main(String[] args){
        No274_HIndex test=new No274_HIndex();
        int[] a={3,12};
        int corret = test.hIndex(a);
        System.out.println("curror=="+corret);
        int i = test.hIndex3(a);
        System.out.println("hIndex3=="+i);

        int hIndex4 = test.hIndex4(a);
        System.out.println("hIndex4=="+hIndex4);

    }
}
