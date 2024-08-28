package co.instio.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ScheduleCreateRequest {
    @NotEmpty
    private String jobName;

    @NotEmpty
    private String jobType;

    @NotEmpty
    private String cronExpression;
}
