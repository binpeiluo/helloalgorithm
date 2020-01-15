package com.luo.sevendays.day1.array;

/**
 * 固定大小的数组,实现动态增删改查功能
 * @param <E>
 */
public class FixedList<E extends Comparable> {
    private static final int DEFAULT_CAPACITY=10;
    private Object[] data;
    private int capacity;
    private int size;
    public FixedList(){
        capacity=DEFAULT_CAPACITY;
        data=new Object[capacity];
    }
    public FixedList(int capacity){
        this.capacity=capacity;
        data=new Object[capacity];
    }
    //增
    public boolean insert(E ele){
        if(size==capacity)
            return false;
        int index = find(ele);
        System.arraycopy(data,index,data,index+1,size-index);
        data[index]=ele;
        size++;
        return true;
    }
    //查
    public E get(int index){
        checkIndex(index);
        return (E) data[index];
    }
    //删
    public boolean remove(E ele){
        int index=find(ele);
        E e = get(index);
        if(e.equals(ele)){
            return remove(index);
        }
        return false;
    }
    //删
    public boolean remove(int index){
        checkIndex(index);
        System.arraycopy(data,index+1,data,index,size-index-1);
        size--;
        return true;
    }
    //改
    public boolean replace(E srcEle,E destEle){
        if(remove(srcEle)){
            insert(destEle);
            return true;
        }
        return false;
    }

    private int find(E key){
        if(size==0)
            return size;
        int lo=0,hi=size-1,mid;
        while(lo<hi){
            mid=(lo+hi)/2;
            int compare=key.compareTo(this.get(mid));
            if(compare<0){
                hi=mid-1;
            }else if(compare>0){
                lo=mid+1;
            }else{
                return mid;
            }
        }
        if(lo==hi)
            return key.compareTo(this.get(lo))>0?lo+1:lo;
        else
            return lo;
    }
    private void checkIndex(int index){
        if(index<0||index>capacity)
            throw new IndexOutOfBoundsException("角标越界");
    }
    public void disPlay() {
        for(int j = 0; j < size; j ++) {
            System.out.print(data[j] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args){
        FixedList<Integer> list=new FixedList<>();
        for(int i=5;i>0;i--){
            list.insert(2*i);
        }
        list.disPlay();
        list.insert(5);
        list.insert(0);
        list.insert(12);
        list.disPlay();
        list.remove(0);
        list.remove(6);
        list.remove(Integer.valueOf(2));
        list.disPlay();
        list.replace(Integer.valueOf(7),Integer.valueOf(9));
        list.replace(Integer.valueOf(5),Integer.valueOf(9));
        list.disPlay();
    }
}