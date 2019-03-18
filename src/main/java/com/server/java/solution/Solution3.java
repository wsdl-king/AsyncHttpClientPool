package com.server.java.solution;

import java.util.Arrays;

/**
 * @author qiwenshuai
 * @note 给定一个未排序的整数数组, 找出其中没有出现的最小正整数.
 * @since 19-3-18 13:54 by jdk 1.8
 */
public class Solution3 {

    // 第一种实现  我的实现
    public static int firstMissingPositive1(int[] nums) {
        // 最小的数字不是0,那便加1  -2,-1,0,2,4
        int max = nums.length;
        int count = 0;
        Arrays.sort(nums);
        for (int num : nums) {
            if (num < 0) count++;
        }
        int[] ints = Arrays.copyOfRange(nums, count, max);
        int max2 = 1;
        for (int anInt : ints) {
            if (anInt == max2) max2++;
        }
        return max2;
    }


    //第二种实现
    // 最小的数字不是0,那便加1  -2,-1,0,2,4
    public static int firstMissingPositive2(int[] nums) {
        int[] temp = new int[nums.length];
        System.arraycopy(nums, 0, temp, 0, temp.length);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >=1&& nums[i] <= nums.length) {
                temp[nums[i] - 1] = nums[i];
            }
        }
        int i;
        for (i = 0; i < nums.length; i++) {
            if (temp[i] != i + 1) {
                break;
            }
        }
        return i + 1;

    }

    public static void main(String[] args) {
        int[] a = {2, 1};
        int i = firstMissingPositive2(a);
        System.out.println(i);
    }
}
