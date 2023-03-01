package com.luo.leetcode.slidingwindow;

/*
1004. 最大连续1的个数 III
给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
返回仅包含 1 的最长（连续）子数组的长度。
示例 1：
输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
输出：6
解释：
[1,1,1,0,0,1,1,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 6。
示例 2：
输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
输出：10
解释：
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 10。
*/

public class No1004_longestOnes {

    /**
     * 滑动窗口法
     *  维护两个指针,当k>0时尽可能移动右指针
     *  当k已经用完时,移动左指针.
     * @param a
     * @param k
     * @return
     */
    public int longestOnes(int[] a, int k) {
        int len=a.length;
        int left=0,right=0,t=k,res=0;
        while(right<len){

//            当k还为饱和时,继续移动右指针
            while(right<len&&(a[right]==1||t>0)){
                if(a[right]==0){
                    t--;
                }
                right++;
            }

            res=Math.max(res,right-left);

//            考虑移动左指针,当左指针当前为0时,
            if(a[left]==0){
                left++;
//                t++;
//                这里不能使用 t++
//                会出现,前边while条件不再符合,右指针不在移动,但是left一直移动知道超过right然后越界.
//                另外一种情况是,k本来就等于0,这时候会死循环

//              这时候顺便移动右指针才是正确的
                right++;
            }else{
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args){
        No1004_longestOnes test=new No1004_longestOnes();
//        int[] nums={1,1,1,0,0,0,1,1,1,1,0};
//        int k=2;

//        int[] nums={0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
//        int k=3;

        int[] nums={0,0,1,1,1,0,0};
        int k=0;

        int i = test.longestOnes(nums, k);
        System.out.println(i);
    }
}
