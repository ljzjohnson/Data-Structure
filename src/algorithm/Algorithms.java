/*   
 * Copyright (c) 2010-2020 Founder LZG. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
package algorithm;

/**
 * 算法设计中的几种主要算法思想
 * 
 * @author luzhongguo
 * @version 1.0, 2016年8月8日
 */
public interface Algorithms {

    /**
     * 贪婪算法
     */
    public abstract void greedyALG();

    /**
     * 分治算法
     */
    public abstract void divconALG();

    /**
     * 动态规划算法
     */
    public abstract void dynamicALG();

    /**
     * 分支定界算法
     */
    public abstract void brachboundALG();

    /**
     * 回溯算法
     */
    public abstract void backtrackingALG();
}
