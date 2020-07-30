package com.luo.leetcode.bfs;

import java.util.*;

/**
 * LCP 13. 寻宝
 * 我们得到了一副藏宝图，藏宝图显示，在一个迷宫中存在着未被世人发现的宝藏。
 * 迷宫是一个二维矩阵，用一个字符串数组表示。它标识了唯一的入口（用 'S' 表示），和唯一的宝藏地点（用 'T' 表示）。
 * 但是，宝藏被一些隐蔽的机关保护了起来。在地图上有若干个机关点（用 'M' 表示），只有所有机关均被触发，才可以拿到宝藏。
 * 要保持机关的触发，需要把一个重石放在上面。迷宫中有若干个石堆（用 'O' 表示），每个石堆都有无限个足够触发机关的重石。
 * 但是由于石头太重，我们一次只能搬一个石头到指定地点。
 * 迷宫中同样有一些墙壁（用 '#' 表示），我们不能走入墙壁。剩余的都是可随意通行的点（用 '.' 表示）。
 * 石堆、机关、起点和终点（无论是否能拿到宝藏）也是可以通行的。
 * 我们每步可以选择向上/向下/向左/向右移动一格，并且不能移出迷宫。搬起石头和放下石头不算步数。
 * 那么，从起点开始，我们最少需要多少步才能最后拿到宝藏呢？如果无法拿到宝藏，返回 -1 。
 *
 * 示例 1：
 * 输入： ["S#O", "M..", "M.T"]
 * 输出：16
 * 解释：最优路线为： S->O, cost = 4, 去搬石头 O->第二行的M, cost = 3, M机关触发 第二行的M->O, cost = 3, 我们需要继续回去 O 搬石头。
 * O->第三行的M, cost = 4, 此时所有机关均触发 第三行的M->T, cost = 2，去T点拿宝藏。 总步数为16。
 *
 */
@SuppressWarnings("Duplicates")
public class No_lcp_13_minimalSteps {

    /**
     * 官方题解,牛皮
     * @param maze
     * @return
     */
    public int minimalSteps(String[] maze) {
        int n = maze.length;
        int m = maze[0].length();
        // 机关 & 石头
        List<int[]> buttons = new ArrayList<int[]>();
        List<int[]> stones = new ArrayList<int[]>();
        // 起点 & 终点
        int sx = -1, sy = -1, tx = -1, ty = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i].charAt(j) == 'M') {
                    buttons.add(new int[]{i, j});
                }
                if (maze[i].charAt(j) == 'O') {
                    stones.add(new int[]{i, j});
                }
                if (maze[i].charAt(j) == 'S') {
                    sx = i;
                    sy = j;
                }
                if (maze[i].charAt(j) == 'T') {
                    tx = i;
                    ty = j;
                }
            }
        }
//        机关数量
        int mSize = buttons.size();
//        石头数量
        int sSize = stones.size();
//        以入口为起点,计算到各个位置的距离
        int[][] startDist = bfs(sx, sy, maze,n,m);

        // 边界情况：没有机关
        if (mSize == 0) {
            return startDist[tx][ty];
        }
        // 从某个机关到其他机关 / 起点与终点的最短距离。
//        坐标 mSize 表示起点,mSize+1 表示终点
        int[][] mDistance = new int[mSize][mSize + 2];
        for (int i = 0; i < mSize; i++) {
            Arrays.fill(mDistance[i], -1);
        }
        // 中间结果
//        以某个机关为起点,计算到各个位置的距离
        int[][][] dd = new int[mSize][][];
//        遍历每个机关
        for (int i = 0; i < mSize; i++) {
            int[][] d = bfs(buttons.get(i)[0], buttons.get(i)[1], maze,n,m);
            dd[i] = d;
            // 从某个点到终点不需要拿石头
//            为什么在这里赋值???,因为每个机关最后都需要到终点,顺便一起遍历
            mDistance[i][mSize + 1] = d[tx][ty];
        }

