package com.server.java.solution;


/**
 * @author qiwenshuai
 * @note 求众数
 * @since 19-3-18 11:24 by jdk 1.8
 */
public class Solution2 {

    public static int majorityElement(int[] nums) {
        int count=1;
        int maj=nums[0];
        for(int i=1;i<nums.length;i++){
            if(maj ==nums[i]){
                count++;
            }else{
                    count--;
                if (count == 0) {
                    maj = nums[i + 1];
                }
            }
        }
        return maj;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,5,4};
        int i = majorityElement(a);
        System.out.println(i);
    }
}
