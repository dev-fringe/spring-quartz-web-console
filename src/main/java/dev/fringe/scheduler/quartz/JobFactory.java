package dev.fringe.scheduler.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.fringe.scheduler.model.Scheduling;

@DisallowConcurrentExecution
public class JobFactory implements Job {
	
	private static final Logger LOG = LoggerFactory.getLogger(JobFactory.class);

	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOG.info("JobFactory execute");
		Scheduling scheduleJob = (Scheduling) context.getMergedJobDataMap().get(Scheduling.JOB_PARAM_KEY);
		try {
			Thread.sleep(1000);
			System.out.println("jobName:" + scheduleJob.getJobName() + "  " + scheduleJob);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
