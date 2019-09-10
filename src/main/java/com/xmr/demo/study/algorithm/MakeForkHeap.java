package com.xmr.demo.study.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最大二叉堆的增加和删除以及维护
 *
 */
public class MakeForkHeap<T extends Comparable<T>>{
    //最大堆
    private List<T> binaryHeap;

    private MakeForkHeap() {
        this.binaryHeap = new ArrayList<>();
    }

    public static void main(String[] args){
        MakeForkHeap<Integer> tree=new MakeForkHeap<>();
        int a[] = {10, 40, 30, 60, 90, 70, 20, 50, 80,10, 40, 30, 60, 90, 70, 20, 50, 80,10, 40, 30, 60, 90, 70, 20, 50, 80,10, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 80,10, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 8010, 40, 30, 60, 90, 70, 20, 50, 80};
        Arrays.sort(a);
        long startTime=System.nanoTime();
        System.out.println(a[a.length-1]);
        long endTime=System.nanoTime();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ns");
        for(int i =0; i<a.length; i++) {
            tree.insert(a[i]);
        }
        startTime=System.nanoTime();
        System.out.println(tree.binaryHeap.get(0));
        endTime=System.nanoTime();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ns");
    }

    //最大堆的向上调整算法，用于添加
    private void maintainL(int start){
        int begin = start;
        int father = (begin-1)/2;
        T integer = binaryHeap.get(start);
        while (begin>0){
            int i = integer.compareTo(binaryHeap.get(father));
            if(i<0)
                break;
            else{
                binaryHeap.set(begin,binaryHeap.get(father));
                begin = father;
                father = (begin-1)/2;
            }
        }
        binaryHeap.set(begin,integer);
    }

    //最大堆的向下调整算法，用于删除
    private void maintainM(int start,int end){
        int begin = start;
        int left = begin*2+1;
        T integer = binaryHeap.get(begin);
        while (left <= end && left + 1 <= end) {
            int i = binaryHeap.get(left).compareTo(binaryHeap.get(left + 1));
            if (i < 0)
                left++;
            i = integer.compareTo(binaryHeap.get(left));
            if (i >= 0)
                break;
            else {
                binaryHeap.set(begin, binaryHeap.get(left));
                begin = left;
                left = begin * 2 + 1;
            }
        }
        binaryHeap.set(begin,integer);
    }

    //添加
    private void insert(T data) {
        int size = binaryHeap.size();
        binaryHeap.add(data);
        maintainL(size);
    }

    //删除
    private Integer remove(T data){
        if(binaryHeap.isEmpty())
            return null;
        int i = binaryHeap.indexOf(data);
        int size = binaryHeap.size();
        if(i == -1)
            return null;
        binaryHeap.set(i,binaryHeap.get(size-1));
        binaryHeap.remove(size-1);
        if(size >1){
            maintainM(i,size-1);
        }
        return i;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < binaryHeap.size(); i++)
            sb.append(binaryHeap.get(i) + " ");
        return sb.toString();
    }
}
