package lsh.assignment.rest.web.validator;

import lsh.assignment.rest.dao.enums.ErrorMessage;
import lsh.assignment.rest.web.dto.VehicleDto;
import lsh.assignment.rest.web.dto.WheelDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.List;

@Component
public class VehicleValidator {

    public void validate(VehicleDto dto, BindingResult result) {
        String licensePlate = dto.getLicensePlate();
        List<WheelDto> wheelList = dto.getWheelList();
        BigDecimal wheelPressureMin = new BigDecimal(Integer.MAX_VALUE);
        BigDecimal wheelPressureMax = BigDecimal.ZERO;

//        - license plate should be 6 characters (letters and digits only), only greek alphabet is valid
        if (!licensePlate.matches("^[α-ωΑ-Ω0-9]{6}$")) {
            result.rejectValue("licensePlate", ErrorMessage.INVALID_LICENSE_PLATE.getMessage());
        }

//        - lowercase characters in license plate should be transformed to upper case
        dto.setLicensePlate(licensePlate.toUpperCase());

//        - the vehicle should have 4 or 6 wheels,
        if (!(wheelList.size() == 4 || wheelList.size() == 6)) {
            result.rejectValue("wheelList", ErrorMessage.INVALID_WHEELLIST_SIZE.getMessage());
        }

        for (WheelDto wheelDto : wheelList) {
            if (wheelDto.getPressure().compareTo(wheelPressureMax) > 0) {
                wheelPressureMax = wheelDto.getPressure();
            }

            if (wheelDto.getPressure().compareTo(wheelPressureMin) < 0) {
                wheelPressureMin = wheelDto.getPressure();
            }
        }

        if (wheelPressureMax.subtract(wheelPressureMin).compareTo(new BigDecimal("0.5")) > 0) {
            result.rejectValue("wheelList", ErrorMessage.INVALID_WHEEL_PRESSURE.getMessage());
        }

        if (!dto.getPostalCode().matches("^[0-9]{5}$")) {
            result.rejectValue("licensePlate", ErrorMessage.INVALID_LICENSE_PLATE.getMessage());
        }

        if (!dto.getVehicleName().matches("^.{3,33}$")) {
            result.rejectValue("licensePlate", ErrorMessage.INVALID_VEHICLE_NAME.getMessage());
        }
    }
}