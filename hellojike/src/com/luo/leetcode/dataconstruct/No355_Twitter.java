package com.luo.leetcode.dataconstruct;

import java.util.*;
import java.util.stream.Collectors;

/*

355. 设计推特
    设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，
    能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：

        postTweet(userId, tweetId): 创建一条新的推文
        getNewsFeed(userId): 检索最近的十条推文。
            每个推文都必须是由此用户关注的人或者是用户自己发出的。
            推文必须按照时间顺序由最近的开始排序。
        follow(followerId, followeeId): 关注一个用户
        unfollow(followerId, followeeId): 取消关注一个用户
        示例:

        Twitter twitter = new Twitter();

        // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
        twitter.postTweet(1, 5);

        // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        twitter.getNewsFeed(1);

        // 用户1关注了用户2.
        twitter.follow(1, 2);

        // 用户2发送了一个新推文 (推文id = 6).
        twitter.postTweet(2, 6);

        // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
        // 推文id6应当在推文id5之前，因为它是在5之后发送的.
        twitter.getNewsFeed(1);

        // 用户1取消关注了用户2.
        twitter.unfollow(1, 2);

        // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        // 因为用户1已经不再关注用户2.
        twitter.getNewsFeed(1);


  使用系统的时间戳是不可行的,有可能在一瞬间有多条推特生成
  可以考虑全局序号id
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

    /**
     * 前边的解法这是为了解决问题
     * 并没有根据具体场景设计模块
     */
    static class Twitter2{
        private static int timestamp;
        private HashMap<Integer,User> userMap=new HashMap<>();
        private static class Tweet{
            private int id;
            private int time;
            private Tweet next;

            public Tweet(int id, int time) {
                this.id = id;
                this.time = time;
                this.next=null;
            }
        }
        private static class User{
            private int id;
            public Set<Integer> followed;
            public Tweet head;

            public User(int userId) {
                this.id = userId;
                this.head=null;
                this.followed=new HashSet<>();
                follow(id);
            }
            public void follow(int userId){
                followed.add(userId);
            }
            public void post(int tweetId){
                Tweet tweet=new Tweet(tweetId,timestamp);
                timestamp++;
                tweet.next=head;
                head=tweet;
            }
            public void unfollow(int userId){
                if(userId!=id){
                    followed.remove(userId);
                }
            }
        }
        public void postTweet(int userId,int tweetId){
            if(!userMap.containsKey(userId)){
                userMap.put(userId,new User(userId));
            }
            User user = userMap.get(userId);
            user.post(tweetId);
        }
        public List<Integer> getNewsFeed(int userId){
            List<Integer> result=new ArrayList<>();
            if(!userMap.containsKey(userId)){
                return result;
            }
            Set<Integer> followed = userMap.get(userId).followed;
            PriorityQueue<Tweet> queue=new PriorityQueue<>((t1,t2)->t2.time-t1.time);
            for (int id:followed){
                Tweet t=userMap.get(id).head;
                if(t==null){
                    continue;
                }
                queue.offer(t);
            }
            while(!queue.isEmpty()){
                if(result.size()==10){
                    break;
                }
                Tweet poll = queue.poll();
                result.add(poll.id);
                if(poll.next!=null){
                    queue.add(poll.next);
                }
            }
            return result;
        }
        public void follow(int followerId,int followeeId){
            if(!userMap.containsKey(followerId)){
                User follower=new User(followerId);
                userMap.put(followerId,follower);
            }
            if(!userMap.containsKey(followeeId)){
                User followee=new User(followeeId);
                userMap.put(followeeId,followee);
            }
            userMap.get(followerId).follow(followeeId);
        }
        public void unfollow(int followerId,int followeeId){
            if(userMap.containsKey(followerId)){
                userMap.get(followerId).unfollow(followeeId);
            }
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
