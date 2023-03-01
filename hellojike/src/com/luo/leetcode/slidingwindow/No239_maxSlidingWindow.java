package com.luo.leetcode.slidingwindow;

/*

239. 滑动窗口最大值
给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
返回滑动窗口中的最大值。
进阶：
你能在线性时间复杂度内解决此题吗？

示例:
输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7]
解释:
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
1 [3  -1  -3] 5  3  6  7       3
1  3 [-1  -3  5] 3  6  7       5
1  3  -1 [-3  5  3] 6  7       5
1  3  -1  -3 [5  3  6] 7       6
1  3  -1  -3  5 [3  6  7]      7
*/

import com.luo.util.CommonUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class No239_maxSlidingWindow {

    /**
     * 思路一
     *      使用双端队列,队列保存着长度为k的窗口中的值索引.并且从大到小组织排序
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len=nums.length;
        if(len<2)
            return nums;
//        使用双端队列储存值索引,并且组织
        LinkedList<Integer> list=new LinkedList<>();
        int[] result=new int[len-k+1];
        for (int i = 0; i < len; i++) {
//            在窗口内尾部发现有小于等于当前元素的值,则弹出.保持顺序性
            while(!list.isEmpty() && nums[list.peekLast()]<=nums[i]){
                list.pollLast();
            }
            list.offerLast(i);
//            当发现窗口头部元素过期则清除
            if(list.peekFirst()<=i-k)
                list.pollFirst();
//            获取最大值,赋值给result
            if(i>=k-1)
                result[i-k+1]=nums[list.peekFirst()];
        }
        return result;
    }

    /**
     * 使用单调队列实现
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        List<Integer> result=new ArrayList<>();
        MonotonousQueue window=new MonotonousQueue();
        int len=nums.length;
        for (int i = 0; i < len; i++) {
            int num=nums[i];
            if(i<k-1){
//              窗口还没满则直接写入
//              其实这需要保证有序性
                window.push(num);
            }else{
//              窗口满了
                window.push(num);
                result.add(window.max());
                window.pop(nums[i-k+1]);
            }
        }
        int[] res=new int[result.size()];
        for (int i = 0; i < res.length; i++) {
            res[i]=result.get(i);
        }
        return res;
    }

    static class MonotonousQueue{
        LinkedList<Integer> queue=new LinkedList<>();

//        入队的时候会将队尾小的部分删除,然后在写入队列
//        保持顺序性
//        当队列尾部与需要写入的元素一致时,不能将原来元素删除
        public void push(Integer num){
            while(!queue.isEmpty()&&queue.getLast()<num){
                queue.removeLast();
            }
            queue.addLast(num);
        }
//        队列头部是最大的
        public Integer max(){
            return queue.getFirst();
        }
//        当窗口移动时需要将窗口尾部的数字删除
//        在队列中删除指定数字,因为之前在压入时有可能删除掉了,所以这里需要添加判断
//        有个疑问 删除是判断队列头部是否等于指定值,是的话才删除
//        那么就造成窗口比设定的k还长呢???
//        想一想其实不可能,因为如果此时窗口尾部时最大值时,这里会进行删除
//        如果此时窗口尾部不是最大值,那么在前一个元素压入时就会被删除了
        public void pop(Integer num){
            if(queue.getFirst()==num){
                queue.pollFirst();
            }
        }
    }

    /**
     * 单调队列  直径使用链表的list实现
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        LinkedList<Integer> window=new LinkedList<>();
        int len=nums.length;
        int[] result=new int[len-k+1];
        int index=0;
        for (int i = 0; i < len; i++) {
//            需要加入窗口的元素
            int num=nums[i];
            if(i<k-1){
//              如果窗口还没满 那么直接写入窗口中
//              但是需要保持窗口有序性
//                将单调队列尾部比该元素小的删除.因为小的不可能会被使用
                while(!window.isEmpty()&&window.getLast()<num){
                    window.removeLast();
                }
                window.addLast(num);
            }else{
//                这里需要处理滑动
//                首先在窗口中有序的加入元素
                while(!window.isEmpty()&&window.getLast()<num){
                    window.removeLast();
                }
                window.addLast(num);
//                窗口已经满了,那么获取窗口的最大值,写入结果数组中
                result[index++]=window.getFirst();
//                窗口滑动了,将窗口尾部删除.在队列中有可能在前边元素加入时被删除了,所以判断
                if(window.getFirst()==nums[i-k+1]){
                    window.removeFirst();
                }
            }
        }
        return result;
    }


    public static void main(String[] args){
        No239_maxSlidingWindow test=new No239_maxSlidingWindow();
//        int[] nums = {1,3,-1,-3,5,3,6,7};
//        int k = 3;

        int[] nums={-7,-8,7,5,7,1,6,0};
        int k= 4;

        int[] ints = test.maxSlidingWindow(nums, k);
        CommonUtil.display(ints);

        int[] ints1 = test.maxSlidingWindow2(nums, k);
        CommonUtil.display(ints1);

        int[] ints2 = test.maxSlidingWindow3(nums, k);
        CommonUtil.display(ints2);
    }
}
