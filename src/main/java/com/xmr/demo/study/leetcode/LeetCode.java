package com.xmr.demo.study.leetcode;


import java.util.HashSet;
import java.util.Set;

public class LeetCode {
    public static void main(String[] args) {
        int abcdaaxczxcvnm123456789 = lengthOfLongestSubstring("ABCBCABCBB");
        System.out.println(abcdaaxczxcvnm123456789);
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * 示例:
     *      给定 nums = [2, 7, 11, 15], target = 9
     *      因为 nums[0] + nums[1] = 2 + 7 = 9
     *      所以返回 [0, 1]
     * */
    public static int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(target == nums[j]+nums[i]){
                    ans[0]=i;
                    ans[1]=j;
                    return ans;
                }
            }
        }
        return ans;
    }

    /**
     * 给出两个非空的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 示例：
     *      输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     *      输出：7 -> 0 -> 8
     *      原因：342 + 465 = 807
     * */

    public static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode res = pre;
        int carry=0;
        while(l1!=null || l2!=null){
            int x = l1==null?0:l1.val;
            int y = l2==null?0:l2.val;
            int sum = x+y+carry;
            carry = sum/10;
            sum = sum%10;
            res.val=sum;
            if(l1!=null){
                l1 = l1.next;
            }
            if(l2!=null){
                l2 = l2.next;
            }
            pre.next=new ListNode(sum);
            pre=pre.next;
        }
        if(carry==1){
            pre.next=new ListNode(1);
        }
        return res.next;
    }

    public static int lengthOfLongestSubstring(String s) {
        int i = 0;
        int flag = 0;
        int length = 0;
        int result = 0;
        while (i < s.length()) {
            int pos = s.indexOf(s.charAt(i),flag);
            if (pos < i) {
                if (length > result) {
                    result = length;
                }
                if (result >= s.length() - pos - 1) {
                    return result;
                }
                length = i - pos - 1;
                flag = pos + 1;
            }
            length++;
            i++;
        }
        return length;
    }
}
