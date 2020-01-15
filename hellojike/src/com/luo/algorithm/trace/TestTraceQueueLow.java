package com.luo.algorithm.trace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 使用二维数组储存棋盘
 */
public class TestTraceQueueLow {
    List<List<String>> queue;

    private static final String SPACE="\t*";
    private static final String FULL="\tQ";

    public void traceQueueLow(int n){
        queue= new ArrayList<>();
        for(int r=0;r<n;r++){
            List<String> row=new ArrayList<>();
            for(int c=0;c<n;c++){
                row.add(SPACE);
            }
            queue.add(row);
        }
        doQueue(0);
    }

    private void doQueue(int row){
//        判断结束条件
        if(row==queue.size()){
            System.out.println("print queue");
            System.out.println(queue);
            return;
        }
        for(int c=0;c<queue.size();c++){
//            判断符合条件
            if(isOk(row,c,queue)){
                //做选择
                queue.get(row).remove(c);
                queue.get(row).add(c,FULL);
                //递归
                doQueue(row+1);
                //撤销选择
                queue.get(row).remove(c);
                queue.get(row).add(c,SPACE);

            }
        }
    }

    private boolean isOk(int row,int column,List<List<String>> queue){
        for(int r=row-1;r>=0;r--){
            if(queue.get(r).get(column).equals(FULL))
                return false;
            for(int c=0;c<queue.size();c++){
                if(queue.get(r).get(c).equals(FULL)){
                    if(Math.abs(column-c)==Math.abs(row-r))
                        return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        TestTraceQueueLow test=new TestTraceQueueLow();
        test.traceQueueLow(4);
    }
}
