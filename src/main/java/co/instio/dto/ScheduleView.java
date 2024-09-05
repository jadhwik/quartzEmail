package co.instio.dto;

import co.instio.enums.Status;
import lombok.Data;

@Data
public class ScheduleView {
    private String email;
    private String subject;
    private String body;
    private String cronExpression;

}
