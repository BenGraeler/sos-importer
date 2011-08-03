package org.n52.sos.importer.model.dateAndTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.n52.sos.importer.interfaces.Combination;

public class DateAndTime extends Combination {
	
	private static final Logger logger = Logger.getLogger(DateAndTime.class);
	
	private Year year;
	private Month month;
	private Day day;
	private Hour hour;
	private Minute minute;
	private Second second;
	private TimeZone timeZone;
	
	public Year getYear() {
		return year;
	}
	public void setYear(Year year) {
		if (getGroup() != null)
			if (year != null)
				logger.info("Add " + year + " to " + this);
			else
				logger.info("Remove " + this.year + " from " + this);
		this.year = year;
	}
	public Month getMonth() {
		return month;
	}
	public void setMonth(Month month) {
		if (getGroup() != null)
			if (month != null)
				logger.info("Add " + month + " to " + this);
			else
				logger.info("Remove " + this.month + " from " + this);
		this.month = month;
	}
	public Day getDay() {
		return day;
	}
	public void setDay(Day day) {
		if (getGroup() != null)
			if (day != null)
				logger.info("Add " + day + " to " + this);
			else
				logger.info("Remove " + this.day + " from " + this);
		this.day = day;
	}
	public Hour getHour() {
		return hour;
	}
	public void setHour(Hour hour) {
		if (getGroup() != null)
			if (hour != null)
				logger.info("Add " + hour + " to " + this);
			else
				logger.info("Remove " + this.hour + " from " + this);
		this.hour = hour;
	}
	public Minute getMinute() {
		return minute;
	}
	public void setMinute(Minute minute) {
		if (getGroup() != null)
			if (minute != null)
				logger.info("Add " + minute + " to " + this);
			else
				logger.info("Remove " + this.minute + " from " + this);
		this.minute = minute;
	}
	public Second getSecond() {
		return second;
	}
	public void setSecond(Second second) {
		if (getGroup() != null)
			if (second != null)
				logger.info("Add " + second + " to " + this);
			else
				logger.info("Remove " + this.second + " from " + this);
		this.second = second;
	}
	public TimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(TimeZone timeZone) {
		if (getGroup() != null)
			if (timeZone != null)
				logger.info("Add " + timeZone + " to " + this);
			else
				logger.info("Remove " + this.timeZone + " from " + this);
		this.timeZone = timeZone;
	}	
	
	@Override
	public Object parse(String s) {
		Date dateTime = null;
		String currentPattern = getPattern();
		SimpleDateFormat formatter =
	           new SimpleDateFormat(currentPattern);      	
        try {
        	dateTime = formatter.parse(s);
		} catch (ParseException e) {
			throw new NumberFormatException();
		}
		return dateTime;
	}
	
	@Override
	public String format(Object o) {
        Date date = (Date)o;		        
    	SimpleDateFormat formatter =
	           new SimpleDateFormat(getPattern());      	
        String dateString = formatter.format(date);

		return dateString;
	}
	@Override
	public String toString() {
		if (getGroup() == null)
			return "Date and Time(" + year + ", " + month + ", "
					+ day + ", " + hour + ", " + minute + ", "
					+ second + ", " + timeZone + ")";
		else 
			return "Date&Time group " + getGroup();
	}
}
