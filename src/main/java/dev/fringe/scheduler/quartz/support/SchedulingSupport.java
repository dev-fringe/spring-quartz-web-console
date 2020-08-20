package dev.fringe.scheduler.quartz.support;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import dev.fringe.scheduler.model.Scheduling;
import dev.fringe.scheduler.quartz.JobFactory;
import dev.fringe.scheduler.quartz.JobSyncFactory;

public class SchedulingSupport {

    public static TriggerKey getTriggerKey(String jobName, String jobGroup) {
        return TriggerKey.triggerKey(jobName, jobGroup);
    }

    public static CronTrigger getCronTrigger(Scheduler scheduler, String jobName, String jobGroup) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            return (CronTrigger) scheduler.getTrigger(triggerKey);
        } catch (SchedulerException e) {
            throw new RuntimeException("获取定时任务CronTrigger出现异常");
        }
    }

    public static void createScheduleJob(Scheduler scheduler, Scheduling scheduleJob) {
        createScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup(),scheduleJob.getCronExpression(), scheduleJob.getIsSync(), scheduleJob);
    }

    public static void createScheduleJob(Scheduler scheduler, String jobName, String jobGroup,
                                         String cronExpression, boolean isSync, Object param) {
        Class<? extends Job> jobClass = isSync ? JobSyncFactory.class : JobFactory.class;
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroup).build();
        jobDetail.getJobDataMap().put(Scheduling.JOB_PARAM_KEY, param);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup)
            .withSchedule(scheduleBuilder).build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException("创建定时任务失败");
        }
    }

    public static void runOnce(Scheduler scheduler, String jobName, String jobGroup) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            throw new RuntimeException("运行一次定时任务失败");
        }
    }

    public static void pauseJob(Scheduler scheduler, String jobName, String jobGroup) {

        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            throw new RuntimeException("暂停定时任务失败");
        }
    }

    public static void resumeJob(Scheduler scheduler, String jobName, String jobGroup) {

        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            throw new RuntimeException("暂停定时任务失败");
        }
    }

    public static JobKey getJobKey(String jobName, String jobGroup) {
        return JobKey.jobKey(jobName, jobGroup);
    }

    public static void updateScheduleJob(Scheduler scheduler, Scheduling scheduleJob) {
        updateScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup(),
        scheduleJob.getCronExpression(), scheduleJob.getIsSync(), scheduleJob);
    }

    public static void updateScheduleJob(Scheduler scheduler, String jobName, String jobGroup, String cronExpression, boolean isSync, Object param) {
        try {

            TriggerKey triggerKey = SchedulingSupport.getTriggerKey(jobName, jobGroup);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder)
                .build();
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException("更新定时任务失败");
        }
    }

    public static void deleteScheduleJob(Scheduler scheduler, String jobName, String jobGroup) {
        try {
            scheduler.deleteJob(getJobKey(jobName, jobGroup));
        } catch (SchedulerException e) {
            throw new RuntimeException("删除定时任务失败");
        }
    }
}
