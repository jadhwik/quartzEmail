package co.instio.service;

import lombok.RequiredArgsConstructor;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulerUpdating {
    private final Scheduler scheduler;
}
