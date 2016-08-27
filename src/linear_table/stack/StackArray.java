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
 * 数组实现栈，以数组一个元素为栈底
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月9日
 */
public class StackArray extends Stack {
    private final static int STACK_DEFAULT_SIZE = 10;
    private int[] stack;
    private int stackPointer;

    public StackArray() {
	stack = new int[STACK_DEFAULT_SIZE];
	stackPointer = 0;
    }

    @Override
    public boolean push(int item) {
	if (stackPointer <= STACK_DEFAULT_SIZE - 1) {
	    stack[stackPointer++] = item;
	} else {
	    return false;
	}
	return true;
    }

    @Override
    public int pop() {
	if (stackPointer > 0) {
	    return stack[--stackPointer];
	} else {
	    return Integer.MAX_VALUE;
	}
    }

    @Override
    public int peek() {
	if (stackPointer > 0) {
	    int tmp = stackPointer - 1;
	    return stack[tmp];
	} else {
	    return Integer.MAX_VALUE;
	}

    }

    @Override
    public boolean isEmpty() {
	if (stackPointer != 0) {
	    return true;
	} else {
	    return false;
	}
    }

    @Override
    public int size() {
	return stackPointer;
    }

}
