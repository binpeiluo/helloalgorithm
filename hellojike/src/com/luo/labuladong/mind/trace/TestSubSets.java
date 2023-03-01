package com.luo.labuladong.mind.trace;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯问题
 * 输入一个不包含重复数字的数组，要求算法输出这些数字的所有子集
 * 比如输入 nums = [1,2,3]，你的算法应输出 8 个子集，包含空集和本身，顺序可以不同：
 *  [ [],[1],[2],[3],[1,3],[2,3],[1,2],[1,2,3] ]
 */
public class TestSubSets {

    /**
     * 可以理解为分别加入长度为 0~len-1 的排列,不需要考虑顺序性
     *  在回溯框架上,可以添加一个最大长度标示.
     *  当选择的路径已经达到最大长度时,加入当前路径.
     *  但是另外有相同数据不同顺序的问题,可以添加一个start标示,标示当前路径选择列表只能选择start之后的值
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> result=new ArrayList<>();
        int len=nums.length;
        for (int i = 0; i <= len; i++) {
            helper(result,i,nums);
        }
        return result;
    }

    private void helper(List<List<Integer>> result,int maxLen,int[] nums){
        helperInner(result,maxLen,0,nums,new ArrayList<>());
    }

    private void helperInner(List<List<Integer>> result,int maxLen,int start,int[] nums,List<Integer> currSelected){
        if(currSelected.size()==maxLen){
            result.add(new ArrayList<>(currSelected));
            return;
        }
        for (int i = start; i < nums.length; i++) {
//            路径已经有了,跳过
            if(currSelected.contains(nums[i]))
                continue;
            currSelected.add(nums[i]);
//            这里 start=start+1 逻辑是不正确的,会导致重复数据
//            需要使用 start=i+1
            helperInner(result,maxLen,i+1,nums,currSelected);
            currSelected.remove(currSelected.size()-1);
        }
    }

    /**
     * 归纳法
     * 假设 subsets(1,2) =[[],[1],[2],[1,2]]
     * 那么 subsets(1,2,3) - subsets(1,2) = [[3],[1,3],[2,3],[1,2,3]]
     * 故 subsets(1,2,3)= subsets(1,2) + for int[].add(3) in subsets(1,2)
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums){
        return null;
    }

    /**
     * 改造回溯算法
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets3(int[] nums) {
        List<Integer> currSelect=new ArrayList<>();
        List<List<Integer>> result=new ArrayList<>();
        helper3(result,currSelect,0,nums);
        return result;
    }

    /**
     * 解释是 可以看见，对 result 更新的位置处在前序遍历???
     * 没看懂
     * @param result
     * @param currSelected
     * @param start
     * @param nums
     */
    private void helper3(List<List<Integer>> result,List<Integer> currSelected,int start,int[] nums){
        result.add(new ArrayList<>(currSelected));
        for (int i = start; i < nums.length; i++) {
            currSelected.add(nums[i]);
            helper3(result,currSelected,i+1,nums);
            currSelected.remove(currSelected.size()-1);
        }
    }

    public static void main(String[] args){
        TestSubSets test=new TestSubSets();
        int[] nums={1,2,3};
        List<List<Integer>> subsets = test.subsets(nums);
        System.out.println(subsets);

        List<List<Integer>> lists = test.subsets3(nums);
        System.out.println(lists);

    }
}
