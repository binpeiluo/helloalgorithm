package com.luo.algorithm.string;

/**
 * 实现 boyer-moore 字符匹配算法
 */
public class TestBoyerMoore {
    private static final int size=256;

    private void generateBC(char[] b, int n, int[] bc) {
        for(int i=0;i<size;i++){
            bc[i]=-1;
        }
        for(int i=0;i<n;i++){
            bc[(int)b[i]]=i;
        }
    }
    private void generateGS(char[] b, int n, int[] suffix, boolean[] prefix) {
        for(int i=0;i<n;i++){
            suffix[i]=-1;
            prefix[i]=false;
        }
        for(int i=0;i<n-1;i++){
            int k=0;
            int j=i;
            while(j>=0&&b[j]==b[n-1-k]){
                j--;
                k++;
                suffix[k]=j+1;
            }
            if(j==-1)
                prefix[k]=true;
        }
    }

    public int bm(char[] a,int m,char[] b ,int n){
//        处理坏字符位置
        int[] bc=new int[size];
        generateBC(b,n,bc);
//        好后缀起始位置数组 index为好后缀长度,value为匹配的好后缀的起始位置
        int[] suffix=new int[n];
//        但有好后缀数组不不够,需要判断好后缀的后缀子串是否在前缀中匹配
        boolean[] prefix=new boolean[n];
        generateGS(b,n,suffix,prefix);

        int i=0;
        while(i<=m-n){
            int j=n-1;

            while(j>=0&&b[j]==a[i+j]){
                j--;
            }
            if(j==-1)
                return i;
//            坏字符所在
            int x=j-bc[(int)a[i+j]];
            int y=0;
//            判断是否有好后缀以及计算
            if(j<n-1){
//                计算长度
                int k=n-j-1;
                if(suffix[k]!=-1){//在前缀中能找到好后缀字符
                    y=j+1-suffix[k];
                }else{//
                    y=n;
                    for(int r=j+2;r<n;r++){//尝试在prefix判断是否在前缀中存在好后缀的后缀子串
                        if(prefix[n-r]){
                            y=r;
                            break;
                        }
                    }
                }
            }
            i=i+Math.max(x,y);
        }
        return -1;
    }

    public static void main(String[] args){
        TestBoyerMoore test=new TestBoyerMoore();
        char[] a="Gennadiy GennadyeGenn Golovkin often known by his nickname".toCharArray();
        char[] b="ten known by ".toCharArray();
        int index = test.bm(a, a.length, b, b.length);
        System.out.println(index);
        System.out.println(String.valueOf(a,index,b.length).equals(String.valueOf(b)));

    }


}
