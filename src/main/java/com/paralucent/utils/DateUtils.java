package com.paralucent.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private Date after;
    private Date before;
    private long diff;
    private long day;
    private long hour;
    private long min;
    private long sec;

    public void calDateDiff(Date after, Date before) {
	this.setDiff(after.getTime() - before.getTime());
	this.setDay(this.getDiff() / (24 * 60 * 60 * 1000));
	this.setHour((this.getDiff() / (60 * 60 * 1000) - this.getDay() * 24));
	this.setMin((this.getDiff() / (60 * 1000)) - this.getDay() * 24 * 60 - this.getHour() * 60);
	this.setSec((this.getDiff() / 1000 - this.getDay() * 24 * 60 * 60 - this.getHour() * 60 * 60 - this.getMin() * 60));
    }

    public Date getAfter() {
	return after;
    }

    public void setAfter(Date after) {
	this.after = after;
    }

    public Date getBefore() {
	return before;
    }

    public void setBefore(Date before) {
	this.before = before;
    }

    public long getDiff() {
	return diff;
    }

    public void setDiff(long diff) {
	this.diff = diff;
    }

    public long getDay() {
	return day;
    }

    public void setDay(long day) {
	this.day = day;
    }

    public long getHour() {
	return hour;
    }

    public void setHour(long hours) {
	this.hour = hour;
    }

    public long getMin() {
	return min;
    }

    public void setMin(long min) {
	this.min = min;
    }

    public long getSec() {
	return sec;
    }

    public void setSec(long sec) {
	this.sec = sec;
    }
}
