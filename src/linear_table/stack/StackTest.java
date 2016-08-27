/*   
 * Copyright (c) 2010-2020 Founder LZG. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
package linear_table.stack;

/**
 * 作为栈相关操作的测试
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月9日
 */
public class StackTest {
    public static void main(String[] args) {
	int[] datas = { 3, 4, 1, 6, 9, 5, 2, 7, 8, 10 };
	System.out.println("原始数据：");
	for (int i : datas) {
	    System.out.print(i + "\t");
	}

	// StackArray object = new StackArray();
	// StackLink object = new StackLink();
	// QueuesStack object = new QueuesStack();
	OfferStack object = new OfferStack();
	int[] inputSeri = { 1, 2, 3, 4, 5 };
	int[] outputSeri = { 4, 5, 3, 2, 1 };
	int[] outputSeri1 = { 4, 3, 5, 1, 2 };
	int[] outputSeri2 = { 1, 2, 3, 4, 5 };
	System.out.println();
	System.out.println("判断结果：" + object.isOPSeri(inputSeri, outputSeri2));

	for (int i = 0; i < datas.length; i++) {
	    object.push(datas[i]);
	}
	// System.out.println("当前栈最小值：" + object.stackMin());

	System.out.println();
	System.out.println("出栈：");
	for (int i = 0; i < 5; i++) {
	    System.out.print(object.pop() + "\t");
	}

	System.out.println();
	System.out.println("入栈：");
	for (int i = 0; i < 3; i++) {
	    System.out.print(i + "\t");
	    object.push(i);
	}

	System.out.println();
	System.out.println("当前栈实际大小：" + object.size());
	System.out.println("出栈：");
	while (object.size() > 0) {// 注意这里不能使用object.size()作为for循环的条件判断，因为栈的实际大小是在不断变化的
	    System.out.print(object.pop() + "\t");
	}
    }
}
