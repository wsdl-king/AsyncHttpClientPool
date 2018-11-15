package com.server.java.sort;

/**
 * @author qiwenshuai
 * @note 插入排序 原地排序 稳定排序 0(n^2) O(1)
 * @since 18-11-15 11:19 by jdk 1.8
 */
public class InsertSort {


    /**
     * 我自己写的
     */
    private static void insertSort(int[] a) {
        //分为已排序部分和未排序部分
        //以第一个元素为基准点
        int size = a.length - 1;
        int i = 1;
        while (size - i >= 0) {
            int c = i;
            int k = i;
            for (int j = i; j > 0; j--) {
                //跟前面元素的比较次数
                //最高移动次数
                if (a[k] >= a[j - 1]) {
                    c--;
                }
            }
            //拿到移动的次数 开始移动
            for (int q = i; c > 0; q--, c--) {
                //开始具体的移动  2,4,5,3
                int temp = a[q - 1];
                a[q - 1] = a[q];
                a[q] = temp;
            }
            i++;
        }

    }


    /**
     * 面向百度编程
     */
    private static void doInsertSort(int a[]) {
        //以0下标的数为基准

        for(int index=1;index<a.length;index++){
            int temp = a[index];
            int leftIndex=index-1;
            while (leftIndex>=0 && temp<a[leftIndex]){
                a[leftIndex+1]=a[leftIndex];
                leftIndex--;
            }
            //容易出错点,记得加1  0 的话是-1  非0的话要在右边,最后补个空位就OK
            a[leftIndex+1]=temp;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 5, 9, 4, 3, 1, 7, 6, 7};
        doInsertSort(a);
    }
}
