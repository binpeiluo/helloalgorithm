package com.luo.leetcode.bfs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 341. 扁平化嵌套列表迭代器
 * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 *
 * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
 */
public class No341_NestedIterator {

    /**
     * 这种实现方式为一股脑将值全部获取出来
     */
    class NestedIterator implements Iterator<Integer>{

        private Iterator<Integer> it;

        public NestedIterator(List<NestedInteger> nestedList) {
            List<Integer> list=new ArrayList<>();
            for(NestedInteger item:nestedList){
                traverse(item,list);
            }
            it=list.iterator();
        }

        @Override
        public Integer next() {
            return it.next();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        private void traverse(NestedInteger item,List<Integer> list){
            if(item.isInteger()){
                list.add(item.getInteger());
            }else{
                for (NestedInteger n:item.getList()){
                    traverse(n,list);
                }
            }
        }
    }

    /**
     * 更优雅的方式
     */
    class NestedIterator2 implements Iterator<Integer>{

        private List<NestedInteger> list;

        public NestedIterator2(List<NestedInteger> nestedList) {
            list=new LinkedList<>(nestedList);
        }

        @Override
        public Integer next() {
            return list.remove(0).getInteger();
        }

        /**
         * 每次调用next之前都会调用hasNest,在这里循环将list首元素的数组形式铺平
         * 这样子就能确定下次调用next时数据结构的组成
         * @return
         */
        @Override
        public boolean hasNext() {
            while(!list.isEmpty()&&!list.get(0).isInteger()){
                List<NestedInteger> items = list.remove(0).getList();
                for (int i=items.size()-1;i>=0;i--){
                    list.add(0,items.get(i));
                }
            }
            return !list.isEmpty();
        }

    }


    class NestedInteger{
        Integer value;
        List<NestedInteger> list;
        NestedInteger(Integer value){
            this.value=value;
        }
        NestedInteger(List<NestedInteger> list){
            this.list=list;
        }

        public Integer getInteger() {
            return value;
        }

        public List<NestedInteger> getList() {
            return list;
        }
        public boolean isInteger(){
            return value!=null;
        }
    }
}
