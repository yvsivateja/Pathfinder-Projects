package com.pathfinder.dto;

import java.util.Date;

public class SchedulerDTO {

	private String jobName;
	private String jobKey;
	private String jobGroup;
	private Date nextFireTime;
	private Date startFireTime;
	private Date previousFireTime;

	public String getJobKey() {
		return jobKey;
	}

	public void setJobKey(String jobKey) {
		this.jobKey = jobKey;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public Date getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public Date getStartFireTime() {
		return startFireTime;
	}

	public void setStartFireTime(Date startFireTime) {
		this.startFireTime = startFireTime;
	}

	public Date getPreviousFireTime() {
		return previousFireTime;
	}

	public void setPreviousFireTime(Date previousFireTime) {
		this.previousFireTime = previousFireTime;
	}

}
