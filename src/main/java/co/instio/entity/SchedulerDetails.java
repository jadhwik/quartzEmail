package co.instio.entity;

import co.instio.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
public class SchedulerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotEmpty
    private String jobName;

    @NotEmpty
    private String jobType;

    @NotEmpty
    private String cronExpression;

    @Enumerated(EnumType.STRING)
    private Status status;
}
