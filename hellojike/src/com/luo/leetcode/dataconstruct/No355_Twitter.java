package com.luo.leetcode.dataconstruct;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 使用系统的时间戳是不可行的,有可能在一瞬间有多条推特生成
 * 可以考虑全局序号id
 */
public class No355_Twitter {
    static class Twitter {

        class Message{
            Integer id;
            long timestamp;

            public Message(Integer id) {
                this.id = id;
                this.timestamp=currNo++;
            }

            public long getTimestamp() {
                return timestamp;
            }

            public Integer getId() {
                return id;
            }
        }

        Map<Integer,LinkedList<Message>> userPosts=new HashMap<>();
        Map<Integer,HashSet<Integer>> userFollows=new HashMap<>();
//        全局有序序号,为实现排序功能
        static volatile long currNo=0;

        /** Initialize your data structure here. */
        public Twitter() {

        }

        /** Compose a new tweet. */
        public void postTweet(int userId, int tweetId) {
            if(!userFollows.containsKey(userId)){
                HashSet<Integer> f=new HashSet<>();
                f.add(userId);
                userFollows.put(userId,f);
            }
            if(userPosts.containsKey(userId)){
                userPosts.get(userId).add(0,new Message(tweetId));
            }else{
                LinkedList<Message> posts=new LinkedList<>();
                posts.add(new Message(tweetId));
                userPosts.put(userId,posts);
            }
        }

        /** Retrieve the 10 most recent tweet ids in the user's news feed.
         * Each item in the news feed must be posted by users
         *  who the user followed or by the user herself.
         * Tweets must be ordered from most recent to least recent. */
        public List<Integer> getNewsFeed(int userId) {
            if(userFollows.containsKey(userId)){
                return userFollows.get(userId).stream()
                        .filter(follower->userPosts.containsKey(follower))
                        .map(follower->userPosts.get(follower))
                        .flatMap(list->list.stream())
                        .sorted((m1,m2)->(int)(m2.getTimestamp()-m1.getTimestamp()))
                        .map(Message::getId)
                        .limit(10)
                        .collect(Collectors.toList());
            }else
                return new ArrayList<>();
        }

        /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
        public void follow(int followerId, int followeeId) {
            if(userFollows.containsKey(followerId)){
                if(userFollows.get(followerId).contains(followeeId))
                    return;
                else
                    userFollows.get(followerId).add(followeeId);
            }else{
                HashSet<Integer> f=new HashSet<>();
                f.add(followerId);
                f.add(followeeId);
                userFollows.put(followerId,f);
            }
        }

        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        public void unfollow(int followerId, int followeeId) {
            if(followeeId!=followerId && userFollows.containsKey(followerId))
                userFollows.get(followerId).remove((Object)followeeId);
        }
    }

    public static void main(String[] args){
//        Twitter twitter=new Twitter();
//        twitter.postTweet(1,5);
//        List<Integer> newsFeed = twitter.getNewsFeed(1);
//        System.out.println(newsFeed);
//        twitter.follow(1,2);
//        twitter.postTweet(2,6);
//        List<Integer> newsFeed1 = twitter.getNewsFeed(1);
//        System.out.println(newsFeed1);
//        twitter.unfollow(1,2);
//        List<Integer> newsFeed2 = twitter.getNewsFeed(1);
//        System.out.println(newsFeed2);

//        Twitter twitter=new Twitter();
//        twitter.postTweet(1,1);
//        twitter.postTweet(1,2);
//        twitter.postTweet(1,3);
//        List<Integer> newsFeed = twitter.getNewsFeed(1);
//        System.out.println(newsFeed);
//        twitter.follow(2,1);
//        twitter.getNewsFeed(2);
//        twitter.unfollow(2,1);
//        twitter.getNewsFeed(2);

        Twitter twitter=new Twitter();
        twitter.postTweet(1,4);
        twitter.postTweet(2,5);
        twitter.follow(1,2);
        List<Integer> newsFeed = twitter.getNewsFeed(1);
        System.out.println(newsFeed);
    }
}
