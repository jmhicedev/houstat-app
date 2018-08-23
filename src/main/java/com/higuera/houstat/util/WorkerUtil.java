package com.higuera.houstat.util;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import org.quartz.CronExpression;

public class WorkerUtil {

	/**
	 * Calculate next execution date from cron string and last execution date
	 * */
	public static Date calculateNextExecution(final String cron, final Date lastExecution) throws ParseException {
		
		final CronExpression ce = new CronExpression(cron);
		ce.setTimeZone(TimeZone.getTimeZone("UTC"));
		final Date nextExecutionDate = ce.getNextValidTimeAfter(lastExecution);
		
		return nextExecutionDate;
	}
}
