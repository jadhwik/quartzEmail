package co.instio.mapper;

import co.instio.dto.EmailDto;
import co.instio.entity.Email;
import co.instio.dto.ScheduleView;
import co.instio.dto.SchedulerCreateRequestDto;
import co.instio.entity.SchedulerDetails;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

@Mapper
public interface ScheduleMapper {

   @Mapping(target="status",constant = "DISABLED")
    SchedulerDetails toEntity(SchedulerCreateRequestDto schedulerCreateRequestDto);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    ScheduleView toView(EmailDto emailDto);

    @Mapping(target = "mailId",ignore = true)
    Email toEntity(EmailDto emailDto);
}
