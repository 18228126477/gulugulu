package com.xmr.demo.study.algorithm;

/**
 * 1) 数组的插入、删除、按照下标随机访问操作；
 * 2）数组中的数据是int类型的；
 *
 * Author: xia
 */
public class Array<T> {
    //定义整型数据data保存数据
    public T data[];
    //定义数组长度
    private int n;
    //定义中实际个数
    private int count;

    //构造方法，定义数组大小
    public Array(int capacity){
        this.data = (T[])new Object[capacity];
        this.n = capacity;
        this.count=0;//一开始一个数都没有存所以为0
    }

    //根据索引，找到数据中的元素并返回
    public T find(int index){
        if (index<0 || index>=count){
            throw new IllegalArgumentException("Add failed! Require index<0 and index>=count.");
        }
        return data[index];
    }

    //插入元素:头部插入，尾部插入
    public boolean insert(int index, T value){
        // 数组空间已满
        if (count == n) {
            System.out.println("没有可插入的位置");
            return false;
        }
        // 如果count还没满，那么就可以插入数据到数组中
        // 位置不合法
        if (index < 0||index > count ) {
            System.out.println("位置不合法");
            return false;
        }
        // 位置合法
        for( int i = count; i > index; --i){
            data[i] = data[i - 1];
        }
        data[index] = value;
        ++count;
        return true;
    }
    //根据索引，删除数组中元素
    public boolean delete(int index){
        if (index<0 || index >=count) return false;
        //从删除位置开始，将后面的元素向前移动一位
        for (int i=index+1; i<count; ++i){
            data[i-1] = data[i];
        }
        --count;
        return true;
    }
    public void printAll() {
        for (int i = 0; i < count; ++i) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Array<String> array = new Array<>(5);
        array.printAll();
        array.insert(0, "a");
        array.insert(1, "b");
        array.insert(2, "c");
        array.insert(3, "d");
        array.insert(4, "e");
        //array.insert(3, 11);
        array.printAll();
    }
}