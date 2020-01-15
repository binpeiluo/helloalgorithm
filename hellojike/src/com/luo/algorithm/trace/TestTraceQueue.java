package com.luo.algorithm.trace;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯-N皇后问题
 */
public class TestTraceQueue {
    private List<List<Integer>> res=new ArrayList<>();
    public void traceQueue(int n){
        doQueue(n,0,new Integer[n]);
    }

    private void doQueue(int n,int row,Integer[] queue){
//        判断符合结束条件
        if(row==n){
//            res.add(Arrays.asList(queue));
            printQueue(queue);
            return;
        }

        for(int c=0;c<n;c++){
//            判断是否符合条件
            if(isOk(row,c,queue)){
                queue[row]=c;
                doQueue(n,row+1,queue);
            }
        }

    }

    public void printQueue(Integer[] data){
        System.out.println("print queue");
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data.length;j++){
                if(data[i]==j)
                    System.out.print("\tQ");
                else
                    System.out.print("\t*");
            }
            System.out.println();
        }
    }

    private boolean isOk(int row,int column,Integer[] queue){
        for(int r=row-1;r>=0;r--){
//            判断上方是否符合
            if(queue[r]==column)
                return false;
            if(Math.abs(queue[r]-column)==Math.abs(row-r))
                return false;
        }
        return true;
    }

    public static void main(String[] args){
        TestTraceQueue test=new TestTraceQueue();
        test.traceQueue(4);

    }
}
