package co.instio.dto;

import lombok.Data;

@Data
public class EmailDto {
    private String email;
    private String subject;
    private String body;
    private String cronExpression;
}
