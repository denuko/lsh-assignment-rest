package lsh.assignment.rest.web.mapper;

import lsh.assignment.rest.dao.model.Vehicle;
import lsh.assignment.rest.dao.model.Wheel;
import lsh.assignment.rest.web.dto.VehicleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {
                WheelMapper.class
        })
public interface VehicleMapper extends MyMapper<Vehicle, VehicleDto> {

}
