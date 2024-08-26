package com.example.scheduler.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ScheduleCreateRequest {
    @NotEmpty
    private String jobName;

    private String body;

    @NotEmpty
    private String cronExpression;
}
