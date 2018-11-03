package lsh.assignment.rest.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class WheelDto implements Serializable {
    private Long id;

    @NotNull @Positive
    private Long size;

    @NotBlank
    private String manufacturer;

    @NotNull @Positive
    private BigDecimal pressure;
}
