package com.pathfinder.scheduler;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.pathfinder.dao.DeviceLocationQueueProcessDAO;

@Component
public class DeviceLocationQueueProcess implements Job {

	@Autowired
	DeviceLocationQueueProcessDAO deviceLocationQueueProcessDAO;

	public void execute(JobExecutionContext jobExecutionContext)
			throws JobExecutionException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		try {
			if (deviceLocationQueueProcessDAO != null) {
				System.out.println("Processing Queue : " + new Date());
				deviceLocationQueueProcessDAO.processQueue();
				System.out.println("Queue Processed  : " + new Date());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
