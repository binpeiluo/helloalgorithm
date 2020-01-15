package com.luo.sevendays.day2.recursion;

public class Factorial {
    int factorial(int n){
        if(n<1)
            return 1;
        return n*factorial(n-1);
    }

    public static void main(String[] args){
        Factorial test=new Factorial();
        int factorial = test.factorial(3);
        System.out.println("factorial="+factorial);
    }
}
