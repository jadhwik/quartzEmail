package co.instio.controller;

import co.instio.dto.EmailDto;
import co.instio.dto.ResponseModel;
import co.instio.dto.ScheduleView;
import co.instio.dto.SchedulerCreateRequestDto;
import co.instio.exceptions.ControllerException;
import co.instio.mapper.ScheduleMapper;
import co.instio.rmq.RmqProducer;
import co.instio.service.SchedulerService;
import co.instio.service.SchedulerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final RmqProducer rmqProducer;

    private final  ScheduleMapper mapper;

    @PostMapping
    public ResponseModel<ScheduleView> schedule(@Valid @RequestBody EmailDto emailDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ControllerException(bindingResult);
        }
        ScheduleView view = schedulerService.schedule(emailDto);
        return  ResponseModel.of(view);
    }


}
