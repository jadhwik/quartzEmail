package co.instio.service;

import co.instio.dto.ScheduleCreateRequest;

public interface SchedulerService {

    void schedule(ScheduleCreateRequest request);
}
