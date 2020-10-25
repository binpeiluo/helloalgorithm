package com.luo.leetcode.dp;

import java.util.Arrays;

/**
 * 1024. 视频拼接
 * 你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
 * 视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。
 * 我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
 *
 * 示例 1：
 * 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 * 输出：3
 * 解释：
 * 我们选中 [0,2], [8,10], [1,9] 这三个片段。
 * 然后，按下面的方案重制比赛片段：
 * 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
 * 现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
 *
 * 示例 2：
 * 输入：clips = [[0,1],[1,2]], T = 5
 * 输出：-1
 * 解释：
 * 我们无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
 *
 * 示例 3：
 * 输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
 * 输出：3
 * 解释：
 * 我们选取片段 [0,4], [4,7] 和 [6,9] 。
 *
 * 示例 4：
 * 输入：clips = [[0,4],[2,8]], T = 5
 * 输出：2
 * 解释：
 * 注意，你可能录制超过比赛结束时间的视频。
 *
 * 提示：
 * 1 <= clips.length <= 100
 * 0 <= clips[i][0] <= clips[i][1] <= 100
 * 0 <= T <= 100
 *
 */
public class No1024_videoStitching {

    /**
     * 动态规划思想
     * 思考是否有重叠子问题,是否符合最优子结构,状态如何转移
     * 最优子结构肯定是符合的,比如dp(10)的最优解肯定包含dp(9)的最优解
     * @param clips
     * @param T
     * @return
     */
    public int videoStitching2(int[][] clips, int T) {
//        确定变量 视频长度
//        确定dp函数定义,dp(i)表示剪切出[0,i)的视频需要的最短长度
//        状态转移方程 dp(i)=min(dp(c[0]))+1 ,枚举每条线段c,满足c[0]<=i<=c[1]
//        状态转移方程,由于是求最小值,所以一开始可以初始化为最大值.同时dp[0]=0
        int len=clips.length;
        int[] dp=new int[T+1];
        Arrays.fill(dp,Integer.MAX_VALUE-1);
        dp[0]=0;
        for (int i = 1; i <=T; i++) {
            for (int j = 0; j < len; j++) {
                if(clips[j][0]<i&&i<=clips[j][1]){
                    dp[i]=Math.min(dp[i],dp[clips[j][0]]+1);
                }
            }
        }
        return dp[T]==Integer.MAX_VALUE-1?-1:dp[T];
    }



    /**
     * 思路  类似于上课,使用贪心算法.
     * 刚开始使用 按clip[1] 升序重新排列clips,但是这样排序有可能造成相同左端点,而长度较短的线段排在前边
     *
     * 贪心算法,对于每次使用的线段,接下来需要找左端点在此线段左边,而右端点尽量靠右的线段.
     * 所以不能通过对线段排序来快速计算接下来取哪条线段,只能从剩下的线段中遍历
     *
     * 那为什么如果是需要尽量多的话,就可以通过对右端点排序呢
     * 如果是需要尽量多的线段的话,可以在剩下的线段中找到左端点大于当前线段而右端点又尽量小的线段
     * @param clips
     * @param T
     * @return
     */
    public int videoStitching(int[][] clips, int T) {
        int len=clips.length;
        int left,right;
        left=right=0;
        for (int i = 0; i < len; i++) {
            if(clips[i][0]<=left){
                right=Math.max(right,clips[i][1]);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        No1024_videoStitching test=new No1024_videoStitching();
        int[][] clips={{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}};
        int t=10;

//        int[][] clips={{0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}};
//        int t=9;

//        int[][] clips={{0,4},{2,8}};
//        int t=5;

//        int i = test.videoStitching(clips, t);
//        System.out.println(i);

        int i1 = test.videoStitching2(clips, t);
        System.out.println(i1);
    }
}
