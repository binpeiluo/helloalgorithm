package com.luo.algorithm;

import com.luo.util.CommonUtil;


/**
 * 使用堆构建造出top n个元素
 */
public class TestHeapTopN<E>{
    private int[] top;
    private int size;
    private int capacity;
    TestHeapTopN(int capacity){
        this.capacity=capacity;
        top=new int[capacity+1];
    }

    public boolean insert(int ele){
        //未满,添加在最后然后headify
        if(size<capacity){
            top[++size]=ele;
            heapifyUp(top,size);
            return true;
        }else if(ele>max()){//满了,判断是否大于堆顶元素,然后堆化
            top[1]=ele;
            heapifyDown(top,capacity,1);
            return true;
        }else{
            return false;
        }
    }


    private int max(){
        return top[1];
    }

    private void heapifyDown(int[] a,int n,int i){
        while(true){
            if(2*i>n)
                return;
            int minP=i;
            if(2*i<=n&&a[minP]>a[2*i])
                minP=2*i;
            if(2*i+1<=n&&a[minP]>a[2*i+1])
                minP=2*i+1;
            if(minP==i)
                return;
            CommonUtil.exch(a,minP,i);
            i=minP;
        }
    }

    private void heapifyUp(int[] a,int i){
        while(true){
            if(i/2<1)
                return;
            int minP=i/2;
            if(a[minP]>a[i])
                minP=i;
            if(minP==i/2)
                return;
            CommonUtil.exch(a,minP,i/2);
            i=minP;
        }
    }

    public void display(){
        CommonUtil.display(top);
    }

    public static void main(String[] args){
        TestHeapTopN topN=new TestHeapTopN(3);
        int[] ints = CommonUtil.generateArray(10, 20, true);
        CommonUtil.display(ints);
        for(int i:ints){
            boolean insert = topN.insert(i);
            System.out.println("top N heap insert :"+insert);
        }
        topN.display();
    }
}
