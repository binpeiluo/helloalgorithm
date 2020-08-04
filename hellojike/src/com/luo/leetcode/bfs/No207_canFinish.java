package com.luo.leetcode.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 *
 * 在选修某些课程之前需要一些先修课程。 
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 *
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 */
@SuppressWarnings("Duplicates")
public class No207_canFinish {
    /**
     * 题目可以简化为判断是否出现有向环
     * @param numCourses 课程数量
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
//        统计课程安排图中每个节点的入度，生成 入度表 indegrees
        int[] indegrees = new int[numCourses];
//        通过课程前置条件列表 prerequisites 可以得到课程安排图的 邻接表 adjacency
        List<List<Integer>> adjacency = new ArrayList<>();
//        借助一个队列 queue，将所有入度为 00 的节点入队。
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        // Get the indegree and adjacency of every course.
        for(int[] cp : prerequisites) {
            indegrees[cp[0]]++;
            adjacency.get(cp[1]).add(cp[0]);
        }
        // Get all the courses with the indegree of 0.
        for(int i = 0; i < numCourses; i++)
            if(indegrees[i] == 0) queue.add(i);
        // BFS TopSort.
        while(!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;
            for(int cur : adjacency.get(pre))
                if(--indegrees[cur] == 0) queue.add(cur);
        }
        return numCourses == 0;
    }

    /**
     * 参考上边的思路
     * 题目这种课程有顺序关系,很明显时有向图.可以使用连接表储存顺序关系
     * 有向图的每一个节点有一个入度的属性,代表依赖这个节点的数量.
     * 当一个节点入度为0时,说明没有依赖的其他节点,可以将依赖此节点的节点入度减一
     *  ,同时判断入度是否为0,以进行下一轮的判断
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish1(int numCourses, int[][] prerequisites){
//        储存每个节点的入度
        int[] indegress=new int[numCourses];
        List<List<Integer>> adjacency=new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList());
//        储存邻接表
        for (int[] p:prerequisites){
            indegress[p[0]]++;
            adjacency.get(p[1]).add(p[0]);
        }
//        使用队列储存入度为0的节点,以判断
        Queue<Integer> queue=new LinkedList<>();
//        将初始入度为0的节点入队
//        将当前需要学习的课程数量-1,因为该课程不需要依赖其他课程
//        这里的判断一开始有问题,因为在这里numCourses自减,所以循环过程中不可以依赖numCourses值
//        为了简便,可以将numCourses--的操作移动到queue.poll时
        for (int i = 0,len=numCourses; i < len; i++) {
            if(indegress[i]==0){
                numCourses--;
                queue.offer(i);
            }
        }
//        将入度为0的节点取出,将依赖此节点的节点的入度-1
        while(!queue.isEmpty()){
            Integer poll = queue.poll();
            for (Integer next:adjacency.get(poll)){
//                其实可以不需要这两行
                if(indegress[next]==0)
                    continue;
                indegress[next]--;
                if(indegress[next]==0){
                    queue.offer(next);
                    numCourses--;
                }
            }
        }
        return numCourses==0;
    }

    /**
     * 根据前边的思路,使用dfs实现
     * 解答不正确??? 哪里有问题
     *
     * bfs的原理是通过遍历入度为0的节点,以此更新依赖此节点的节点的入度信息
     * 遍历完判断是否还存在没有学习的节点
     *
     * 而dfs的原理是判断是否出现有向环,使用入度这种来判断显得虽然显得复杂,但是应该也是正确的才是
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites){
//        每个节点的入度
        int[] indegress=new int[numCourses];
        List<List<Integer>> adjacency=new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
//        计算入度
        for (int[] p:prerequisites){
            indegress[p[0]]++;
            adjacency.get(p[1]).add(p[0]);
        }
//        使用一个数组储存剩余学习的课程数量
        int[] retail={numCourses};
        for (int i = 0; i < numCourses; i++) {
            if(indegress[i]==0&&retail[0]!=0)
                dfs2(retail,indegress,adjacency,i);
        }
        return retail[0]==0;
    }

    private void dfs2(int[] retail,
                      int[] indegress,
                      List<List<Integer>> adjacency,
                      int i) {
        retail[0]--;
        for (Integer next:adjacency.get(i)){
            indegress[next]--;
            if(indegress[next]==0)
                dfs2(retail,indegress,adjacency,next);
        }
    }

    /**
     * 使用dfs判断是否存在环
     * @param numCourses
     * @param prerequisites
     * @return
     *
     * 时间复杂度 O(N+M)：
     *  遍历一个图需要访问所有节点和所有临边，NN 和 MM 分别为节点数量和临边数量
     * 空间复杂度 O(N+M)：
     *  为建立邻接表所需额外空间，adjacency 长度为 NN ，并存储 MM 条临边的数据
     */
    public boolean canFinish3(int numCourses, int[][] prerequisites) {
//        邻接表
        List<List<Integer>> adjacency = new ArrayList<>();
        for(int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
//        借助一个标志列表 flags，用于判断每个节点 i （课程）的状态
//        未被 DFS 访问：0 ,已被其他节点启动的DFS访问：-1,已被当前节点启动的DFS访问：1
        int[] flags = new int[numCourses];
        for(int[] cp : prerequisites)
            adjacency.get(cp[1]).add(cp[0]);
        for(int i = 0; i < numCourses; i++)
            if(!dfs3(adjacency, flags, i)) return false;
//        若整个图 DFS 结束并未发现环，返回 True
        return true;
    }

    /**
     * 是否无环
     * @param adjacency
     * @param flags
     * @param i
     * @return
     */
    private boolean dfs3(List<List<Integer>> adjacency, int[] flags, int i) {
//        当 flag[i] == 1，说明在本轮 DFS 搜索中节点 i 被第 2 次访问，
//        即 课程安排图有环 ，直接返回 False
        if(flags[i] == 1) return false;
//        当 flag[i] == -1，说明当前访问节点已被其他节点启动的 DFS 访问，
//        无需再重复搜索，直接返回 True
        if(flags[i] == -1) return true;
//        将当前访问节点 i 对应 flag[i] 置 1，即标记其被本轮 DFS 访问过
        flags[i] = 1;
//        递归访问当前节点 i 的所有邻接节点 j，当发现环直接返回 False
        for(Integer j : adjacency.get(i))
            if(!dfs3(adjacency, flags, j)) return false;
//        当前节点所有邻接节点已被遍历，并没有发现环，则将当前节点 flag 置为 -1−1 并返回 True
        flags[i] = -1;
        return true;
    }

    /**
     * dfs方式 判断有向环问题
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish4(int numCourses, int[][] prerequisites) {
//        对于与每个节点,被访问时的状态值 0没有别访问过,-1已经被其他路径访问过,1被当前路径访问过
        int[] flag=new int[numCourses];
//        邻接表
        List<List<Integer>> adjacency=new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        for (int[] p:prerequisites)
            adjacency.get(p[1]).add(p[0]);
        for (int i = 0; i < numCourses; i++) {
            if(!dfs4(flag,adjacency,i))
                return false;
        }
        return true;
    }

    private boolean dfs4(int[] flag,List<List<Integer>> adjacency,int curr){
//        本次循环已经出现环了
        if(flag[curr]==1)
            return false;
//        避免重复循环
        if(flag[curr]==-1)
            return true;
        flag[curr]=1;
        for(Integer next:adjacency.get(curr)){
            if(!dfs4(flag,adjacency,next))
                return false;
        }
        flag[curr]=-1;
        return true;
    }


    public boolean canFinish5(int numCourses, int[][] prerequisites) {
//        每个节点的入度
        int[] indegress=new int[numCourses];
//        某个课程学完后可以学习什么课程
        List<List<Integer>> adjust=new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjust.add(new ArrayList<>());
        }
        for (int i = 0,len=prerequisites.length; i < len; i++) {
            indegress[prerequisites[i][0]]++;
            adjust.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        Queue<Integer> queue=new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if(indegress[i]==0){
                queue.offer(i);
            }
        }
        int retailNum=numCourses;
        while(!queue.isEmpty()){
            Integer poll = queue.poll();
            retailNum--;
            for(Integer next:adjust.get(poll)){
                indegress[next]--;
                if(indegress[next]==0){
                    queue.offer(next);
                }
            }
        }
        return retailNum==0;
    }

    public static void main(String[] args){
        No207_canFinish test=new No207_canFinish();
        int numCourses=2;
//        int[][] prerequisites={};
//        int[][] prerequisites={{1,0}};
        int[][] prerequisites={{0,1},{1,0}};

        boolean b = test.canFinish(numCourses, prerequisites);
        System.out.println(b);

        boolean b1 = test.canFinish1(numCourses, prerequisites);
        System.out.println(b1);

        boolean b2 = test.canFinish2(numCourses, prerequisites);
        System.out.println(b2);

        boolean b3 = test.canFinish3(numCourses, prerequisites);
        System.out.println(b3);

        boolean b4 = test.canFinish4(numCourses, prerequisites);
        System.out.println(b4);

        boolean b5 = test.canFinish5(numCourses, prerequisites);
        System.out.println(b5);
    }

}
