package lsh.assignment.rest.web.dto;

import lombok.Getter;
import lombok.Setter;
import lsh.assignment.rest.dao.model.Vehicle;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class WheelDto implements Serializable {
    private Long id;

    private Long size;

    private String manufacturer;

    private BigDecimal pressure;
}
