package lsh.assignment.rest.web.mapper;

import lsh.assignment.rest.dao.model.Wheel;
import lsh.assignment.rest.web.dto.WheelDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WheelMapper extends MyMapper<Wheel, WheelDto> {
}
