package com.luo.util;

import java.util.Random;

public class CommonUtil {

    private static Random r=new Random();

    public static void exch(int[] a,int i,int j){
        int t=a[j];
        a[j]=a[i];
        a[i]=t;
    }
    public static void exch(char[] a,int i,int j){
        char t=a[j];
        a[j]=a[i];
        a[i]=t;
    }

    /**
     * 反转数组中的部分
     * @param a
     * @param lo
     * @param hi
     */
    public static void reverse(char[] a,int lo,int hi){
        if(lo<0||hi>a.length-1)
            throw new IndexOutOfBoundsException("角标越界");
        if(lo==hi)
            return;
        while(lo<hi){
            exch(a,lo++,hi--);
        }
    }

    public static void display(int[] a){
        System.out.println("数组长度:"+a.length);
        for(int n:a){
            System.out.print("\t"+n);
        }
        System.out.println();
    }
    public static void display(char[] a){
        System.out.println("数组长度:"+a.length);
        for(char n:a){
            System.out.print("\t"+n);
        }
        System.out.println();
    }

    public static void display(int[][] a){
        System.out.println("数组长度:"+a.length);
        for(int[] n:a){
            for(int b:n){
                System.out.print("\t"+b);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void display(char[][] cs){
        System.out.println("数组长度:"+cs.length);
        for(char[] n:cs){
            for(char b:n){
                System.out.print("\t"+b);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[] generateArray(int len,int bounds,boolean negetive){
        int[] res=new int[len];
        for(int i=0;i<len;i++){
            int num=r.nextInt(bounds);
            if(negetive&&r.nextFloat()>0.5)
                num=-num;
            res[i]=num;
        }
        return res;
    }

    public static int[] generateArray(int len,int bounds){
        return generateArray(len,bounds,false);
    }

    public static void checkOrder(int[] a){
        checkOrder(a,true);
    }
    public static void checkOrder(int[] a,boolean aesc){
        if(a==null||a.length==1)
            return;
        for(int i=0;i<a.length-1;i++){
            if(aesc&&a[i]>a[i+1])
                assert false;
            else if(!aesc&&a[i]<a[i+1])
                assert false;
        }
    }

    public static void main(String[] args){
        int[] ints = generateArray(10, 5, true);
        display(ints);

        char[] chars = "abcd".toCharArray();
        reverse(chars,0,3);
        display(chars);
    }
}
