package com.luo.zhinan.stack_queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个数组与一个值, 寻找有多少个子数组内的最大值和最小值相差小于num
 */
public class GetArrayNum {

    /**
     * 如果a[i,j]符合条件, 那么a[p,q]也符合, i<=p<=q<=j
     * 如果a[i,j]不符合条件, 那么a[p,q]也不会符合, p<=i<=j<=q
     * 使用滑动窗口, 不断右移右侧指针, 直到不符合条件, 那么这个窗口符合条件的就有 (j-1)*i/2 ?
     * 然后在从j开始重新开始? 仔细想想并不行, 因为可能还有些符合条件的数组包含窗口右部分和接下一些元素, 如果从j开始重新滑动窗口会导致计算少
     *
     * 那么修改为不断右移右侧指针, 直到不符合条件, 此时符合条件的 以i为起点的数组有 j-1-i个. 移动指针的过程如何记录窗口的最大值和最小值呢? 使用栈
     * 接下来再移动左侧指针知道符合条件, 同时维护最小栈和最大栈的结构, 此时再重复上述操作
     * @param a
     * @param num
     * @return
     */
    public static int getArrayNum(int[] a, int num){
        if(a==null||a.length==0){
            return 0;
        }
        int left=0, right=0,len=a.length;
        Deque<Integer> minDeque=new ArrayDeque<>(), maxDeque=new ArrayDeque<>();
        int result=0;
        for (left = 0; left < len; left++) {
            // 前一个元素可能出队列
            if(left-1>=0){
                if(minDeque.getFirst()==a[left-1]){
                    minDeque.removeFirst();
                }
                if(maxDeque.getFirst()==a[left-1]){
                    maxDeque.removeFirst();
                }
            }

            // 当前元素入队列
            if(!minDeque.isEmpty() && a[left]<minDeque.getLast()){
                minDeque.pollLast();
            }
            minDeque.addLast(a[left]);
            if(!maxDeque.isEmpty() && a[left]>maxDeque.getLast()){
                maxDeque.pollLast();
            }
            maxDeque.addLast(a[left]);

            System.out.println("min deque:" + minDeque.toString());
            System.out.println("max deque:" + maxDeque.toString());

            // 一直移动右指针 直到不符合条件
            right = left;
            int diff=0;
            while(right< len && (maxDeque.getFirst()-minDeque.getFirst()) < num){
                if(!minDeque.isEmpty() && a[right]<minDeque.getLast()){
                    minDeque.pollLast();
                }
                minDeque.addLast(a[right]);
                if(!maxDeque.isEmpty() && a[right]>maxDeque.getLast()){
                    maxDeque.pollLast();
                }
                maxDeque.addLast(a[right]);
                diff = right-left+1;
                right++;
            }
            result += diff;
            System.out.println("min deque:" + minDeque.toString());
            System.out.println("max deque:" + maxDeque.toString());
            System.out.println("result="+result);
            System.out.println();
        }
        return result;
    }

    /**
     * 按照这种方式, 最普通的解法, 计算出所有的子数组, 然后遍历获取最大值和最小值, 计算是否符合, 遍历时间复杂度为O(N^3)
     *
     * 使用滑动窗口, 使用单调栈维护窗口内的最大值, 如此可以在O(1)时间复杂度内获取窗口内最大值/最小值, 时间复杂度为O(N), 空间复杂度也为O(N)
     * 如果a[i,j]符合条件, 那么a[p,q]也符合, i<=p<=q<=j
     * 如果a[i,j]不符合条件, 那么a[p,q]也不会符合, p<=i<=j<=q
     * 所以可以使用两个指针, 固定i, 符合条件时不断扩大j, 直到不符合条件, 此时这个数组内部的子数组都符合, 但只计算从i开始的数组
     *      接着右移i, 再重复上述步骤
     *
     * @param a
     * @param num
     * @return
     */
    public static int getArrayNumV2(int[] a, int num){
        if(a==null||a.length==0){
            return 0;
        }
        int len=a.length;
        // 两个单调双端队列  一个最大栈, 一个最小栈
        // 使用滑动窗口, i,j, 维护滑动窗口内的最大队列和最小队列
        // 单调栈内的值必须是元素的角标, 因为需要在最小/最大元素滑出窗口时, 将元素从单调栈移除. 如果储存的是元素值, 那么无法做到
        // 当新的元素到来时,   最大栈内小于等于此值的元素都可以被遗弃, 所以这时从最大栈的最小端开始移除元素, 然后再将新元素角标入队列
        //                  最小栈内大于等于此值的元素都可以被遗弃, 所以这时从最小栈的最大栈开始移除元素, 然后再将新元素角标入队列
        // 当元素滑出窗口时, 判断该元素是否为最大栈最大值时/最小栈最小值时, 将其出队列
        LinkedList<Integer> qMin = new LinkedList<>();
        LinkedList<Integer> qMax = new LinkedList<>();
        int i=0, j=0, res=0;
        while(i<len){
            // j角标不需要重置吗?
            // 不需要, 因为根据 '如果a[i,j]符合条件, 那么a[p,q]也符合, i<=p<=q<=j'和'如果a[i,j]不符合条件, 那么a[p,q]也不会符合, p<=i<=j<=q'
            // 这两条结论, 其子数组都符合条件, 就算因为右窗口不满足条件而不能右移时, 左窗口便会不断右移, 将其子数组都计算进来
            while(j<len){
                while(!qMin.isEmpty()&&a[j]<=a[qMin.peekLast()]){
                    qMin.pollLast();
                }
                qMin.addLast(j);
                while(!qMax.isEmpty()&&a[j]>=a[qMax.peekLast()]){
                    qMax.pollLast();
                }
                qMax.addLast(j);
                if(a[qMax.getFirst()]-a[qMin.getFirst()]>num){
                    break;
                }
                j++;
            }
            if(qMax.peekFirst()==i){
                qMax.pollFirst();
            }
            if(qMin.peekFirst()==i){
                qMin.pollFirst();
            }
            res+=j-i;
            i++;
        }
        return res;
    }


    public static void main(String[] args) {
        int[] a={1,2,3,4,5};
        int num =2;
        int result = GetArrayNum.getArrayNumV2(a, num);
        System.out.println(result);
    }
}
