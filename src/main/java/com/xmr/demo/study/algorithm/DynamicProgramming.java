package com.xmr.demo.study.algorithm;

import java.util.Arrays;

public class DynamicProgramming {

    public static void main(String[] args) {
        // 物品的数量
        int number = 4;
        // 注意：我们声明数组的长度为"n+1"。
        // {0,132,25,59,13} 每个物品对应的重量
        int[] weight = new int[number + 1];
        weight[0] = 0;
        weight[1] = 132;
        weight[2] = 25;
        weight[3] = 59;
        weight[4] = 13;
        // {0,1,1,1,1} 每个物品对应的价值
        int[] value = new int[number + 1];
        value[0] = 0;
        value[1] = 1;
        value[2] = 1;
        value[3] = 1;
        value[4] = 1;
        // 背包容量
        int capacity = 1000;
        /* 2.求解01背包问题 */
        // 声明动态规划表.其中v[i][j]对应于：
        // 当前有i个物品可选，并且当前背包的容量为j时，我们能得到的最大价值
        int[][] v = new int[number + 1][capacity + 1];

        // 填动态规划表。当前有i个物品可选，并且当前背包的容量为j。
        for (int i = 1; i < number + 1; i++) {
            for (int j = 1; j < capacity + 1; j++) {
                if (j < weight[i]) {
                    // 包的容量比当前该物品体积小，装不下，此时的价值与前i-1个的价值是一样的，即V(i,j)=V(i-1,j)；
                    v[i][j] = v[i - 1][j];
                } else {
                    // 还有足够的容量可以装当前该物品，但装了当前物品也不一定达到当前最优价值，
                    // 所以在装与不装之间选择最优的一个，即V(i,j)=max｛V(i-1,j)，V(i-1,j-w(i))+v(i)｝。
                    v[i][j] = Math.max(v[i - 1][j], v[i - 1][j - weight[i]] + value[i]);
                }
            }
        }
        System.out.println("---------------------------");
        System.out.println("动态规划表如下：");
        for (int i = 0; i < number + 1; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                System.out.print(v[i][j] + "\t");
            }
            System.out.println();
        }
        // 有number个物品可选，且背包的容量为capacity的情况下，能装入背包的最大价值
        System.out.println("背包内最大的物品价值总和为：" + v[number][capacity]);

        /* 3.价值最大时，包内装入了哪些物品？ */

        // 下标i对应的物品若被选中，设置值为1
        int[] item = new int[number + 1];
        // 将数组item的所有元素初始化为0
        Arrays.fill(item, 0);
        // 从最优解，倒推回去找
        int j = capacity;
        for (int i = number; i > 0; i--) {
            // 在最优解中，v[i][j]>v[i-1][j]说明选择了第i个商品
            if (v[i][j] > v[i - 1][j]) {
                item[i] = 1;
                j = j - weight[i];
            }
        }

        System.out.print("包内物品的编号为：");
        for (int i = 0; i < number + 1; i++) {
            if (item[i] == 1) {
                System.out.print(i + " ");
            }
        }
        System.out.println("\t");
        System.out.println("----------------------------");
    }
}
