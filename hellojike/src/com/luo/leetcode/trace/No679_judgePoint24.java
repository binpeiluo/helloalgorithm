package com.luo.leetcode.trace;

import java.util.ArrayList;
import java.util.List;

/**
 * 679. 24 点游戏
 * 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
 *
 * 示例 1:
 *
 * 输入: [4, 1, 8, 7]
 * 输出: True
 * 解释: (8-4) * (7-1) = 24
 * 示例 2:
 *
 * 输入: [1, 2, 1, 2]
 * 输出: False
 * 注意:
 *
 * 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。
 * 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允许的。
 * 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。
 *
 */
public class No679_judgePoint24 {

    /**
     * 穷举
     * 从四个挑选出两个,计算可能的结果.在分别和另外两个数运算.
     * 有可能另外两个数需要优先计算
     * @param nums
     * @return
     */
    public boolean judgePoint24(int[] nums) {
        List<Double> list=new ArrayList<>();
        for (int n:nums){
            list.add(n*1.0);
        }
        boolean result = trace(list);
        return result;
    }

    private boolean trace(List<Double> list){
        if(list.size()==1){
//            判断是否是24
            return Math.abs(list.get(0)-24)<0.0000001;
        }
        int len=list.size();
        for (int i = 0; i < len; i++) {
            Double left = list.get(i);
            Double right=null;
            for (int j = 0; j < len; j++) {
                if(j==i){
                    continue;
                }
                right=list.get(j);

                Double twoRes=null;
                for (int k = 0; k < 4; k++) {
                    if(k==0){
                        twoRes=left+right;
                    }else if(k==1){
                        twoRes=left-right;
                    }else if(k==2){
                        twoRes=left*right;
                    }else if(k==3){
                        if(right==0){
                            continue;
                        }
                        twoRes=left/right;
                    }
                    if(i>j){
                        list.remove(i);
                        list.remove(j);
                    }else{
                        list.remove(j);
                        list.remove(i);
                    }
                    list.add(twoRes);
                    if(trace(list)){
                        return true;
                    }
                    list.remove(list.size()-1);
                    if(i>j){
                        list.add(j,right);
                        list.add(i,left);
                    }else{
                        list.add(i,left);
                        list.add(j,right);
                    }
                }
            }
        }
        return false;
    }


    public boolean judgePoint24_2(int[] nums) {
        return backTrack_2(nums, 0);
    }

    // 第一步：求出所有排列，一一验证
    private boolean backTrack_2(int[] nums, int index) {
        if (index == 4) {
            return judge_2(nums[0], nums[1], nums[2], nums[3]);
        }
        for (int i = index; i < 4; i++) {
            swap_2(nums, index, i);
            if (backTrack_2(nums, index + 1)) return true;
            swap_2(nums, index, i);
        }
        return false;
    }

    private void swap_2(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 第二步：由于已经全排列，a、b、c、d 都是等价的，利用四种运算符选出三个数继续
    private boolean judge_2(double a, double b, double c, double d) {
        return judge_2(a + b, c, d) ||
                judge_2(a - b, c, d) ||
                judge_2(a * b, c, d) ||
                judge_2(a / b, c, d);
    }

    // 第三步：a 是有两个数组成的，b、c 只表示一个数，等价
    private boolean judge_2(double a, double b, double c) {
        // 情况一：a 和 b(c) 组合，a 和 b(c) 不等价，- 号和 / 号需要考虑两种情况
        return judge_2(a + b, c) ||
                judge_2(a - b, c) ||
                judge_2(a * b, c) ||
                judge_2(a / b, c) ||
                judge_2(b - a, c) ||
                judge_2(b / a, c) ||
                // 情况二：b 和 c 组合
                judge_2(a, b - c) ||
                judge_2(a, b + c) ||
                judge_2(a, b * c) ||
                judge_2(a, b / c);
    }

    // 第四步：a 和 b 不等价
    private boolean judge_2(double a, double b) {
        return Math.abs(a + b - 24) < 0.001 ||
                Math.abs(a - b - 24) < 0.001 ||
                Math.abs(a * b - 24) < 0.001 ||
                Math.abs(a / b - 24) < 0.001 ||
                Math.abs(b - a - 24) < 0.001 ||
                Math.abs(b / a - 24) < 0.001;
    }


    public static void main(String[] args){
        No679_judgePoint24 test=new No679_judgePoint24();
//        int[] nums={4, 1, 8, 7};
        int[] nums={1, 2, 1, 2};

        boolean b = test.judgePoint24(nums);
        System.out.println(b);
    }
}
