package dev.fringe.scheduler.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fringe.scheduler.model.Scheduling;
import dev.fringe.scheduler.quartz.support.SchedulingSupport;
import dev.fringe.scheduler.repositories.SchedulingRepositories;

@Service
public class SchedulingService {

    @Autowired private Scheduler scheduler;
    
    @Autowired private SchedulingRepositories schedulingRepositories;

    public void initScheduleJob() {
        List<Scheduling> scheduleJobList = schedulingRepositories.findAll();
        if (CollectionUtils.isEmpty(scheduleJobList)) {
            return;
        }
        for (Scheduling scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = SchedulingSupport.getCronTrigger(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
            if (cronTrigger == null) {
                SchedulingSupport.createScheduleJob(scheduler, scheduleJob);
            } else {
                SchedulingSupport.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    public Long insert(Scheduling scheduleJobVo) {
        SchedulingSupport.createScheduleJob(scheduler, scheduleJobVo);
        return schedulingRepositories.save(scheduleJobVo).getSchedulingId();
    }

    public Long update(Scheduling scheduleJobVo) {
        SchedulingSupport.updateScheduleJob(scheduler, scheduleJobVo);
        return schedulingRepositories.save(scheduleJobVo).getSchedulingId();
    }

//    public int delUpdate(ScheduleJobVo scheduleJobVo) {
//        Scheduling scheduleJob = scheduleJobVo.getTargetObject(Scheduling.class);
//        SchedulingSupport.deleteScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
//        SchedulingSupport.createScheduleJob(scheduler, scheduleJob);
//        return jdbcDao.update(scheduleJob);
//    }

    public void delete(Long scheduleJobId) {
        schedulingRepositories.deleteById(scheduleJobId);
    }

    public void runOnce(Long scheduleJobId) {
        Scheduling scheduleJob = this.get(scheduleJobId);
        SchedulingSupport.runOnce(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
    }

    public void pauseJob(Long scheduleJobId) {
        Scheduling scheduleJob = this.get(scheduleJobId);
        SchedulingSupport.pauseJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
    }

    public void resumeJob(Long scheduleJobId) {
        Scheduling scheduleJob = this.get(scheduleJobId);
        SchedulingSupport.resumeJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
    }

    public Scheduling get(Long scheduleJobId) {
      return schedulingRepositories.getOne(scheduleJobId);
    }

    public List<Scheduling> queryList(Scheduling scheduleJobVo) {
        List<Scheduling> schedulings = schedulingRepositories.findAll();
        try {
            for (Scheduling vo : schedulings) {

                JobKey jobKey = SchedulingSupport.getJobKey(vo.getJobName(), vo.getJobGroup());
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                if (CollectionUtils.isEmpty(triggers)) {
                    continue;
                }

                Trigger trigger = triggers.iterator().next();
                scheduleJobVo.setJobTrigger(trigger.getKey().getName());

                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                vo.setStatus(triggerState.name());

                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    vo.setCronExpression(cronExpression);
                }
            }
        } catch (SchedulerException e) {
        }
        return schedulings;
    }

    public List<Scheduling> queryExecutingJobList() {
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            List<Scheduling> jobList = new ArrayList<Scheduling>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
            	Scheduling job = new Scheduling();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setJobTrigger(trigger.getKey().getName());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
            return jobList;
        } catch (SchedulerException e) {
            return null;
        }

    }
    
    
    private static final Logger LOG = LoggerFactory.getLogger(SchedulingService.class);

    @PostConstruct
    public void init() {
        if (LOG.isInfoEnabled()) {
            LOG.info("init");
        }
        this.initScheduleJob();
        if (LOG.isInfoEnabled()) {
            LOG.info("end");
        }
    }

}
