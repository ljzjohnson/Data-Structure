/*   
 * Copyright (c) 2010-2020 Founder LZG. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
package sort;

import java.util.Random;

/**
 * 经测试，排序好费时间排序：直接插入>直接选择>冒泡>折半插入
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月3日
 */
public class BaiscSortMethods {
    public int[] basicDirectInsert(int[] datas) {
	int tmp = 0;
	for (int i = 0; i < datas.length; i++) {
	    for (int j = i; j > 0; j--) {
		if (datas[j] < datas[j - 1]) {
		    tmp = datas[j];
		    datas[j] = datas[j - 1];
		    datas[j - 1] = tmp;
		}
	    }
	}
	return datas;
    }

    public int[] basicSelectSort(int[] datas) {
	for (int i = 0; i < datas.length; i++) {
	    int min = Integer.MAX_VALUE, minIndex = -1;
	    for (int j = i; j < datas.length; j++) {
		if (datas[j] < min) {
		    min = datas[j];
		    minIndex = j;
		}
	    }
	    int tmp = 0;
	    tmp = datas[minIndex];
	    datas[minIndex] = datas[i];
	    datas[i] = tmp;
	}
	return datas;
    }

    public int[] basicBubbleSort(int[] datas) {
	int tmp = 0;
	for (int i = 0; i < datas.length - 1; i++) {// 控制轮数，最多N-1轮
	    for (int j = 0; j < datas.length - i - 1; j++) {// 控制内存循环次数，上限为总元素个数减已经排序的轮数
		if (datas[j] > datas[j + 1]) {
		    tmp = datas[j + 1];
		    datas[j + 1] = datas[j];
		    datas[j] = tmp;
		}
	    }
	}
	return datas;
    }

    public int[] basicBinaryInsert(int[] datas) {
	int index, tmp;
	for (int i = 0; i < datas.length - 1; i++) {
	    index = binarySearch(datas, i, datas[i + 1]);
	    tmp = datas[i + 1];
	    for (int j = i + 1; j > index; j--) {
		datas[j] = datas[j - 1];
	    }
	    datas[index] = tmp;
	}
	return datas;
    }

    private int binarySearch(int[] datas, int end, int data) {
	int start = 0;
	int middle;
	while (start <= end) {// =对应着数组中只有一个元素的情况
	    middle = (start + end) / 2;
	    if (data < datas[middle]) {
		end = middle - 1;
	    } else {
		start = middle + 1;
	    }
	}
	return start;
    }

    public static void main(String[] args) {
	// int[] datas = { 3, 4, 1, 6, 9, 5, 2, 7, 8, 10 };
	int size = 10000;
	int[] datas = new int[size];
	Random rand = new Random();
	for (int i = 0; i < datas.length; i++) {
	    datas[i] = rand.nextInt(1000);
	}

	System.out.println("原始数据：");
	// for (int i : datas) {
	// System.out.print(i + "\t");
	// }
	BaiscSortMethods object = new BaiscSortMethods();
	long curTime = System.currentTimeMillis();
	// int[] sortedDatas = object.basicDirectInsert(datas);
	long curTime1 = System.currentTimeMillis();

	// System.out.println();
	// System.out.println("直接插入排序：" + (curTime1 - curTime));
	// // for (int i : sortedDatas) {
	// // System.out.print(i + "\t");
	// // }
	//
	// curTime = System.currentTimeMillis();
	// sortedDatas = object.basicSelectSort(datas);
	// curTime1 = System.currentTimeMillis();
	// System.out.println();
	// System.out.println("选择排序：" + (curTime1 - curTime));
	// // for (int i : sortedDatas) {
	// // System.out.print(i + "\t");
	// // }
	//
	// curTime = System.currentTimeMillis();
	// sortedDatas = object.basicBubbleSort(datas);
	// curTime1 = System.currentTimeMillis();
	// System.out.println();
	// System.out.println("冒泡排序：" + (curTime1 - curTime));
	// // for (int i : sortedDatas) {
	// // System.out.print(i + "\t");
	// // }

	curTime = System.currentTimeMillis();
	int[] sortedDatas = object.basicBinaryInsert(datas);
	curTime1 = System.currentTimeMillis();
	System.out.println();
	System.out.println("折半插入排序：" + (curTime1 - curTime));
	// for (int i : sortedDatas) {
	// System.out.print(i + "\t");
	// }
    }
}
