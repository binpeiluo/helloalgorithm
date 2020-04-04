package com.luo.leetcode.stack;

import java.util.Stack;

/**
 * 71. 简化路径
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 *
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；
 * 此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。
 * 更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 *
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。
 * 最后一个目录名（如果存在）不能以 / 结尾。
 * 此外，规范路径必须是表示绝对路径的最短字符串。
 *
 * 示例 1：
 *
 * 输入："/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 *
 */
public class No71_simplifyPath {
    public String simplifyPath(String path) {
        String[] split = path.split("/");
        Stack<String> stack=new Stack<>();
        for (int i = 0,len=split.length; i < len; i++) {
            if(".".equals(split[i])||split[i]==null||"".equals(split[i])){
//                不处理
                continue;
            }else if("..".equals(split[i])){
                if(stack.isEmpty())
                    continue;
                stack.pop();
            }else
                stack.push(split[i]);
        }
        String result="";
        while(!stack.isEmpty())
            result="/"+stack.pop()+result;
        if(result.equals(""))
            result="/";
        return result;
    }

    public static void main(String[] args){
        No71_simplifyPath test=new No71_simplifyPath();
//        String path="/home/";
//        String path="/../";
//        String path="/home//foo/";
//        String path="/a/./b/../../c/";
        String path="/a//b////c/d//././/..";
        String s = test.simplifyPath(path);
        System.out.println(s);
    }
}
