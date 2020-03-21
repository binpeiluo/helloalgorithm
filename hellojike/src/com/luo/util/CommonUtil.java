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

    public static int min(int a,int b,int c){
        int min=Math.min(a,b);
        min=Math.min(min,c);
        return min;
    }
    public static int max(int a,int b,int c){
        int max=Math.max(a,b);
        max=Math.max(max,c);
        return max;
    }

    public static class TreeNode{
        public TreeNode left;
        public TreeNode right;
        public int val;
        TreeNode(int val,TreeNode left,TreeNode right){
            this.val=val;
            this.left=left;
            this.right=right;
        }
        TreeNode(int val){
            this.val=val;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    /**
     * 生成二叉树 方便计算.忽略第一个节点
     * @param valuas
     * @return
     */
    public static TreeNode generateNodeMissing(Integer[] valuas){
        int len=valuas.length;
        TreeNode[] nodes=new TreeNode[len];
        for (int i = 0; i < len; i++) {
            if(valuas[i]!=null)
                nodes[i]=new TreeNode(valuas[i]);
        }
        for (int i = 1; i < len / 2; i++) {
            if(nodes[i]!=null){
                nodes[i].left=nodes[2*i];
                nodes[i].right=nodes[2*i+1];
            }
        }
        return nodes[1];
    }

    /**
     * 生成二叉树节点,不忽略首位置
     * @param valuas
     * @return
     */
    public static TreeNode generateNode(Integer[] valuas){
        Integer[] after=new Integer[valuas.length+1];
        for (int i = 1; i < after.length; i++) {
            after[i]=valuas[i-1];
        }
        return generateNodeMissing(after);
    }


    public static void main(String[] args){
        int[] ints = generateArray(10, 5, true);
        display(ints);

        char[] chars = "abcd".toCharArray();
        reverse(chars,0,3);
        display(chars);
    }
}
