package com.server.java.sort;

/**
 * @author qiwenshuai
 * @note 练习一个快排玩 时间O(nlogn) 空间 最优的情况下空间复杂度为：O(nlogn)  ；每一次都平分数组的情况
 * 最差的情况下空间复杂度为：O( n2 )      ；退化为冒泡排序的情况
 * @since 18-11-13 14:06 by jdk 1.8
 */
public class QuickSort {

    private static void quickSort(int[] a, int low, int high) {
        if (low > high) {
            return;
        }
        int i = low;
        int j = high;
        int key = a[low];
        while (i < j) {
            while (i < j && a[j] > key) j--;
            while (i < j && a[i] <= key) i++;
            if (i < j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        int temp = a[low];
        a[low] = a[i];
        a[i] = temp;
        quickSort(a,low,i-1);
        quickSort(a,i+1,high);
    }


    private static int subQuickSortCore(int[] arrays, int start, int end) {
        int middleValue = arrays[start];
        while (start < end) {
            while (arrays[end] >= middleValue && start < end) {
                end--;
            }
            arrays[start] = arrays[end];
            while (arrays[start] <= middleValue && start < end) {
                start++;
            }
            arrays[end] = arrays[start];
        }
        arrays[start] = middleValue;
        return start;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 1, 7, 3, 8, 6, 6};
        quickSort(a, 0, 6);
    }


}
