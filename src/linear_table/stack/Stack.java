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
 * 栈的相关操作：入栈、出栈、读栈顶元素、判断栈空、遍历栈、栈长。栈的存储结构：顺序存储、链接存储。 Java使用Stack或者LinkedList实现栈
 * 栈的实现相对来说，简单点
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月8日
 */
public abstract class Stack {

    public abstract boolean push(int item);

    public abstract int pop();

    public abstract int peek();

    public abstract boolean isEmpty();

    public abstract int size();
}
