package com.luo.leetcode.string;

import com.luo.util.CommonUtil;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，
 * 返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 */
public class No43_multiply {

    /**
     * 没有捷径,就是模拟乘法
     *
     * 时间复杂度:   O(m*n) m为数字1长度,n为数字2长度
     * 空间复杂度:   O(m+n)
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
//        遍历第二个数的每一个数字,和第一个数相乘,并且累加
        int len1=num1.length();
        int len2=num2.length();
        String res="";
        StringBuilder sb=null;
        for (int i = 0; i < len2 ; i++) {
            sb=new StringBuilder();
//            需要补0
            for (int j = 0; j <len2-(i+1) ; j++) {
                sb.append("0");
            }
            int n2=num2.charAt(i)-'0';
            int c=0;
            for (int j = len1-1; j >=0; j--) {
                int n1=num1.charAt(j)-'0';
                int m=n1*n2+c;
                int t=m%10;
                c=m/10;
                sb.append(t);
                if(j==0&&c>0){
                    sb.append(c);
                }
            }
//            现在sb已经是第二个数的某一个数字和第一个数相乘的结果的反转数了.
//            现在需要累加
            res=addString(sb.toString(),res);
        }
        sb=new StringBuilder();
        sb.append(res);
//        除去最后的0
        while(sb.length()>1&&sb.charAt(sb.length()-1)=='0'){
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.reverse().toString();
    }

    private String addString(String str1,String str2){
        int len1=str1.length();
        int len2=str2.length();
        int max=Math.max(len1,len2);
        StringBuilder sb=new StringBuilder(max+1);
        int c=0;
        for (int i = 0; i < max; i++) {
            int c1=i>=len1?0:(str1.charAt(i)-'0');
            int c2=i>=len2?0:(str2.charAt(i)-'0');
            int a=c1+c2+c;
            int t=a%10;
            c=a/10;
            sb.append(t);
            if(i==max-1&&c>0){
                sb.append(c);
            }
        }
        return sb.toString();
    }


    /**
     * 优化在于减少字符串操作,
     * 长度为m的数字和长度为n的数字,有可能长度为m+n,有可能长度为m+n+1
     * 建立长度为m+n+1的二维数组,储存每一位的值
     * @param num1
     * @param num2
     * @return
     */
    public String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }
        CommonUtil.display(ansArr);
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }

        CommonUtil.display(ansArr);
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuffer ans = new StringBuffer();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }


    public static void main(String[] args){
        No43_multiply test=new No43_multiply();
//        String num1 = "2", num2 = "3";
//        String num1 = "123", num2 = "456";
//        String num1 = "0", num2 = "111";
        String num1 = "140", num2 = "721";
        String multiply = test.multiply(num1, num2);
        System.out.println(multiply);

        String s = test.multiply2(num1, num2);
        System.out.println(s);
    }
}
