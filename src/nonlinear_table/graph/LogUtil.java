/*   
 * Copyright (c) 2010-2020 Founder LZG. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
package nonlinear_table.graph;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LogUtil {
	static class Time {
		private static final long start = System.currentTimeMillis();

		private long current = 0;

		public Time() {
		}

		public long getTimeDelay() {
			current = System.currentTimeMillis();
			return current - start;
		}

		public long getStart() {
			return start;
		}
	}

	public static void printLog(final String log) {

		String logtemp = "{0} date/time is: {1} \r\nuse time is {2} s {3} ms.";

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);

		Time time = new Time();
		long delay = time.getTimeDelay();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(delay);

		System.err.println(MessageFormat.format(logtemp,
				new Object[]{log, dateString, calendar.get(Calendar.SECOND),
						calendar.get(Calendar.MILLISECOND)}));
	}
}
