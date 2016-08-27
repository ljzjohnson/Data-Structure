/*   
 * Copyright (c) 2010-2020 Founder LZG. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
package search;

/**
 * description
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月3日
 */
public class SearchMethods {

    public void pubbleSort(int[] datas) {
	int tmp = 0;
	for (int i = 0; i < datas.length - 1; i++) {
	    for (int j = 0; j < datas.length - i - 1; j++) {
		if (datas[j] > datas[j + 1]) {
		    tmp = datas[j + 1];
		    datas[j + 1] = datas[j];
		    datas[j] = tmp;
		}
	    }
	}
    }

    /**
     * 二分查找的非递归实现，查找成功返回相应下表，否则返回-1。 mid = (start + end)>>1;
     * 
     * @param datas
     * @param item
     * @return
     */
    public int baniarySerach(int[] datas, int item) {
	if (datas == null) {
	    return -1;
	}

	int start = 0;
	int end = datas.length - 1;
	int mid = -1;

	while (start <= end) {
	    mid = (start + end) / 2;
	    if (item < datas[mid]) {
		end = mid - 1;
	    } else if (item > datas[mid]) {
		start = mid + 1;
	    } else {
		return mid;
	    }
	}
	return -1;
    }

    /**
     * 二分查找的递归实现，查找成功返回相应下表，否则返回-1
     */
    public int BSRecursion(int[] datas, int start, int end, int item) {
	if (datas == null) {
	    return -1;
	}

	int mid = (start + end) / 2;
	if (start <= end) {
	    if (item < datas[mid]) {
		return BSRecursion(datas, start, mid - 1, item);
	    } else if (item > datas[mid]) {
		return BSRecursion(datas, mid + 1, end, item);
	    } else {
		return mid;// 成功找到
	    }
	} else {// 没有找到
	    return -1;
	}
    }

    public void sortedBTreeSerach() {
	System.out
		.println("please look at:package nonlinear_table.binary_tree;");
    }

    public void balanceBTreeSerach() {
	System.out
		.println("please look at:package nonlinear_table.binary_tree;");
    }

    public static void main(String[] args) {
	int[] datas = { 3, 4, 1, 6, 9, 5, 2, 7, 8, 10 };
	System.out.println("原始数据：");
	for (int i : datas) {
	    System.out.print(i + "\t");
	}
	System.out.println();

	SearchMethods object = new SearchMethods();
	object.pubbleSort(datas);

	int index = object.baniarySerach(datas, 7);
	System.out.println("查找元素的下标：" + index);

	index = object.BSRecursion(datas, 0, datas.length - 1, 7);
	System.out.println("查找元素的下标：" + index);

    }
}
