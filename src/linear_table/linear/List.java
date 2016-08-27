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

//根据链表中指针的指向：分为单向链表、双向链表、循环链表（链表尾节点指向头结点）、复杂链表（有一个可以指向任意节点的指针）
//主要考察的还是单向链表的相关问题

/*线性表基本操作：
 添加：add()
 删除：remove(data)
 遍历：traverse()
 插入：insert(location,newItem)
 反转：reverse()
 包含：contain(data)
 空判断：isEmpty()
 获取表的长度：length()
 读取表中的数据:get(location)
 替换操作:set(location,data)
 按值查找：indexOf(value)
 */

public abstract class List {
    public int listSize = 0;

    public abstract void add(int data);

    public abstract void traverse();

    public abstract boolean isEmpty();

    public abstract boolean contain(int data);

    public int length() {
	return listSize;
    }
}
