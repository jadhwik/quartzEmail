package com.example.scheduler.controller;
import com.example.scheduler.dto.ScheduleCreateRequest;
import com.example.scheduler.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/schedule")
public class SchedulerController {

    private final SchedulerService schedulerService;

    @PostMapping
    public ResponseEntity<String> schedule(@Valid @RequestBody ScheduleCreateRequest request, BindingResult bindingResult) {
//        if(bindingResult.hasErrors()){
//            log.error(String.valueOf(bindingResult));
//            throw new RuntimeException();
//        }
        schedulerService.schedule(request);
        return ResponseEntity.ok("success");
    }

}
