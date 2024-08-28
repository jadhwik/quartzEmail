package co.instio.service;

import co.instio.enums.CommonErrorCodeEnum;
import co.instio.exceptions.ServiceException;
import co.instio.job.EmailJobHandler;
import co.instio.dto.ScheduleCreateRequest;
import co.instio.job.LogginJobHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerServiceImpl implements SchedulerService{
    private final Scheduler scheduler;

    @Override
    public void schedule(ScheduleCreateRequest request){
        JobDetail jobDetail= jobBuilder(request.getJobName(), request.getJobType());
        Trigger trigger=triggerBuilder(jobDetail,request.getCronExpression());
        try{
            scheduler.scheduleJob(jobDetail,  trigger);
        }
        catch (SchedulerException se){
            throw new RuntimeException();
        }
    }

    private JobDetail jobBuilder(String jobName,String jobType ){
        JobDataMap jobDataMap= new JobDataMap();
        jobDataMap.put("jobName",jobName);
        jobDataMap.put("jobTYpe",jobType);
        switch (jobType){
            case "email":
                return JobBuilder.newJob(EmailJobHandler.class)
                        .withIdentity(UUID.randomUUID().toString(),"email-jobs")
                        .withDescription("Create jobs for"+jobType)
                        .storeDurably()
                        .usingJobData(jobDataMap)
                        .build();

            case "logging":
                return JobBuilder.newJob(LogginJobHandler.class)
                        .withIdentity(UUID.randomUUID().toString(),"logging-jobs")
                        .withDescription("Create jobs for"+jobType)
                        .usingJobData(jobDataMap)
                        .storeDurably()
                        .build();

            default:
                throw new ServiceException(CommonErrorCodeEnum.BAD_REQUEST);

        }
    }

    private Trigger triggerBuilder(JobDetail jobDetail, String cronExpression){
        return  TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(),"triggers")
                .withDescription("Trigger for"+jobDetail.getKey().getName())
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing())
                .build();
    }
}