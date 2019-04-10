package com.server.java.sort;

/**
 * @author qiwenshuai
 * @note   手写二分查找
 * @since 19-4-10 18:04 by jdk 1.8
 */
public class TwoPoints {


    public static int search(int[] a, int key) {
        int left = 0;
        int right = a.length - 1;

        while (left <= right) {
            int mid = (left + right) >> 1;
            if (key == a[mid]) return a[mid];
            if (key >= a[mid]) left = mid + 1;
            if (key < a[mid]) right = mid - 1;
        }
        return 0;
    }


    public static void main(String[] args) {
        int[] a={1,2,3,4,5};
        int search = search(a, 7);
        System.out.println(search);
    }
}


