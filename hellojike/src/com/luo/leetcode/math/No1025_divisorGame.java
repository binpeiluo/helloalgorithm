package com.luo.leetcode.math;

/**
 * 1025. 除数博弈
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
 *
 * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
 * 用 N - x 替换黑板上的数字 N 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 *
 * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
 *
 * 示例 1：
 * 输入：2
 * 输出：true
 * 解释：爱丽丝选择 1，鲍勃无法进行操作。
 *
 * 示例 2：
 * 输入：3
 * 输出：false
 * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
 *  
 * 提示：
 * 1 <= N <= 1000
 *
 */
public class No1025_divisorGame {

    /**
     * 博弈逻辑.
     * 当alice面对2的时候必定赢,所以alice只需要剩余2时是自己做选择即可
     * 当alice面对3时,别无选择,只有执行x=1,如此会将情况2拱手让给对面,自己则输
     *
     * 地推 假设res[i] 表示当前面对数字i时,是否能取胜
     * 遍历数字从 1~i-1 .当 i%j==0 时 且当 res[i-j]==false (表示当面对i-j数字时必定输) 时自己肯定赢
     * @param N
     * @return
     */
    public boolean divisorGame(int N) {
        boolean[] res=new boolean[N+2];
        res[1]=false;
        res[2]=true;
        for (int i = 3; i <=N; i++) {
            for (int j = 1; j < i-1 ; j++) {
                if( i%j==0 && !res[i-j]){
                    res[i]=true;
                    break;
                }
            }
        }
        return res[N];
    }

    public static void main(String[] args){
        No1025_divisorGame test=new No1025_divisorGame();
        int n=10000;

        boolean b = test.divisorGame(n);
        System.out.println(b);
    }
}
