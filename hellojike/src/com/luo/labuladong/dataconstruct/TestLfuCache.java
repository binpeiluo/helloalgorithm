package com.luo.labuladong.dataconstruct;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class TestLfuCache {

    static class LfuCache{

//        key->value
        Map<Integer,Integer> cache=new HashMap<>();
//        key->visited
        Map<Integer,Integer> visited=new HashMap<>();
//        visited->key list
        Map<Integer, LinkedHashSet<Integer>> visitedList=new LinkedHashMap<>();
//        最小访问次数
        int minVisitedCnt =Integer.MIN_VALUE;
        int capacity;

        LfuCache(int capacity){
            this.capacity=capacity;
        }

        public int get(int key){
            if(cache.containsKey(key)){
                visitKey(key);
                return cache.get(key);
            }else{
                return -1;
            }
        }

        public void put(int key,int value){
            if(cache.containsKey(key)){
                visitKey(key);
                cache.put(key,value);
            }else{
                if(cache.size()==capacity){
                    removeLastVersion();
                }
                addNewKv(key,value);
            }
        }

//        删除访问次数最小的元素
        private void removeLastVersion(){
            boolean canRemove= minVisitedCnt !=Integer.MIN_VALUE;
            if(!canRemove){
                return;
            }
//            找到最小访问次数对应的key列表中的首个
            int key=visitedList.get(minVisitedCnt).iterator().next();
//            将该最小访问次数对应的key列表删除该key
            visitedList.get(minVisitedCnt).remove(key);
//            在缓存中删除key
            cache.remove(key);
//            在key->访问次数map中删除key
            visited.remove(key);
//            更新最小访问次数
            updateMinVisitedCnt();
        }

        private void visitKey(int key){
//            获取该key对应的访问次数
            int currVisitedCnt=visited.get(key);
//            计算新的访问次数
            int newVisitedCnt=currVisitedCnt+1;
//            在key->访问次数的map更新
            visited.put(key,newVisitedCnt);
//            在访问次数->key列表中删除该key,因为其对应的访问次数已经更新了
            visitedList.get(currVisitedCnt).remove(key);
//            更新key对应的访问次数
            LinkedHashSet<Integer> orDefault = visitedList.getOrDefault(newVisitedCnt, new LinkedHashSet<>());
            orDefault.add(key);
            visitedList.put(newVisitedCnt,orDefault);
//            更新最小访问次数
            updateMinVisitedCnt();
        }

        private void addNewKv(int key,int value){
            cache.put(key,value);
            visited.put(key,1);
            LinkedHashSet<Integer> orDefault = visitedList.getOrDefault(1, new LinkedHashSet<>());
            orDefault.add(key);
            visitedList.put(key,orDefault);
//            更新最小访问次数
            updateMinVisitedCnt();
        }

//        更新最小访问次数变量
        private void updateMinVisitedCnt(){
            Integer next=visitedList.keySet().iterator().next();
            if(next==null){
                minVisitedCnt=Integer.MIN_VALUE;
            }else{
                minVisitedCnt=next;
            }
        }
    }

    public static void main(String[] args){
        LfuCache lfuCache=new LfuCache(2);
        lfuCache.put(1,1);
        lfuCache.put(2,2);
        int i = lfuCache.get(1);
        System.out.println(i);
        lfuCache.put(3,3);
        i = lfuCache.get(2);
        System.out.println(i);
        i = lfuCache.get(1);
        System.out.println(i);
    }
}
