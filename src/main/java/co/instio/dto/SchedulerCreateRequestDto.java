package co.instio.dto;

import co.instio.enums.Status;
import lombok.Data;
import javax.validation.constraints.NotEmpty;


@Data
public class SchedulerCreateRequestDto {


    private Long Id;

    @NotEmpty
    private String jobName;

    @NotEmpty
    private String jobType;

    @NotEmpty
    private String cronExpression;

    private Status status;

}
