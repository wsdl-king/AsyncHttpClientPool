package com.server.java.sort;

/**
 * @author qiwenshuai
 * @note 写个归并排序  时间0(nlogn) 空间0(n^2) 原地稳定排序算法
 * @since 18-11-14 15:15 by jdk 1.8
 */
public class MergerSort {


    private static void sort(int[] p) {
        mergerSort(p, 0, p.length - 1);
    }


    private static void mergerSort(int[] a, Integer p, Integer q) {
        if (p >= q) return;
        int mid = (p + q) >> 1;
        mergerSort(a, p, mid);
        mergerSort(a, mid + 1, q);
        merge(a, p, mid, q);
    }

    //1,2,  3 6,4  2--4
    private static void merge(int[] a, int low, int mid, int high) {
        //创建临时数组low
        int[] temp = new int[high - low + 1];
        int k = 0;
        int p = low;
        int q = mid + 1;
        while (p <= mid && q <= high) {
            if (a[p] < a[q]) {
                temp[k++] = a[p++];
            } else {
                temp[k++] = a[q++];
            }
        }
        while (p <= mid) {
            temp[k++] = a[p++];
        }
        while (q <= high) {
            temp[k++] = a[q++];
        }
        for (int x = 0; x < temp.length; x++) {
            a[low + x] =temp[x];
        }
    }

    public static void main(String[] args) {
        sort(new int[]{2, 1, 9, 7, 5, 4, 6});

    }
}
