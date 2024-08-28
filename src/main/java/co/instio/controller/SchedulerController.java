package co.instio.controller;
import co.instio.dto.ResponseModel;
import co.instio.dto.ScheduleCreateRequest;
import co.instio.exceptions.ControllerException;
import co.instio.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/schedule")
public class SchedulerController {

    private final SchedulerService schedulerService;

    @PostMapping
    public ResponseModel<?> schedule(@Valid @RequestBody ScheduleCreateRequest request, BindingResult bindingResult) {
            if(bindingResult.hasErrors()){
                throw new ControllerException(bindingResult);
            }
            schedulerService.schedule(request );
            return new ResponseModel<>();
    }

}