//        遍历每个机关位置
        for (int i = 0; i < mSize; i++) {
            int tmp = -1;
//            遍历每个石头位置
            for (int k = 0; k < sSize; k++) {
//                当前石头位置
                int midX = stones.get(k)[0], midY = stones.get(k)[1];
//                从某个机关到该石头可达且从入口到该石头位置可达
                if (dd[i][midX][midY] != -1 && startDist[midX][midY] != -1) {
//                    dd[i][midX][midY] 表示从第i个机关到第k个石头的距离
//                    startDist[midX][midY] 表示从起点到第k个石头的距离
//                    所以这里是计算从入口走到某个石头然后到达某个机关的最近距离
                    if (tmp == -1 || tmp > dd[i][midX][midY] + startDist[midX][midY]) {
                        tmp = dd[i][midX][midY] + startDist[midX][midY];
                    }
                }
            }
//            已经计算处从第i个机关走到某个石头然后再到起点的距离,其实也是入口取石头然后到第i个机关的距离
            mDistance[i][mSize] = tmp;
//            从第i+1个机关开始遍历每个机关
//            已经处理了从入口->某一个机关,接下来就是某一个机关->另一个机关
            for (int j = i + 1; j < mSize; j++) {
                int mn = -1;
//                遍历每个石头
                for (int k = 0; k < sSize; k++) {
//                    每个石头的位置
                    int midX = stones.get(k)[0], midY = stones.get(k)[1];
//                    从第i个机关到第k个石头可达,且从第k个石头到第j个机关可达
                    if (dd[i][midX][midY] != -1 && dd[j][midX][midY] != -1) {
//                        dd[i][midX][midY] 表示从第i个机关到第k个石头距离
//                        dd[j][midX][midY] 表示从第k个石头到第j个开关可达
//                        实际是在计算从第i个机关出发取石头后到第j个机关的最小路径
                        if (mn == -1 || mn > dd[i][midX][midY] + dd[j][midX][midY]) {
                            mn = dd[i][midX][midY] + dd[j][midX][midY];
                        }
                    }
                }
//                更新从第i个机关出发取石头后到第j个机关的最小距离
                mDistance[i][j] = mn;
//                同时反过来也使用,从第j个机关出发取石头后到第i个机关的最小距离
                mDistance[j][i] = mn;
            }
        }

        // 无法达成的情形
//        遍历每个机关,当发现有机关无法走到起点或者终点,那么肯定无法完成
        for (int i = 0; i < mSize; i++) {
            if (mDistance[i][mSize] == -1 || mDistance[i][mSize + 1] == -1) {
                return -1;
            }
        }

        // dp 数组， -1 代表没有遍历到
//        定义 f(mask,i) 表示当前在第 i 个 M 处，触发状态为 mask 的最小步数
//        dp数组,一维表示当前机关触发状态,二维表示当前机关坐标
        int[][] dp = new int[1 << mSize][mSize];
//        初始为无法到达
        for (int i = 0; i < 1 << mSize; i++) {
            Arrays.fill(dp[i], -1);
        }
//        表示触发第i个机关的最小路径
        for (int i = 0; i < mSize; i++) {
            dp[1 << i][i] = mDistance[i][mSize];
        }
//        状态转移方程为 dp[mask][i]= min( dp[mask xor j][j] + mDistance[j][i] ) , j为机关的位置
//        状态转移 mask 肯定大于 mask xor j ,但是 i和j的值没有大小关系
        // 由于更新的状态都比未更新的大，所以直接从小到大遍历即可
        for (int mask = 1; mask < (1 << mSize); mask++) {
//            遍历每个机关
            for (int i = 0; i < mSize; i++) {
                // 当前 dp 是合法的,
//                (mask & (1 << i))!=0 表示第i个机关被触发
//                可是为什么呢??? 为什么这就是合法
//                因为需要从第i个机关没有被触发的状态转移到第i个机关被触发
                if ((mask & (1 << i)) != 0) {
//                    再次遍历每个机关
                    for (int j = 0; j < mSize; j++) {
                        // j 不在 mask 里
//                        (mask & (1 << j)) == 0 表示第j个机关还没有被触发
                        if ((mask & (1 << j)) == 0) {
//                            表示当前mask 加上触发第j个机关后的mask
                            int next = mask | (1 << j);
                            if (dp[next][j] == -1 || dp[next][j] > dp[mask][i] + mDistance[i][j]) {
                                dp[next][j] = dp[mask][i] + mDistance[i][j];
                            }
                        }
                    }
                }
            }
        }

        int ret = -1;
//        表示全部机关触发时的mask
        int finalMask = (1 << mSize) - 1;
//        遍历所有机关,加上最后一步,从已经完成触发所有机关的位置走到出口的距离,取最小值
        for (int i = 0; i < mSize; i++) {
            if (ret == -1 || ret > dp[finalMask][i] + mDistance[i][mSize + 1]) {
                ret = dp[finalMask][i] + mDistance[i][mSize + 1];
            }
        }

        return ret;
    }

    /**
     * 以某一点作为起点,计算到各个位置的距离
     * @param x 起点x坐标
     * @param y 起点y坐标
     * @param maze 返回二位距离数组
     * @return
     */
    private int[][] bfs(int x, int y, String[] maze,int n,int m) {
        int[][] direct={{0,1},{0,-1},{1,0},{-1,0}};
        int[][] ret = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(ret[i], -1);
        }
        ret[x][y] = 0;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            int curx = p[0], cury = p[1];
            for (int i = 0; i < 4; i++) {
                int nx = curx + direct[i][0], ny = cury + direct[i][1];
                if (inBound(nx, ny,n,m) && maze[nx].charAt(ny) != '#' && ret[nx][ny] == -1) {
                    ret[nx][ny] = ret[curx][cury] + 1;
                    queue.offer(new int[]{nx, ny});
                }

            }
        }
        return ret;
    }

    private boolean inBound(int x, int y,int n,int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }


    public static void main(String[] args){
        No_lcp_13_minimalSteps test=new No_lcp_13_minimalSteps();
        String[] maze={
                "S#O",
                "M..",
                "M.T"};

        int i = test.minimalSteps(maze);
        System.out.println(i);
    }

}
