package com.luo.labuladong.highfrequence;

import java.util.Arrays;

/**
 * union-find应用2
 * 990. 等式方程的可满足性
 */
public class TestUnionFindApp2 {
    public boolean equationsPossible(String[] equations) {
        int len=equations.length;
        if(len<=0)
            return true;
        Arrays.sort(equations,(e1,e2)->{return e2.charAt(1)-e1.charAt(1);});
        TestUnionFind.UF uf=new TestUnionFind.UF('z'-'a'+1);
        for (int i = 0; i < len; i++) {
            int start=equations[i].charAt(0)-'a';
            int end = equations[i].charAt(3)-'a';
            char s = equations[i].charAt(1);
            if(s=='='){
                uf.union(start,end);
            }else{
                if(uf.connected(start,end))
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        TestUnionFindApp2 test=new TestUnionFindApp2();
        String[] equations={"a==b","b!=a"};
        boolean b = test.equationsPossible(equations);
        System.out.println(b);
    }
}
