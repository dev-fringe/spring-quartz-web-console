package dev.fringe.scheduler.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import dev.fringe.scheduler.model.Scheduling;

@Component
public class JobSyncFactory implements Job {

	private static final Logger LOG = LoggerFactory.getLogger(JobSyncFactory.class);

	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		LOG.info(" execute");
		JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
		Scheduling scheduleJob = (Scheduling) mergedJobDataMap.get(Scheduling.JOB_PARAM_KEY);
//		System.out.println("jobName:" + scheduleJob.getJobName() + "  " + scheduleJob);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
