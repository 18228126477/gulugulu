package com.xmr.demo.algorithm;

public class SortingAlgorithm {

    public static void bubbleSort(int[] arr){
        for(int i=arr.length-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(arr[j]>arr[j+1]){
                    int tmp = arr[j];
                    arr[j] =  arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args){
        int[] arr = {1,3,4,2,9,6,7,8};
        bubbleSort(arr);
        for(int a:arr){
            System.out.println(a);
        }
    }
}