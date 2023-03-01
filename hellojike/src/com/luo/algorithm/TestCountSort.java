package com.luo.algorithm;

import com.luo.util.CommonUtil;

/**
 * 计数排序
 */
public class TestCountSort {

    /**
     * 计数排序
     *      这可以视为桶排序的一种特例.当待排序的值不会很大时,可以视一个分值为一个桶
     *      这样可减去桶内排序的过程.
     *
     *      为什么在最后排序的过程是从后往前排序?
     *          这是因为从后往前遍历是稳定的.从前往后遍历是非稳定的.
     *          前边在计数的时候,count[i]++ 所以重复的值计算出来的inde会在后边.
     *          此时,从前往后遍历会将首次出现的元素放置在后边.
     *
     *      为什么在计算出count之后,不直接从前到后按次数输出当前元素?
     *          假如待排序元素时数值时可以这么做,
     *          但是加入待排序元素是对象,那么这么做将没有意义.
     * @param nums
     */
    public void countSort(int[] nums){
        if(nums==null||nums.length<2)
            return ;
        int max=-1;
        int size=nums.length;
//        找出分值最大的数
        for(int num:nums){
            max=Math.max(max,num);
        }
//        计算每个分值的个数 count[i] 表示 分值为 i 的元素个数
        int[] count=new int[max+1];
        for(int i=0;i<max;i++){
            count[i]=0;
        }
        for(int i=0;i<size;i++){
            count[nums[i]]++;
        }
//        计算  count[i] 表示分值小于等于 i 的元素的个数
        for(int i=1;i<=max;i++){
            count[i]=count[i-1]+count[i];
        }
//        从后往前计算
        int[] r=new int[size];
        for(int i=size-1;i>=0;i--){
//            获取分值小于等于 nums[i] 的元素的个数c,那么当前元素就在第c个位置,角标c-1
            int index=count[nums[i]]-1;
            r[index]=nums[i];
            count[nums[i]]--;
        }
//        复制回原来的数组
        for(int i=0;i<size;i++){
            nums[i]=r[i];
        }
    }

    /**
     * 使用计数排序对对象进行排序
     * 验证稳定性以及最后的输出问题
     * @param students
     */
    public void countSort(Student[] students){
        if(students==null||students.length<2)
            return ;
        int max=-1;
        int size=students.length;
        for(Student student:students){
            max=Math.max(max,student.score);
        }
        int[] count=new int[max+1];
        for(int i=0;i<size;i++){
            count[students[i].score]++;
        }
        for(int i=1;i<size;i++){
            count[i]=count[i-1]+count[i];
        }
        Student[] result=new Student[size];
        for(int i=size-1;i>=0;i--){
            int index=count[students[i].score]--;
            result[index]=students[i];
            count[students[i].score]--;
        }
        for(int i=0;i<size;i++){
            students[i]=result[i];
        }
    }

    static class Student{
        int score;
        String name;
    }

    public static void main(String[] args){
        TestCountSort test=new TestCountSort();
        int[] nums={2,5,3,0,2,3,0,3};
        CommonUtil.display(nums);
        test.countSort(nums);
        CommonUtil.display(nums);
    }
}
