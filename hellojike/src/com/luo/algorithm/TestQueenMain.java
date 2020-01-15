package com.luo.algorithm;


public class TestQueenMain {

    private int rowCount=4;
    private int columnCount=4;
    private int[] data;

    public TestQueenMain(){
        data=new int[rowCount];
    }

    public void recurse(int row){
        if(row==rowCount){
            printArray(data);
            printQueue(data);
            System.out.println();
            return;
        }
        for(int c=0;c<columnCount;c++){
            if(isOk(row,c)){
                data[row]=c;
                recurse(row+1);
            }
        }
    }

    /**
     * 考察第row行在column列放置是否允许
     * @param row
     * @param column
     * @return
     */
    private boolean isOk(int row,int column){
        for(int r=row-1;r>=0;r--){
            if(data[r]==column)
                return false;
            if(Math.abs(column-data[r])==Math.abs(row-r))
                return false;
        }
        return true;
    }

    public void printArray(int[] data){
        System.out.println("print array");
        for(int i=0;i<data.length;i++){
            System.out.print("\t"+data[i]);
        }
        System.out.println();
    }

    public void printQueue(int[] data){
        System.out.println("print data");
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<columnCount;j++){
                if(data[i]==j)
                    System.out.print("\tQ");
                else
                    System.out.print("\t*");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        TestQueenMain test=new TestQueenMain();
        test.recurse(0);
    }
}
