package com.luo.leetcode.trace;

import java.util.*;

/**
 * 491. 递增子序列
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，
 * 递增子序列的长度至少是2。
 * 示例:
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7],
 *  [6, 7, 7], [7,7], [4,7,7]]
 *
 * 说明:
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 *
 */
public class No491_findSubsequences {

    /**
     * 暴力算法可以使用二位数组代表是否选择当前元素来构建数组
     * 然后判断是否递增
     *
     * 这里使用回溯算法
     *
     * 这种方式实在丑陋
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        List<Integer> curr=new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            curr.clear();
            curr.add(nums[i]);
            trace(result,curr,nums,i);
        }
        return result;
    }

    /**
     * 回溯
     * 使用 start 参数指定开始第一个元素
     * 但是相同数组会造成重复,比如 4,7   4,7
     * 可以通过构造一个hash 表示列表中可选数字中没有选择的数字
     * 比如当前发现到7这个数字,当发现前边已经有7这数字,而且没有选择时,可以不必选择
     * @param result
     * @param curr
     * @param nums
     * @param start
     */
    private void trace(
            List<List<Integer>> result,
            List<Integer> curr,
            int[] nums,
            int start){
//        终止条件是啥???
//        为什么不需要
//        因为不会无限制递归
        if(curr.size()>1){
            result.add(new ArrayList<>(curr));
        }
        for (int i = start+1,len=nums.length; i < len; i++) {
//            小于curr最后一个元素,则不考虑
            if(nums[i]<nums[start]){
                continue;
            }

//            这里剪枝有两个条件
//            1 当前元素前边有相同元素
//            2 前边的相同元素没有被选择

//            还是不对  淦

            boolean hasFront=false;
            for (int j = 0; j <= i-1; j++) {
                if(nums[j]==nums[i]){
                    hasFront=true;
                    break;
                }
            }
            boolean contain=curr.contains(nums[i]);
            if(hasFront&&!contain){
                continue;
            }

            curr.add(nums[i]);
            trace(result,curr,nums,i);
            curr.remove(curr.size()-1);
        }
    }

    /**
     * 参考别人的代码,加上自己的思路
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences2(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        List<Integer> curr=new ArrayList<>();
        trace2(result,curr,-1,nums);
        return result;
    }

    private void trace2(List<List<Integer>> result,
                        List<Integer> curr,
                        int idx,
                        int[] nums){
        if(curr.size()>1){
            result.add(new ArrayList<>(curr));
        }

        Set<Integer> set=new HashSet<>();
        for (int i = idx+1,len=nums.length; i < len; i++) {
            if(set.contains(nums[i])){
                continue;
            }
            if(idx>-1&&nums[i]<nums[idx]){
                continue;
            }
            curr.add(nums[i]);
            set.add(nums[i]);
            trace2(result,curr,i,nums);
            curr.remove(curr.size()-1);
        }
    }


    /**
     * 剪枝技巧 real nice
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequencesOther(int[] nums) {
        // idx 初始化为 -1，开始 dfs 搜索。
        List<List<Integer>> res = new ArrayList<>();
        dfsOther(res,nums, -1, new ArrayList<>());
        return res;
    }

    private void dfsOther(List<List<Integer>> res, int[] nums, int idx, List<Integer> curList) {
        // 只要当前的递增序列长度大于 1，就加入到结果 res 中，然后继续搜索递增序列的下一个值。
        if (curList.size() > 1) {
            res.add(new ArrayList<>(curList));
        }

        // 在 [idx + 1, nums.length - 1] 范围内遍历搜索递增序列的下一个值。
        // 借助 set 对 [idx + 1, nums.length - 1] 范围内的数去重。

//        此set的作用于在于一次dfs,每个dfs都有一个set
//        假如一次dfs含有4层子dfs,set记录当前选择过的数字.
//        假如当前选择的数组是已经包含的,则可以跳过.
//        那这样子会不会造成 1 1 1 这含有相同数字的组合被跳过呢
//        其实是不会的,因为这个组合已经在这个dfs的子dfs添加过了

        Set<Integer> set = new HashSet<>();
        for (int i = idx + 1; i < nums.length; i++) {
            // 1. 如果 set 中已经有与 nums[i] 相同的值了，说明加上 nums[i] 后的所有可能的递增序列之前已经被搜过一遍了，因此停止继续搜索。
            if (set.contains(nums[i])) {
                continue;
            }
            set.add(nums[i]);
            // 2. 如果 nums[i] >= nums[idx] 的话，说明出现了新的递增序列，因此继续 dfs 搜索（因为 curList 在这里是复用的，因此别忘了 remove 哦）
            if (idx == -1 || nums[i] >= nums[idx]) {
                curList.add(nums[i]);
                dfsOther(res,nums, i, curList);
                curList.remove(curList.size() - 1);
            }
        }
    }

    public static void main(String[] args){
        No491_findSubsequences test=new No491_findSubsequences();
//        int[] nums={4, 6, 7, 7};
        int[] nums={1,2,3,1,1};
//        int[] nums={1,1,1};

        List<List<Integer>> subsequences = test.findSubsequences(nums);
        System.out.println(subsequences);

        List<List<Integer>> subsequences1 = test.findSubsequencesOther(nums);
        System.out.println(subsequences1);

        List<List<Integer>> subsequences2 = test.findSubsequences2(nums);
        System.out.println(subsequences2);


    }

}
