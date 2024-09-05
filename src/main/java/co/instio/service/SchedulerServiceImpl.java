package co.instio.service;

import co.instio.dto.EmailDto;
import co.instio.dto.ScheduleView;
import co.instio.dto.SchedulerCreateRequestDto;
import co.instio.entity.Email;
import co.instio.entity.SchedulerDetails;
import co.instio.enums.CommonErrorCodeEnum;
import co.instio.exceptions.ServiceException;
import co.instio.job.EmailJobHandler;
import co.instio.job.LogginJobHandler;
import co.instio.mapper.ScheduleMapper;
import co.instio.repo.EmailSchedulerRepo;
import co.instio.repo.SchedulerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerServiceImpl implements SchedulerService{
    private final Scheduler scheduler;
    private final ScheduleMapper mapper;
    private final EmailSchedulerRepo schedulerRepo;


    @Override
    public ScheduleView schedule(EmailDto emailDto){
        Email email = mapper.toEntity(emailDto);
        schedulerRepo.save(email);


        JobDetail jobDetail= emailJobBuilder(email.getEmail(),email.getSubject(), email.getBody());
        Trigger trigger=triggerBuilder(jobDetail, email.getCronExpression());

        try{
            scheduler.scheduleJob(jobDetail,trigger);

        }
        catch (SchedulerException se){
            throw new RuntimeException();
        }
        return mapper.toView(emailDto);
    }



    private JobDetail emailJobBuilder(String email, String subject, String body){
        JobDataMap jobDataMap =new JobDataMap();
        jobDataMap.put("to",email);
        jobDataMap.put("subject",subject);
        jobDataMap.put("body",body);
        return JobBuilder.newJob(EmailJobHandler.class)
                .withIdentity(UUID.randomUUID().toString(),"email-jobs")
                .withDescription("Create jobs for email")
                .storeDurably()
                .usingJobData(jobDataMap)
                .build();


        }




    private Trigger triggerBuilder(JobDetail emailJobBuilder, String cronExpression){

         return TriggerBuilder.newTrigger()
                .forJob(emailJobBuilder)
                .withIdentity(emailJobBuilder.getKey().getName(),"triggers")
                .withDescription("Trigger for"+emailJobBuilder.getKey().getName())
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing())
                .build();


    }


}