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

import java.util.Arrays;

/**
 * 堆排序是快排三倍到五倍速度，快速排序是归并排序一倍速度,但是快速排序、堆排序不稳定，归并排序稳定
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月3日
 */
public class AdvancedSortMethods {
    public int[] quickSort(int[] datas, int start, int end) {
	if (start < end) {// 迭代终止条件:start>end
	    int position = partition(datas, start, end);// 进行一轮分割，并返回分割后枢纽所在位置
	    // 如果已经排好序，那么position==start,进行下一次快拍，start>end
	    quickSort(datas, start, position - 1);// 左半部分
	    quickSort(datas, position + 1, end);// 右半部分
	}
	return datas;
    }

    private int partition(int[] datas, int start, int end) {
	int std = datas[start];

	// 比较的过程中，start,end不断变化，直至start>=end，比较结束。
	// 其实是=时停止，即停止时，start，end指向同一个元素。
	while (start < end) {
	    while (std < datas[end] && start < end) {// 从右向左扫描
		end--;
	    }
	    if (start < end) {
		datas[start++] = datas[end];// 这种处理方式可以减少交换赋值的次数
	    }

	    // std > datas[start]也可是<=
	    while (std > datas[start] && start < end) {// 从左向右扫描
		start++;
	    }
	    if (start < end) {
		datas[end--] = datas[start];
	    }
	}
	datas[start] = std;// 一轮分割结束后，将枢纽位置的值补上
	return start;// 此时start=end

    }

    // 这里用的是二路归并，采用二分查找的方法进行分裂。归并实际在做的过程中是，先排序左半部分，然后再排序右半部分
    public int[] mergeSort(int[] datas, int start, int end) {
	if (start < end) {
	    int mid = (start + end) / 2;
	    mergeSort(datas, start, mid);// 先排序左半部分,不断递归进行
	    mergeSort(datas, mid + 1, end);// 再排序右半部分，不断递归进行
	    merge(datas, start, mid, end);// 最后合并左右两部分
	}
	return datas;
    }

    // 将两个有序的子序列合并成一个有序的新序列
    private void merge(int[] datas, int start, int mid, int end) {
	int i = start;
	int j = mid + 1;// 分别是两个子序列的起点
	int[] tmpArray = new int[end - start + 1];
	int k = 0;

	while (i <= mid && j <= end) {// 一旦条件不成立，肯定有一个序列中的元素已全部放入临时数组中
	    if (datas[i] < datas[j]) {// 较小的元素放入临时数组中
		tmpArray[k++] = datas[i++];
	    } else {
		tmpArray[k++] = datas[j++];
	    }
	}

	// 右序列已经全部放入临时数组中
	while (i <= mid) {
	    tmpArray[k++] = datas[i++];
	}

	// 左序列已经全部放入临时数组中
	while (j <= end) {
	    tmpArray[k++] = datas[j++];
	}

	// 将临时数组中的内容复制到原始数组中
	for (int item : tmpArray) {
	    datas[start++] = item;
	}
    }

    // 堆排序（这里是最大堆）：
    // 第一步：将初始数组视为无序堆，根据堆定义，对无序堆进行堆调整。（根据堆定义，由初始数组建立初始堆）
    // 第二步：将堆顶元素和数组的最后一个元素交换，重新调整堆使其为最大堆，待排数组长度减1。
    // 第三步：不断重复第二步直至排序完成。只需重复n-1次
    // 注意，一旦发生交换就要一直向下确认直到叶子节点。构建最大堆实现升序排序，最小堆实现降序排序。
    // 这里的堆用数组实现，堆是一个完全二叉树，即数组实现二叉树，节点之间的关系根据相应的关系进行确定。
    // 这里面的核心函数是maxHeap调整堆。
    // 堆序性质：根元素大于左右子节点（递归定义）
    /*
     * 这里堆排序的相关操作和构建二叉堆是一样的，只是二叉堆还具备其它的一些功能（插入（使用上滤）、删除指定节点）
     * 优先队列-二叉堆内部使用数组进行操作，和这里一样，只是它的数组长度会随着数据的增长，而进行调整，和ArrayList相似
     * 优先队列的插入操作，内部是将数据添加到数组中（如果数组长度不够，则进行数组扩充，然后再插入数组中），然后进上滤操作
     */

