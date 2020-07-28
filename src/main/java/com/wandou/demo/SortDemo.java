package com.wandou.demo;

import org.junit.Test;

//import java.sql.SQLOutput;
import java.util.Arrays;

/**
 * @author liming
 * @date 2020/7/23
 */
public class SortDemo {

    /**
     * exe 冒泡排序
     */
    @Test
    public void bubblingSortExe() {
        int[] arr = {1, 3, 8, -4, 3, 30, 2, 5};
        int[] arr1 = {1, 3, 8, -4, 3, 30, 2, 5};
        bubblingSort(arr);
        bubblingSortV2(arr1);
        System.out.println("arr: " + Arrays.toString(arr));
        System.out.println("arr1: " + Arrays.toString(arr1));
    }

    /**
     * do 冒泡
     */
    public void bubblingSort(int[] arr) {
        int count = 0;
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1; j++) {
                count ++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                // System.out.println("内层第" + j + "轮循环: " + Arrays.toString(arr));
            }
            // System.out.println("外层第" + i + "轮循环: " + Arrays.toString(arr));
        }
        System.out.println(count);
    }

    public void bubblingSortV2(int[] arr) {
        int count = 0;
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                count ++;
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(count);
    }

}
