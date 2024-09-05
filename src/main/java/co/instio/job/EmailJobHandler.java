package co.instio.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailJobHandler implements Job {

    private final JavaMailSender mailSender;

//    @Value("${spring.mail.username}")
//    private String fromMailId;



    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        log.info("Sending email from: {}", fromMailId);

        String to = jobExecutionContext.getJobDetail().getJobDataMap().getString("to");
        String subject = jobExecutionContext.getJobDetail().getJobDataMap().getString("subject");
        String body = jobExecutionContext.getJobDetail().getJobDataMap().getString("body");

        if (to == null || subject == null) {
            log.error("Email jobs cannot have null recipient or subject");
            throw new JobExecutionException("Incomplete email details");
        }

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//            simpleMailMessage.setFrom(fromMailId);
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);
            mailSender.send(simpleMailMessage);
            log.info("Email sent successfully to: {}", to);
        } catch (Exception se) {
            log.error("Unable to send email: {}", se.getMessage(), se);
            throw new JobExecutionException("Unable to send mail", se);
        }
    }
}
