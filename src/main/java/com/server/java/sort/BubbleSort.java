package com.server.java.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qiwenshuai
 * @note 冒泡排序法 O(n^2),O(1)
 * @since 18-11-13 11:44 by jdk 1.8
 */
public class BubbleSort {


    private static List sort(List<Integer> list) {

        if (list.isEmpty() || list.size() == 1) {
            return list;
        }
        int n = list.size();
         for(int i =0;i<n;i++){
             boolean flag =false;
              for(int j =0;j<n-1-i;j++){
                  Integer integer1 = list.get(j);
                  Integer integer = list.get(j+1);
                  if(integer1>integer){
                      list.set(j+1,integer1);
                      list.set(j,integer);
                      flag=true;
                  }
              }
             if(!flag) return list;
         }
        return list;
    }

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>(Arrays.asList(9, 2, 1, 8, 7));
        List sort = sort(integers);
        //冒泡排序
        System.out.println(sort);
    }
}


