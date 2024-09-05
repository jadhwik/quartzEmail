package co.instio.service;

import co.instio.dto.EmailDto;
import co.instio.dto.ScheduleView;
import co.instio.dto.SchedulerCreateRequestDto;

public interface SchedulerService {

    ScheduleView schedule(EmailDto emailDto);

}
