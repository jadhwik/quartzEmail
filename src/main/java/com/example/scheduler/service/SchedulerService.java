package com.example.scheduler.service;

import com.example.scheduler.dto.ScheduleCreateRequest;

public interface SchedulerService {

    void schedule(ScheduleCreateRequest request);
}
