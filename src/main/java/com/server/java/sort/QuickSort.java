package com.server.java.sort;

/**
 * @author qiwenshuai
 * @note 练习一个快排玩 时间O(nlogn) 空间 最优的情况下空间复杂度为：O(nlogn)  ；每一次都平分数组的情况
 * 最差的情况下空间复杂度为：O( n2 )      ；退化为冒泡排序的情况
 * @since 18-11-13 14:06 by jdk 1.8
 */
public class QuickSort {
    private static void quickSort(int[] a, int begin, int end) {
        if(begin>end){
            return;
        }
        int low= begin;
        int key=a[begin];
        int high=end;
        while (low<high){
            while (low<high && a[high]>key) high--;
            while (low<high && a[low]<=key) low++;
            if(low<high){
                int temp=a[low];
                a[low]=a[high];
                a[high]=temp;
            }
        }
        int temp =a[begin];
        a[begin]=a[low];
        a[low]=temp;
        quickSort(a,begin,low-1);
        quickSort(a,low+1,end);
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
