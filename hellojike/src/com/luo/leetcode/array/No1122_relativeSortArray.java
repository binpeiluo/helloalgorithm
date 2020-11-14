package com.luo.leetcode.array;

import com.luo.util.CommonUtil;

import java.util.*;

/**
 * 1122. 数组的相对排序
 * 给你两个数组，arr1 和 arr2，
 *
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 *
 * 示例：
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 *
 * 提示：
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i] 各不相同
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 *
 */
public class No1122_relativeSortArray {

    /**
     * 自定义排序
     * 思路:
     *      由于arr1需要保持包含在arr2的元素顺序,可以使用map记录数值在arr2的位置,代表大小关系
     *      然后由此构建排序算法.当两个元素都存在与arr2时,就是比较在map中值大大小.
     *          当两个元素都不存在时,就是比较这两个元素的大小关系.
     *          当任意一个元素在arr2存在时,(由于存在arr2的元素需要放置在前边) 所以也可以获取map.不存在则设置一个很大的值,如此就会放置在后边
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i],i);
        }
        List<Integer> res=new ArrayList<>();
        for (int i = 0; i < arr1.length; i++) {
            res.add(arr1[i]);
        }
        Collections.sort(res,(a,b)->{
            if(map.containsKey(a)||map.containsKey(b)){
                return map.getOrDefault(a,1001)-map.getOrDefault(b,1001);
            }else{
                return a-b;
            }
        });
        int[] result=new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            result[i]=res.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};

        No1122_relativeSortArray test=new No1122_relativeSortArray();
        int[] ints = test.relativeSortArray(arr1, arr2);
        CommonUtil.display(ints);

    }
}
