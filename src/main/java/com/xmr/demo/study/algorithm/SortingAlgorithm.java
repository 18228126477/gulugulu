package com.xmr.demo.study.algorithm;


public class SortingAlgorithm {

    /**
     * 冒泡排序
     * **/
    private static void bubbleSort(int[] arr){
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

    /**
     * 插入排序
     * **/
    private static void insertSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int tmp=arr[i];
            int j;
            for(j=i-1;j>=0;j--){
                if(arr[j]>tmp){
                    arr[j+1]=arr[j];
                }else{
                    break;
                }
            }
            arr[j+1]=tmp;
        }
    }

    /**
     * 归并排序
     * **/
    private static void mergeSort(int[] arr){
        int length =arr.length/2;
        int[] a = new int[length];
        int[] b = new int[arr.length-length];
        System.arraycopy(arr, 0, a, 0, length);
        System.arraycopy(arr, length, b, 0, arr.length-length);
        insertSort(a);
        insertSort(b);
        int x=0;
        int y=0;
        int k=0;
        int[] c = new int[arr.length];
        while(x<a.length && y<b.length){
            if(a[x]<b[y]){
                c[k++] = a[x];
                x++;
            }else{
                c[k++] = b[y];
                y++;
            }
        }
        while(x<a.length){
            c[k++] = a[x++];
        }
        while (y<b.length){
            c[k++] = b[y++];
        }
    }

    /**
     * 快速排序
     * **/
    private static void quickSort(int[] arr,int p,int q){
        if(p<q){
            int a = partition(arr,p,q);
            quickSort(arr,p,a-1);
            quickSort(arr,a+1,q);
        }
    }

    /**
     * 随机快速排序
     * **/
    private  static  void quickSortRandom(int[] arr,int p,int q){
        int rand = (int)(Math.random()*(q-p)+p);
        swap(arr,p,rand);
        quickSort(arr,p,q);
    }

    private static int partition(int[] arr,int p,int q){
        /*int i =p;
        int j =q+1;
        int v = arr[p];
        while(true){
            while(arr[++i]<v)
                if(i>=q) break;
            while(arr[--j]>v)
                if(j<=p) break;
            if(i>=j) break;
            swap(arr,i,j);
        }
        swap(arr,p,j);
        return j;*/
        int i=p;
        int v=arr[p];
        for(int j=p+1;j<=q;j++){
            if(v>=arr[j]){
                swap(arr,++i,j);
            }
        }
        swap(arr,p,i);
        return i;
    }

    private static void swap(int[] arr,int p,int q){
        int x = arr[p];
        arr[p]=arr[q];
        arr[q]=x;
    }

    public static void main(String[] args) {
        int[] arr = {6, 10, 13, 5, 8, 3, 2, 11};
        long startTime=System.nanoTime();
        mergeSort(arr);
        long endTime=System.nanoTime();
        System.out.println("归并排序运行时间： "+(endTime-startTime)+"ms");
        int[] arr1 = {6,10,13,5,8,3,2,11};
        startTime=System.nanoTime();
        insertSort(arr1);
        endTime=System.nanoTime();
        System.out.println("插入排序运行时间： "+(endTime-startTime)+"ms");
        int[] arr2 = {6,10,13,5,8,3,2,11};
        startTime=System.nanoTime();
        bubbleSort(arr2);
        endTime=System.nanoTime();
        System.out.println("冒泡排序运行时间： "+(endTime-startTime)+"ms");
        int[] arr3 = {6,10,13,5,8,3,2,11};
        startTime=System.nanoTime();
        quickSort(arr3,0,arr3.length-1);
        endTime=System.nanoTime();
        System.out.println("快速排序运行时间： "+(endTime-startTime)+"ms");
        int[] arr4 = {6,10,13,5,8,3,2,11};
        startTime=System.nanoTime();
        quickSortRandom(arr4,0,arr4.length-1);
        endTime=System.nanoTime();
        System.out.println("随机快速排序运行时间： "+(endTime-startTime)+"ms");
        //print(arr4);
    }

    private static void print(int[] arr){
        for(int i:arr){
            System.out.println(arr[i]);
        }
    }
}