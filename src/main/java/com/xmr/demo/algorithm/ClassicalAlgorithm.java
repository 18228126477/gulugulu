package com.xmr.demo.algorithm;

/**
 * 经典算法
 * */
public class ClassicalAlgorithm {


    /**
     * 斐波那契数列 公式循环法 o(n)
     * **/
    static int fibonacciSequenceForArray(int n){
        n=n+1;
        int[] a=new int[n];
        for(int i=0;i<2;i++)
            a[i]=i;
        for(int i=2;i<n;i++)
            a[i]=a[i-1]+a[i-2];
        return a[n-1];
    }

    /**
     * 斐波那契数列 公式递归法 o(2^n)
     * **/
    static int fibonacciSequenceForRecursion(int n){
        if(n==0)
            return 0;
        if(n==1)
            return 1;
        return fibonacciSequenceForRecursion(n-1)+fibonacciSequenceForRecursion(n-2);
    }

    /**
     * 斐波那契数列 矩阵计算法 o(lg(n))
     * **/
    static int fibonacciSequenceForMatrix(int n){
        int a1[][] = new int[2][2];
        a1[0][0]=1;
        a1[1][0]=1;
        for(int i=0;i<n-2;i++){
            int tem=a1[0][0];
            a1[0][0]=a1[0][0]+a1[1][0];
            a1[1][0]=tem;
        }
        return a1[0][0];
    }

    /**
     * 蒙特卡罗算法计算圆周率
     * **/
    public static double monteCarlo(int n){
        double x;
        double y;
        int j = 0;
        for(int i=0;i<n;i++){
            x = Math.random();
            y = Math.random();
            if((x*x+y*y)<1.0)
                j++;
        }
        return ((double) j/n)*4;
    }

    public static void main(String[] args){
        /*long a= System.nanoTime();
        System.out.println(fibonacciSequenceForArray(30));
        System.out.println(System.nanoTime()-a+"纳秒");
        long b= System.nanoTime();
        System.out.println(fibonacciSequenceForRecursion(30));
        System.out.println(System.nanoTime()-b+"纳秒");
        long c= System.nanoTime();
        System.out.println(fibonacciSequenceForMatrix(30));
        System.out.println(System.nanoTime()-c+"纳秒");*/
        System.out.println(monteCarlo(100000000));
    }
}
