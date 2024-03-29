package com.luo.util;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
    public static void display(boolean[][] a){
        System.out.println("数组长度:"+a.length);
        for(boolean[] n:a){
            for(boolean b:n){
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
        public TreeNode(int val,TreeNode left,TreeNode right){
            this.val=val;
            this.left=left;
            this.right=right;
        }
        public TreeNode(int val){
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
        for (int i = 1; i <=(len-1) / 2; i++) {
            if(nodes[i]!=null){
                nodes[i].left=nodes[2*i];
                nodes[i].right=(2*i+1==len)?null:nodes[2*i+1];
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

    public static class ListNode{
        public int val;
        public ListNode next;
        public ListNode(int val){
            this.val=val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static ListNode generateListNode(int[] nums){
        int len=nums.length;
        if(len==1)
            return new ListNode(nums[0]);
        ListNode[] list=new ListNode[len];
        for(int i=0;i<len;i++){
            list[i]=new ListNode(nums[i]);
        }
        for (int i = 0; i < len - 1; i++) {
            list[i].next=list[i+1];
        }
        return list[0];
    }

    /**
     *
     * @param nums
     * @param cycleStart
     * @param cycleNext
     * @return
     */
    public static ListNode[] generateListNodeCycle(int[] nums,int cycleStart,int cycleNext){
        int len=nums.length;
        ListNode[] list=new ListNode[len];
        for(int i=0;i<len;i++){
            list[i]=new ListNode(nums[i]);
        }
        for (int i = 0; i < len - 1; i++) {
            list[i].next=list[i+1];
        }
        list[cycleStart].next=list[cycleNext];
        return list;
    }

    public static void printListNode(ListNode node){
        System.out.println("链表:");
        while(node!=null){
            System.out.print("\t-->"+node.val);
            node=node.next;
        }
        System.out.println();
    }

    public static void printListNodeCycle(ListNode node){
        System.out.println("环形链表:");
        if (node==null){
            return;
        }
        ListNode t=node;
        Set<ListNode> set=new HashSet<>();
        while(t!=null){
            if (set.contains(t)){
                break;
            }
            set.add(t);
            System.out.print("\t-->"+t.val);
            t=t.next;
        }
        System.out.print("\t-->(back to the exists ele)"+t.val);
        System.out.println();
    }

    public static void printTreeNode(TreeNode root){
        System.out.println("二叉树:");
        System.out.println(root.toString());
    }

    public static void main(String[] args){
        int[] ints = generateArray(10, 5, true);
        display(ints);

        char[] chars = "abcd".toCharArray();
        reverse(chars,0,3);
        display(chars);
    }
}
