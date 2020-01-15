package com.luo.sevendays.day3;

import com.luo.util.CommonUtil;


/**
 * 二分查找
 */
public class BinSearch {
    /**
     * 递归二分查找,查找数值n的元素角标
     * @param s 已经排序的元素
     * @param n 元素值
     * @return  元素值为n的元素所在数组角标.不存在时返回-1
     */
    public int bSearchRecurse(int[] s,int n){
        return bSearchRecurse(s,n,0,s.length-1);
    }

    private int bSearchRecurse(int[] s,int n,int lo ,int hi){
        if(lo>hi)
            return -1;
        int mid=lo+(hi-lo)/2;
        if(s[mid]==n)
            return mid;
        else if(s[mid]>n)
            return bSearchRecurse(s,n,lo,mid-1);
        else
            return bSearchRecurse(s,n,mid+1,hi);
    }

    /**
     * 非递归二分查找,返回值为n的元素所在数组的交表
     * @param s 有序数组
     * @param n 数值为n
     * @return  返回数值为n所在数组的角标
     */
    public int bSearch(int[] s,int n){
        int lo=0,hi=s.length-1;
        while(true){
            if(lo>hi)
                return -1;
            int mid=lo+(hi-lo)/2;
            if(s[mid]>n)
                hi=mid-1;
            else if(s[mid]<n)
                lo=mid+1;
            else
                return mid;
        }
    }

    /**
     * 非递归二分查找 第一个数组等于n的元素所在角标
     * @param s 有序数组
     * @param n 数组为n的元素
     * @return  第一个数值为n的元素所在的角标
     */
    public int bSearchFirst(int[] s,int n){
        if(s==null||s.length==0)
            return -1;
        int lo=0,hi=s.length-1;
        while(true){
            if(lo>hi)
                return -1;
            int mid=lo+(hi-lo)/2;
            if(s[mid]>n)
                hi=mid-1;
            else if(s[mid]<n)
                lo=mid+1;
            else
                if(mid==0||s[mid-1]!=n)
                    return mid;
                else
                    hi=mid-1;
        }
    }

    /**
     * 非递归二分查找  最后一个数值等于n的元素角标
     * @param s 有序数组
     * @param n 数值为n的元素
     * @return  最后一个数组数值扥与n的元素角标
     */
    public int bSearchLast(int[] s,int n){
        if(s==null||s.length==0)
            return -1;
        int lo=0,hi=s.length-1;
        while(true){
            if(lo>hi)
                return -1;
            int mid=lo+(hi-lo)/2;
            if(s[mid]>n)
                hi=mid-1;
            else if(s[mid]<n)
                lo=mid+1;
            else
                if(mid==s.length-1||s[mid+1]!=n)
                    return mid;
                else
                    lo=mid+1;
        }
    }

    /**
     * 非递归二分查找,返回最后一个小于等于n的元素的角标
     * @param s 有序数组
     * @param n 数值等于n的元素
     * @return  返回最后一个小于等于n的元素的角标
     */
    public int bSearchCeil(int[] s,int n){
        if(s==null||s.length==0)
            return -1;
        int lo=0,hi=s.length-1;
        while(lo<=hi){
            int mid=lo+(hi-lo)/2;
            if(s[mid]>n)
                hi=mid-1;
            else
                if(mid==s.length-1||s[mid+1]>n)
                    return mid;
                else
                    lo=mid+1;
        }
        return -1;
    }

    /**
     * 二分查找 第一个大于等于n的元素角标
     * @param s 有序数组
     * @param n 数值为n的元素
     * @return  第一个大于等于n的元素的角标
     */
    public int bSearchFloor(int[] s,int n){
        if(s==null||s.length==0)
            return -1;
        int lo=0,hi=s.length-1;
        while(lo<=hi){
            int mid=lo+((hi-lo)>>1);
            if(s[mid]<n)
                lo=mid+1;
            else
                if(mid==0||s[mid-1]<n)
                    return mid;
                else
                    hi=mid-1;
        }
        return -1;
    }


    public static void main(String[] args){
        BinSearch search=new BinSearch();
//        int[] ints = CommonUtil.generateArray(10, 20, true);
        int[] ints=new int[]{-11,-11,-6,-6,-1,0,4,9,9,13};
        SortQuick quick=new SortQuick();
        quick.quickSort(ints);
        CommonUtil.display(ints);
        int n=12;
        System.out.println("starting ... find the element where value is "+ n);
        int i = search.bSearchRecurse(ints, n);
        System.out.println("bSearchRecurse the index is "+i);

        int j = search.bSearch(ints, n);
        System.out.println("bSearch the index is "+j);

        int firstIndex = search.bSearchFirst(ints, n);
        System.out.println("bSearchFirst the index is "+firstIndex);

        int lastIndex = search.bSearchLast(ints, n);
        System.out.println("lastIndex the index is "+lastIndex);

        int ceil = search.bSearchCeil(ints, n);
        System.out.println("bSearchCeil the index is "+ceil);

        int floor = search.bSearchFloor(ints, n);
        System.out.println("bSearchFloor the index is "+floor);

    }

}
