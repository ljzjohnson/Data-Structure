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

import java.util.Stack;

/**
 * 剑指offer上有关栈的题目.栈的添加和删除都是O(1)操作，如何利用栈实现从栈中取出最小值/最大值，时间复杂度为O(1)。
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月10日
 */

// 1、定义栈数据结构，实现栈的添加，删除，取栈中最小值都是时间复杂度为O(1)。
// 2、给定两个整数序列，前者表示栈的入栈顺序，判断后者是否栈的一个出栈序列。(假设所有数字不相等)
// 问题2的解决思路基于一点：每次栈弹出的数据一定是栈顶的数据或者还未入栈的数据。
// 解决方法是：依次判断出栈序列中的元素是否是栈顶元素或还未栈的元素。
// 以第一个为例，建立一个辅助栈，将第一个元素和栈顶元素比较，如果是则直接弹出；如果不是，则将该栈顶元素后面序列中的值依次入栈，直至和该元素相等。
// 如果将整个入栈序列全部入栈都没有和该元素相等的，则该序列不可能是出栈序列

public class OfferStack extends Stack<Integer> {

    private Stack<Integer> mStack = new Stack<Integer>();
    private Stack<Integer> pStack = new Stack<Integer>();
    private Integer minValue = Integer.MAX_VALUE;

    @Override
    public Integer push(Integer item) {
	// TODO Auto-generated method stub
	mStack.push(item);
	if (item < minValue) {
	    minValue = item;
	}
	pStack.push(minValue);// 每次入栈，辅助栈值入栈当前最小值
	return item;
    }

    @Override
    public synchronized Integer pop() {
	Integer tmp = mStack.pop();
	pStack.pop();// 出栈时，辅助栈也需要出栈。这样同时更新辅助栈，确保最小可能已经出栈
	return tmp;
    }

    @Override
    public synchronized Integer peek() {
	Integer tmp = mStack.peek();
	return tmp;
    }

    // 利用一个辅助栈，存储栈中的最小值，辅助栈的大小和原栈的大小适中相同
    // 取栈中最小值，时间复杂度是O(1)
    public Integer stackMin() {
	return pStack.pop();
    }

    // 给定两个整数序列，前者表示栈的入栈顺序，判断后者是否栈的一个出栈序列
    public boolean isOPSeri(int[] inputSeri, int[] outputSeri) {
	Stack<Integer> tmpStack = new Stack<Integer>();// 辅助栈，用来存储入栈序列（不是一次性入栈）
	int j = 0, i = 0;

	if (inputSeri == null || outputSeri == null) {// 安全检查
	    System.out.println("输入为空");
	    return false;
	}
	while (j < outputSeri.length) {
	    if (tmpStack.isEmpty()) {
		tmpStack.push(inputSeri[i]);
	    }
	    if (outputSeri[j] == tmpStack.peek()) {// 和栈顶元素进行比较
		tmpStack.pop();
		j++;
		if (tmpStack.isEmpty()) {
		    i++;
		}
	    } else {
		if (++i < inputSeri.length) {
		    tmpStack.push(inputSeri[i]);// 在和栈顶元素不同的情况下，将未入栈的数据相继入栈，以便下一次判断
		} else {// 入栈序列入栈结束仍然没有找到匹配
		    return false;
		}
	    }
	}
	return true;
    }

}
