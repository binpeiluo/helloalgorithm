package com.luo.leetcode.slidingwindow;

public class No1687_boxDelivering {

    // 思路 滑动窗口
    // 原来这里的行程定义 比如从仓库到A卸货, 再去B卸货, 再去C卸货, 行程等于1(码头到A)+1(A到B)+1(B到C)+1(C到码头)
    // 这样子就不能单纯使用动态规划计算了, 因为这时的滑动窗口满了, 但是后边的箱子是可以和前边在一起卸货了, 那么就会增加行程数量
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        // 箱子数组长度
        int len = boxes.length;
        int left=0, right=0;
        int result=0;
        while(left < len){
            right=left;
            int windowCnt=1, windowWeight=boxes[left][1];
            // 标记窗口内只能先递增后递减, 或者递减
            boolean flag=true;
            while(right+1<len && windowCnt+1 < maxBoxes && windowWeight+boxes[right+1][1] < maxWeight){
                // 已经是递减了, 接下来是递增则不能再滑动了
                if(!flag && windowWeight+boxes[right+1][1] > windowWeight+boxes[right][1]){
                    break;
                }
                // 开始递减了
                if(flag && windowWeight+boxes[right+1][1] < windowWeight+boxes[right][1]){
                    flag=false;
                }
                right++;
                windowCnt++;
                windowWeight+=boxes[right][1];
            }
            result +=2;
            left=right+1;
        }
        return result;
    }

    public static void main(String[] args) {
        No1687_boxDelivering test = new No1687_boxDelivering();
//        boxes = [[1,1],[2,1],[1,1]], portsCount = 2, maxBoxes = 3, maxWeight = 3      答案4
//        int[][] boxes = {{1, 1}, {2, 1}, {1, 1}};
//        int portsCount=2, maxBoxes = 3, maxWeight = 3;

//        boxes = [[1,2],[3,3],[3,1],[3,1],[2,4]], portsCount = 3, maxBoxes = 3, maxWeight = 6      答案6
//        int[][] boxes = {{1, 2}, {3, 3}, {3, 1}, {3, 1}, {2, 4}};
//        int portsCount=3, maxBoxes = 3, maxWeight = 6;

//        boxes = [[1,4],[1,2],[2,1],[2,1],[3,2],[3,4]], portsCount = 3, maxBoxes = 6, maxWeight = 7  答案6
//        int[][] boxes = {{1, 4}, {1, 2}, {2, 1}, {2, 1}, {3, 2}, {3, 4}};
//        int portsCount=3, maxBoxes = 6, maxWeight = 7;

//        boxes = [[2,4],[2,5],[3,1],[3,2],[3,7],[3,1],[4,4],[1,3],[5,2]], portsCount = 5, maxBoxes = 5, maxWeight = 7     答案14
        int[][] boxes = {{2,4},{2,5},{3,1},{3,2},{3,7},{3,1},{4,4},{1,3},{5,2}};
        int portsCount=5, maxBoxes = 5, maxWeight = 7;

        int result = test.boxDelivering(boxes, portsCount, maxBoxes, maxWeight);
        System.out.println(result);
    }
}