    public int[] heapSort(int[] datas) {
	if (datas == null || datas.length <= 1) {// 代码安全性检查
	    return datas;
	}

	buildHeap(datas);// 由初始数组建立堆

	for (int i = datas.length - 1; i > 0; i--) {
	    swap(datas, 0, i);
	    maxHeap(datas, i, 0);
	    // 交换可能破坏堆序性质，调整堆顶元素，使堆保持堆序性质，i为此刻堆的大小，相当于删除堆顶元素
	}
	return datas;
    }

    // 建立初始堆
    private void buildHeap(int[] datas) {
	int initalIndex = (datas.length - 1) / 2;
	for (int i = initalIndex; i >= 0; i--) {// 从堆的中间位置开始
	    maxHeap(datas, datas.length, i);
	}
    }

    // 调整堆，使其具备堆序性质（在二叉堆中，该操作称为下滤）
    private void maxHeap(int[] datas, int heapSize, int location) {
	int left = 2 * location + 1;
	int right = 2 * location + 2;
	int maxIndex = location;// 初始默认location为最大值索引

	if (left < heapSize && datas[location] < datas[left]) {
	    swap(datas, location, left);
	    maxIndex = left;
	}
	if (right < heapSize && datas[location] < datas[right]) {
	    swap(datas, location, right);
	    maxIndex = right;
	}// 经过两个if之后，location中对应着最大值，maxIndex为原最大值对应的下标
	 // 若发生交换，maxIndex中对应着较小值，并由此继续向下调整

	// 一旦发生交换，将沿着交换后的值，继续向下调整堆直至叶子节点或者满足堆序性质
	if (maxIndex != location) {
	    maxHeap(datas, heapSize, maxIndex);
	}
    }

    // 数据交换操作
    private void swap(int[] datas, int i, int j) {
	int tmp = 0;
	tmp = datas[j];
	datas[j] = datas[i];
	datas[i] = tmp;
    }

    public static void main(String[] args) {
	// int[] datas = { 3, 4, 1, 5, 2 };
	int[] datas = { 3, 4, 1, 6, 9, 5, 2, 7, 8, 10 };
	System.out.println("原始数据：");
	for (int i : datas) {
	    System.out.print(i + "\t");
	}

	// int size = 1000000;
	// int[] datas = new int[size];
	// Random rand = new Random();
	// for (int i = 0; i < datas.length; i++) {
	// datas[i] = rand.nextInt(1000);
	// }

	AdvancedSortMethods object = new AdvancedSortMethods();

	int[] quickDatas = Arrays.copyOf(datas, datas.length);
	long curTime = System.currentTimeMillis();
	int[] sortedDatas = object.quickSort(quickDatas, 0, datas.length - 1);
	long curTime1 = System.currentTimeMillis();
	System.out.println();
	System.out.println("快速排序：" + (curTime1 - curTime));
	for (int i : sortedDatas) {
	    System.out.print(i + "\t");
	}

	int[] mergeDatas = Arrays.copyOf(datas, datas.length);
	curTime = System.currentTimeMillis();
	sortedDatas = object.mergeSort(mergeDatas, 0, datas.length - 1);
	curTime1 = System.currentTimeMillis();
	System.out.println();
	System.out.println("归并排序：" + (curTime1 - curTime));
	for (int i : sortedDatas) {
	    System.out.print(i + "\t");
	}

	int[] heapDatas = Arrays.copyOf(datas, datas.length);
	curTime = System.currentTimeMillis();
	sortedDatas = object.heapSort(heapDatas);
	curTime1 = System.currentTimeMillis();
	System.out.println();
	System.out.println("堆排序：" + (curTime1 - curTime));
	for (int i : sortedDatas) {
	    System.out.print(i + "\t");
	}
    }
}
