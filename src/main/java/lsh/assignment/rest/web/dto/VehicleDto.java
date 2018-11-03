package lsh.assignment.rest.web.dto;

import lombok.Getter;
import lombok.Setter;
import lsh.assignment.rest.dao.model.Wheel;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class VehicleDto implements Serializable {
    private String licensePlate;

    private String postalCode;

    private String vehicleName;

    private List<WheelDto> wheelList;
}
