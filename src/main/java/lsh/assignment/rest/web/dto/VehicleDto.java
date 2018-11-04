package lsh.assignment.rest.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class VehicleDto implements Serializable {

    @NotBlank
    private String licensePlate;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String vehicleName;

    @NotNull
    private List<@Valid WheelDto> wheelList;
}
