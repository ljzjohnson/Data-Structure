/*   
 * Copyright (c) 2010-2020 Founder LZG. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
package linear_table.linear;

import java.util.Arrays;

/**
 * description
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月1日
 */
public class ListTest {

    public static void main(String[] args) {
	int[] datas = { 1, 3, 5, 7, 9, 2, 4, 6, 8 };
	int[] datas1 = { 1, 3, 5, 7, 9 };

	SingleList sList = new SingleList();

	System.out.println("原始数据：" + Arrays.toString(datas));
	for (int i : datas) {
	    sList.add(i);
	}
	System.out.println("单向链表：");
	sList.traverse();
	System.out.println();

	System.out.println("倒数第k个节点值：");
	System.out.println(sList.findKthToTail(sList.headNoe(), 5).data);

	// Node head = sList.inverse(sList.headNoe());
	// System.out.println("改变链表结构-链表反转输出：");
	// while (head.nextNode != null) {
	// head = head.nextNode;
	// System.out.print(head.data + "\t");
	// }
	// System.out.println();

	// System.out.println("合并两个已排序链表：");
	// Node head = sList.merge(sList.headNoe(), sList1.headNoe());
	// while (head.nextNode != null) {
	// head = head.nextNode;
	// System.out.print(head.data + "\t");
	// }
	// System.out.println();

	System.out.println("不改变链表结构-链表反序输出：");
	System.out.println(sList.inverseOuput(sList.headNoe()));

	System.out.println("O(1)时间删除指定节点：");
	System.out.println(sList.delete(sList.headNoe(), sList.subNode()));

	// DoubleList TestList = new DoubleList();
	// for (int i : datas) {
	// TestList.add(i);
	// }
	// System.out.println("双向链表：");
	// TestList.traverse();
    }
}
