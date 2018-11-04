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

        if (licensePlate != null) {
            if (!licensePlate.matches("^[α-ωΑ-Ω0-9]{6}$")) {
                result.rejectValue("licensePlate", ErrorMessage.INVALID_LICENSE_PLATE.getMessage());
            }

            dto.setLicensePlate(licensePlate.toUpperCase());
        }

        if (wheelList != null) {
            if (!(wheelList.size() == 4 || wheelList.size() == 6)) {
                result.rejectValue("wheelList", ErrorMessage.INVALID_WHEELLIST_SIZE.getMessage());
            }

            for (WheelDto wheelDto : wheelList) {
                if (wheelDto.getPressure() != null) {
                    if (wheelDto.getPressure().compareTo(wheelPressureMax) > 0) {
                        wheelPressureMax = wheelDto.getPressure();
                    }

                    if (wheelDto.getPressure().compareTo(wheelPressureMin) < 0) {
                        wheelPressureMin = wheelDto.getPressure();
                    }
                }
            }

            if (wheelPressureMax.subtract(wheelPressureMin).compareTo(new BigDecimal("0.5")) > 0) {
                result.rejectValue("wheelList", ErrorMessage.INVALID_WHEEL_PRESSURE.getMessage());
            }
        }

        if (dto.getPostalCode() != null) {
            if (!dto.getPostalCode().matches("^[0-9]{5}$")) {
                result.rejectValue("postalCode", ErrorMessage.INVALID_POSTAL_CODE.getMessage());
            }
        }

        if (dto.getVehicleName() != null) {
            if (!dto.getVehicleName().matches("^.{3,33}$")) {
                result.rejectValue("vehicleName", ErrorMessage.INVALID_VEHICLE_NAME.getMessage());
            }
        }
    }
}