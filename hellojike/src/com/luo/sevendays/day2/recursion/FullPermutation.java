package com.luo.sevendays.day2.recursion;

import com.luo.util.CommonUtil;

import java.util.*;

/**
 * 实现数据全排列
 */
public class FullPermutation {
    List<String> res=new ArrayList<String>();

    /**
     * 全排序 回溯+递归
     * @param cs
     * @param lo
     */
    void fullPermutation(char[] cs,int lo){
        if(lo==cs.length-1)
            res.add(String.valueOf(cs));
        else{
            for(int i=lo;i<=cs.length-1;i++){
                if(!swapAccept(cs,lo,i))
                    continue;
                CommonUtil.exch(cs,lo,i);
                fullPermutation(cs,lo+1);
                CommonUtil.exch(cs,lo,i);
            }
        }
    }

    /**
     * 判断是否符合交换条件
     * @param cs
     * @param lo
     * @param hi
     * @return
     */
    boolean swapAccept(char[] cs,int lo,int hi){
        for(int i=lo;i<hi;i++){
            if(cs[i]==cs[hi])
                return false;
        }
        return true;
    }

    /**
     * 字典序法
     * @param cs
     */
    List<String> dictPermutation(char[] cs){
        Arrays.sort(cs);
        List<String> res=new ArrayList<String>();
        while(true){
            res.add(String.valueOf(cs));
            //从右往左找到第一个值变小的位置
            int i=cs.length-1;
            while(cs[i]<=cs[i-1]){
                i--;
                if(i==0)//查找不到,没有了
                    return res;
            }
            //向后边查找大于cs[i-1]的最小值
            int j=i;
            int min=j;
            while(j<cs.length){
                if(cs[i-1]<cs[j]&&cs[j]<cs[i])
                    min=j;
                j++;
            }
            CommonUtil.exch(cs,i-1,min);
            CommonUtil.reverse(cs,i,cs.length-1);
        }
    }


    public static void main(String[] args){
        FullPermutation test=new FullPermutation();
        String str="abca";
        test.fullPermutation(str.toCharArray(),0);
        System.out.println(test.res.size());
        System.out.println(test.res);

//        test.fullPermutationFor(str.toCharArray());

        List<String> strings = test.dictPermutation(str.toCharArray());
        System.out.println(strings.toString());

        Set<String> allSet=new HashSet<>();
        allSet.addAll(test.res);
        allSet.addAll(strings);
        System.out.println("allSet size="+allSet.size());

    }

}
