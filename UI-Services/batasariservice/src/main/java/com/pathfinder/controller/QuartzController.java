package com.pathfinder.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pathfinder.constants.RealConstants;
import com.pathfinder.dto.SchedulerDTO;
import com.pathfinder.scheduler.SchedulerInitProcess;

@Controller
public class QuartzController {

	@Autowired
	SchedulerInitProcess schedulerInitProcess;

	@PostConstruct
	public void beanPostConstruct() {
		System.out.println("post construct");
		schedulerInitProcess.initializeJobs();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = RealConstants.SCHEDULER_URL, method = RequestMethod.GET)
	public String getSchedulers(Model model) {

		try {
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			List<SchedulerDTO> schedulerDTOs = new ArrayList<SchedulerDTO>();
			for (String groupName : scheduler.getJobGroupNames()) {
				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher
						.jobGroupEquals(groupName))) {
					SchedulerDTO schedulerDTO = new SchedulerDTO();

					schedulerDTO.setJobName(jobKey.getName());
					schedulerDTO.setJobKey(jobKey.getGroup());

					List<Trigger> triggers = (List<Trigger>) scheduler
							.getTriggersOfJob(jobKey);
					schedulerDTO.setNextFireTime(triggers.get(0)
							.getNextFireTime());
					schedulerDTO.setStartFireTime(triggers.get(0)
							.getStartTime());
					schedulerDTO.setPreviousFireTime(triggers.get(0)
							.getPreviousFireTime());

					schedulerDTOs.add(schedulerDTO);
				}
			}
			model.addAttribute("schedulerDTOs", schedulerDTOs);

		} catch (SchedulerException e) {
			System.err.println("Error retrieving schedulers : "
					+ e.getMessage());
		}
		return RealConstants.SCHEDULER_PAGE;

	}

}
