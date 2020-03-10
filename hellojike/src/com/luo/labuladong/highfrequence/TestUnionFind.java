package com.luo.labuladong.highfrequence;

/**
 * 高频题目
 *      算法4,连通性问题
 */
public class TestUnionFind {

    static class UF {

        /*联通量*/
        int count;
        /*节点个数*/
        int len;
        /*节点父节点,使用树的结构表示图*/
        int[] parent;
        /*节点的高度问题,用来优化union导致树不平衡问题*/
        int[] height;

        UF(int n){
            this.count=n;
            this.len=n;
            parent=new int[n];
//            初始是指向自己,表示单独的联通量
            for (int i = 0; i < n; i++) {
                parent[i]=i;
            }
            height=new int[n];
            for (int i = 0; i < n; i++) {
                height[i]=1;
            }
        }

        /* 将 p 和 q 连接 */
        public void union(int p, int q){
            int rootP = root(p);
            int rootQ = root(q);
//            假如已经联通了,那没必要关联了
            if(rootP==rootQ)
                return;
//            优化树的不平衡性问题,本来这里的时间复杂度最高为O(n),优化后可为O(logn)
            if(height[rootP]<height[rootQ]){
                parent[rootP]=rootQ;
                height[rootQ]+=height[rootP];
            }else{
                parent[rootQ]=rootP;
                height[rootP]+=height[rootQ];
            }
            count--;
        }
        /* 判断 p 和 q 是否连通 */
        public boolean connected(int p, int q){
            int rootP = root(p);
            int rootQ = root(q);
            return rootP==rootQ;
        }
        /* 返回图中有多少个连通分量 */
        public int count(){
            return this.count;
        }

        /*寻找p节点的根节点,这里可以进一步优化,可以压缩树的高度,使其最大高度为3*/
        private int root(int p){
            while(parent[p]!=p){
                parent[p]=parent[parent[p]];
                p=parent[p];
            }
            return p;
        }
    }

    public static void main(String[] args){
        UF uf=new UF(10);
        testPrintCount(uf);
        uf.union(0,1);
        testPrintCount(uf);
        uf.union(1,2);
        testPrintCount(uf);
        uf.union(2,3);
        testPrintCount(uf);

        uf.union(4,5);
        testPrintCount(uf);
        uf.union(5,6);
        testPrintCount(uf);
        uf.union(6,7);
        testPrintCount(uf);

        testPrint(uf);

    }

    private static void testPrint(UF uf){
        for (int i = 0; i < uf.len; i++) {
            for (int j = 0; j < uf.len; j++) {
                System.out.printf("uf.connected(%d,%d)=%b",i,j,uf.connected(i,j));
                System.out.println();
            }
        }
    }

    private static void testPrintCount(UF uf){
        System.out.printf("联通量:%d",uf.count);
        System.out.println();
    }
}
