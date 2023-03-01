package com.luo.sevendays.day1.array;

/**
 * 动态扩容数组
 * @param <E>
 */
public class DynamicList<E> {
    //当前List的元素个数
    private int size;
    //默认容量
    private static final int DEFAULT_INIT_CAPACITY = 10;
    //数组
    private Object[] elementData;

    //默认构造器
    public DynamicList() {
        elementData = new Object[DEFAULT_INIT_CAPACITY];
    }
    //可指定容量的构造器
    public DynamicList(int initCapacity) {
        elementData = new Object[initCapacity];
    }

    //添加元素
    public void add(E e) {
        //添加元素前判断当前元素个数和数组长度是否相同，相同则需要扩容
        if (size == elementData.length) {
            Object[] dest = new Object[elementData.length * 3 / 2];
            System.arraycopy(elementData, 0, dest, 0, elementData.length);
            elementData = dest;
        }
        elementData[size++] = e;
    }

    //移除元素
    public void remove(int index) {
        System.arraycopy(elementData, index + 1, elementData, index, elementData.length - index - 1);
        elementData[--size] = null;
    }

    //修改元素
    public void modify(int index, E e) {
        //检查下标是否超出范围
        rangeCheck(index);
        elementData[index] = e;
    }

    //查找元素
    public E get(int index) {
        //检查下标是否超出范围
        rangeCheck(index);
        return (E) elementData[index];
    }

    //范围检查
    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("下标越界,index：" + index);
        }
    }

    //获取当前存储元素个数
    public int size() {
        return size;
    }

    //打印（用于测试）
    public void print() {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            if (elementData[i] != null) {
                if (i != size - 1) {
                    System.out.print(elementData[i] + ", ");
                } else {
                    System.out.print(elementData[i]);
                }
            }
        }
        System.out.println("]");
    }
}
