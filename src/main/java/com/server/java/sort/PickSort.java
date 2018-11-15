package com.server.java.sort;

/**
 * @author qiwenshuai
 * @note 选择排序   时间复杂度 O(^2)   空间0(1)
 * @since 18-11-15 14:48 by jdk 1.8
 */
public class PickSort {

    private static void pickSort(int a[]) {
        //把数组中最大的放左边
        int index = 0;
        while (index < a.length) {
            for(int i=index;i<a.length;i++){
                //交换位置
                if(a[i]<a[index]){
                    int temp=a[index];
                    a[index]=a[i];
                    a[i]=temp;
                }
            }
            index++;
        }

    }


    public static void main(String[] args) {
         int a[] =new int[]{5,2,2,1,3};
         pickSort(a);
    }
}
