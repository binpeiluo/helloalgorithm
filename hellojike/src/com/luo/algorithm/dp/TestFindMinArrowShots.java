package com.luo.algorithm.dp;

import java.util.Arrays;

/**
 * 动态规划 贪心算法
 * 452. 用最少数量的箭引爆气球
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。
 * 由于它是水平的，所以y坐标并不重要，因此只要知道开始和结束的x坐标就足够了。
 * 开始坐标总是小于结束坐标。平面内最多存在104个气球。
 *
 * 一支弓箭可以沿着x轴从不同点完全垂直地射出。在坐标x处射出一支箭，
 * 若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，
 * 则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。
 * 我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 *
 */
public class TestFindMinArrowShots {
    public int findMinArrowShots(int[][] intvs){
        Arrays.sort(intvs,(i1,i2)->{
            return i1[1]-i2[1];
        });
        int len=intvs.length;
        int res=0;
        int i=0;
        while(i<len){
            res++;
            int[] curr=intvs[i];
            while(i+1<len&&intvs[i+1][0]<=curr[1]){
                i++;
            }
            i++;
        }
        return res;
    }

    public static void main(String[] args){
        TestFindMinArrowShots test=new TestFindMinArrowShots();
        int[][] invts={{10,16}, {2,8}, {1,6}, {7,12}};
        int minArrowShots = test.findMinArrowShots(invts);
        System.out.println(minArrowShots);
    }
}
