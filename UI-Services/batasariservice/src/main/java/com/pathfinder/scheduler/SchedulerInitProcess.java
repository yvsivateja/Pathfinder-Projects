package com.pathfinder.scheduler;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

@Component
public class SchedulerInitProcess {

	public void initializeJobs() {

		try {
			JobDetail job = JobBuilder.newJob(DeviceLocationQueueProcess.class)
					.withIdentity("DeviceLocationQueueProcess", "group1")
					.build();

			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("DeviceLocationQueueTrigger", "group1")
					.withSchedule(
							SimpleScheduleBuilder.simpleSchedule()
									.withIntervalInSeconds(100).repeatForever())
					.build();

			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();

			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			System.err.println("Error while scheduling : " + e.getMessage());
		}

	}
}
