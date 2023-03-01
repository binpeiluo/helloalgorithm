package com.luo.labuladong.mind.trace;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯系列
 * 不重复数组全排列问题
 */
public class TestPermute {

    /**
     * 回溯算法框架,路径表示当前已经做出的选择,选择列表表示当前可以做出的选择
     *      trace(路径,选择列表):
     *          if 结束条件:
     *              结果.add(路径)
     *          for 选择 in 选择列表:
     *              做出选择
     *              trace(路径,选择列表)
     *              撤销选择
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> result=new ArrayList<>();
        helper(result,new ArrayList<Integer>(),nums);
        return result;
    }

    /**
     *
     * @param result
     * @param currSelect
     * @param nums
     */
    private void helper(List<List<Integer>> result,List<Integer> currSelect,int[] nums){
//        结束条件,当路径长度得到入参的长度时
        if(currSelect.size()==nums.length){
            result.add(new ArrayList<>(currSelect));
            return;
        }
//        做下处理,由于入参数组不会重复,那么可以使用路径判断某个元素是否已经加入
        for (int i = 0; i < nums.length; i++) {
            if(currSelect.contains(nums[i]))
                continue;
//            做出选择
            currSelect.add(nums[i]);
            helper(result,currSelect,nums);
            currSelect.remove(currSelect.size()-1);
        }
    }

    public static void main(String[] args){
        TestPermute test=new TestPermute();
        int[] nums={1,2,3};
        List<List<Integer>> permute = test.permute(nums);
        System.out.println(permute);
    }
}
