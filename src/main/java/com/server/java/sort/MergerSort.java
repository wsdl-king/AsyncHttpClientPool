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

    private static void mergerSort(int[] a, Integer begin, Integer end) {
        if (begin >= end) return;
        int mid = (begin + end) >> 1;
        mergerSort(a, begin, mid);
        mergerSort(a, mid + 1, end);
        merge(a, begin, mid, end);
    }

    //1,2,  3 6,4  2--4
    private static void merge(int[] a, int begin, int mid, int end) {
        int[] temp = new int[end - begin + 1];
        int m = begin;
        int n = mid + 1;
        int k = 0;
        while (m <= mid && n <= end) {
            if (a[m] <= a[n]) {
                temp[k++] = a[m++];
            } else {
                temp[k++] = a[n++];
            }

        }
        while (m <= mid) {
            temp[k++] = a[m++];
        }
        while (n <= end) {
            temp[k++] = a[n++];
        }
        for (int i = 0; i < temp.length; i++) {
            a[begin + i] = temp[i];
        }
    }

    public static void main(String[] args) {
        sort(new int[]{2, 1, 9, 7, 5, 4, 6});

    }
}
