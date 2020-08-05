package com.luo.labuladong.dataconstruct;

import java.util.*;

/**
 * LFU
 */
@SuppressWarnings("Duplicates")
public class TestLfuCache {

    /**
     * navie想法,使用三个map分别保存 k-v,k-f,f-ks之间的关系,
     * 并且记录 minFreq.
     * 这里 f-ks 映射使用 TreeMap<Integer,<LinkedHashSet>>
     *  使用TreeMap是为了将key有序组织起来,方便找到最小的minFreq.
     *  由于使用红黑树,这里时间复杂度为 O(logn),其实不太符合题目要求O(1)
     *
     *  都使用了 minFreq保存最小访问次数,那这里还有需要使用 TreeMap来有序地组织key吗?
     *  其实duck不必.仔细思考,当插入新纪录后,minFreq必定为1.
     *  只有当需要插入记录才会删除记录.所以只需要保证在删除记录的时候 minFreq是正确的即可.
     *  删除记录前的操作,有可能为获取了记录,此时更新逻辑一定正确
     */
    static class LfuCache{

//        key->value
        Map<Integer,Integer> cache=new HashMap<>();
//        key->k2f
        Map<Integer,Integer> visited=new HashMap<>();
//        k2f->key list
//        LinkedHashMap 能通过访问次序调整位置
//        TreeMap 数据结构为红黑树,可以将key按照大小顺序组织,但获取key的复杂度为O(logn)级别
        Map<Integer, LinkedHashSet<Integer>> visitedList=new TreeMap<>();
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
//                防止 capacity=0
                if(cache.size()<capacity){
                    addNewKv(key,value);
                }
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
            if(visitedList.get(minVisitedCnt).isEmpty()){
                visitedList.remove(minVisitedCnt);
            }
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
            if(visitedList.get(minVisitedCnt).isEmpty()){
                visitedList.remove(minVisitedCnt);
            }
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
            visitedList.put(1,orDefault);
//            更新最小访问次数
            updateMinVisitedCnt();
        }

//        更新最小访问次数变量
        private void updateMinVisitedCnt(){
            Set<Integer> integers = visitedList.keySet();
            if(integers.isEmpty()){
                minVisitedCnt=Integer.MIN_VALUE;
            }else{
                minVisitedCnt=integers.iterator().next();
            }
        }
    }
    static class LfuCacheV2{

        //        key->value
        Map<Integer,Integer> k2v ;
        //        key->freq
        Map<Integer,Integer> k2f ;
        //        freq->keys
        Map<Integer, LinkedHashSet<Integer>> f2ks ;
        //        最小访问次数
        int minFreq =Integer.MIN_VALUE;
        int capacity;

        LfuCacheV2(int capacity){
            this.capacity=capacity;
            k2v =new HashMap<>();
            k2f =new HashMap<>();
            f2ks =new HashMap<>();
        }

        public int get(int key){
            if(k2v.containsKey(key)){
                visitKey(key);
                return k2v.get(key);
            }else{
                return -1;
            }
        }

        public void put(int key,int value){
            if(k2v.containsKey(key)){
                visitKey(key);
                k2v.put(key,value);
            }else{
                if(k2v.size()==capacity){
                    removeLastVersion();
                }
//                防止 capacity=0
                if(k2v.size()<capacity){
                    addNewKv(key,value);
                }
            }
        }

        //        删除访问次数最小的元素
        private void removeLastVersion(){
            boolean canRemove= minFreq !=Integer.MIN_VALUE;
            if(!canRemove){
                return;
            }
//            找到最小访问次数对应的key列表中的首个
            int key= f2ks.get(minFreq).iterator().next();
//            将该最小访问次数对应的key列表删除该key
            f2ks.get(minFreq).remove(key);
            if(f2ks.get(minFreq).isEmpty()){
                f2ks.remove(minFreq);
            }
//            在缓存中删除key
            k2v.remove(key);
//            在key->访问次数map中删除key
            k2f.remove(key);
//            更新最小访问次数
//            不更新起始无所谓,因为接下来插入新元素肯定会将最小频次最为1
        }

        private void visitKey(int key){
//            获取该key对应的访问次数
            int currVisitedCnt= k2f.get(key);
//            计算新的访问次数
            int newVisitedCnt=currVisitedCnt+1;
//            在key->访问次数的map更新
            k2f.put(key,newVisitedCnt);
//            在访问次数->key列表中删除该key,因为其对应的访问次数已经更新了
            f2ks.get(currVisitedCnt).remove(key);
            if(f2ks.get(minFreq).isEmpty()){
                f2ks.remove(minFreq);
//            更新最小访问次数
                minFreq=newVisitedCnt;
            }
//            更新key对应的访问次数
            LinkedHashSet<Integer> orDefault = f2ks.getOrDefault(newVisitedCnt, new LinkedHashSet<>());
            orDefault.add(key);
            f2ks.put(newVisitedCnt,orDefault);
        }

        private void addNewKv(int key,int value){
            k2v.put(key,value);
            k2f.put(key,1);
            LinkedHashSet<Integer> orDefault = f2ks.getOrDefault(1, new LinkedHashSet<>());
            orDefault.add(key);
            f2ks.put(1,orDefault);
//            更新最小访问次数
//            此时最小频率一定为1
            minFreq=1;
        }

    }


    public static void main(String[] args){
//        LfuCache lfuCache=new LfuCache(2);
//        lfuCache.put(1,1);
//        lfuCache.put(2,2);
//        int i = lfuCache.get(1);
//        System.out.println(i);
//        lfuCache.put(3,3);
//        i = lfuCache.get(2);
//        System.out.println(i);
//        i = lfuCache.get(1);
//        System.out.println(i);

//        [[3],[2,2],[1,1],[2],[1],[2],[3,3],[4,4],[3],[2],[1],[4]]
//        LfuCache lfuCache=new LfuCache(3);
//        lfuCache.put(2,2);
//        lfuCache.put(1,1);
//        int i = lfuCache.get(2);
//        System.out.println(i);
//        i = lfuCache.get(1);
//        System.out.println(i);
//        i = lfuCache.get(2);
//        System.out.println(i);
//        lfuCache.put(3,3);
//        lfuCache.put(4,4);
////        开始不对劲
//        i = lfuCache.get(3);
//        System.out.println(i);
//        i = lfuCache.get(2);
//        System.out.println(i);
//        i = lfuCache.get(1);
//        System.out.println(i);
//        i = lfuCache.get(4);
//        System.out.println(i);

//        [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
//        LfuCache lfuCache=new LfuCache(2);
//        lfuCache.put(1,1);
//        lfuCache.put(2,2);
//        int i = lfuCache.get(1);
//        System.out.println(i);
//        lfuCache.put(3,3);
//        i = lfuCache.get(2);
//        System.out.println(i);
//        i = lfuCache.get(3);
//        System.out.println(i);
//        lfuCache.put(4,4);
//        i = lfuCache.get(1);
//        System.out.println(i);
//        i = lfuCache.get(3);
//        System.out.println(i);
//        i = lfuCache.get(4);
//        System.out.println(i);

//        [[0],[0,0],[0]]
//        LfuCache lfuCache=new LfuCache(0);
//        lfuCache.put(0,0);
//        int i = lfuCache.get(0);
//        System.out.println(i);

//        [[1],[2,1],[2],[3,2],[2],[3]]
        LfuCacheV2 lfuCache=new LfuCacheV2(1);
        lfuCache.put(2,1);
        int i = lfuCache.get(2);
        System.out.println(i);
        lfuCache.put(3,2);
        i = lfuCache.get(2);
        System.out.println(i);
        i = lfuCache.get(3);
        System.out.println(i);
    }
}
