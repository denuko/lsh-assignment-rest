package lsh.assignment.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.NoArgsConstructor;
import lsh.assignment.rest.dao.enums.ErrorMessage;
import lsh.assignment.rest.dao.model.Wheel;
import lsh.assignment.rest.web.dto.VehicleDto;
import lsh.assignment.rest.web.dto.WheelDto;
import lsh.assignment.rest.web.validator.VehicleValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestApplicationTests {

    @Autowired
    private VehicleValidator validator;

    @Autowired
    private MockMvc mockMvc;

    VehicleDto vehicleDto;
    BeanPropertyBindingResult result;

    @Before
    public void initialize() {
        vehicleDto = new VehicleDto();
        vehicleDto.setLicensePlate("ΑΒΓ123");
        vehicleDto.setPostalCode("43664");
        vehicleDto.setVehicleName("TOYOTA");

        List<WheelDto> wheelListDto = new ArrayList<>();
        vehicleDto.setWheelList(wheelListDto);
        vehicleDto.getWheelList().add(new WheelDto(14L, "OZ", new BigDecimal("43")));
        vehicleDto.getWheelList().add(new WheelDto(14L, "OZ", new BigDecimal("43")));
        vehicleDto.getWheelList().add(new WheelDto(14L, "OZ", new BigDecimal("43")));
        vehicleDto.getWheelList().add(new WheelDto(14L, "OZ", new BigDecimal("43")));
        result = new BeanPropertyBindingResult(vehicleDto, "vehicleDto");
    }

    @Test
    public void testVehicleLicensePlateValid() {
        validator.validate(vehicleDto, result);

        assertFalse(result.hasErrors());
    }

    @Test
    public void testVehicleLicensePlateLettersDigitsOnly() {
        vehicleDto.setLicensePlate("ΑΒΓ23!!!");

        validator.validate(vehicleDto, result);

        assertTrue(result.hasErrors());
        assertEquals("licensePlate", result.getFieldError().getField());
        assertEquals(ErrorMessage.INVALID_LICENSE_PLATE.getMessage(), result.getFieldError().getCode());
    }

    @Test
    public void testVehicleLicensePlateLength() {
        vehicleDto.setLicensePlate("ΑΒΓ23");

        validator.validate(vehicleDto, result);

        assertTrue(result.hasErrors());
        assertEquals("licensePlate", result.getFieldError().getField());
        assertEquals(ErrorMessage.INVALID_LICENSE_PLATE.getMessage(), result.getFieldError().getCode());
    }

    @Test
    public void testVehicleLicensePlateGreekOnly() {
        vehicleDto.setLicensePlate("ABC123");

        validator.validate(vehicleDto, result);

        assertTrue(result.hasErrors());
        assertEquals("licensePlate", result.getFieldError().getField());
        assertEquals(ErrorMessage.INVALID_LICENSE_PLATE.getMessage(), result.getFieldError().getCode());
    }

    @Test
    public void testVehicleLicensePlateUppercase() {
        String licencePlate = "αΒγ123";
        vehicleDto.setLicensePlate(licencePlate);

        validator.validate(vehicleDto, result);

        assertFalse(result.hasErrors());
        assertEquals(vehicleDto.getLicensePlate(), licencePlate.toUpperCase());
    }

    @Test
    public void testVehicleWheelListSize() {
        vehicleDto.getWheelList().add(new WheelDto(14L, "OZ", new BigDecimal("43")));

        validator.validate(vehicleDto, result);

        assertTrue(result.hasErrors());
        assertEquals("wheelList", result.getFieldError().getField());
        assertEquals(ErrorMessage.INVALID_WHEELLIST_SIZE.getMessage(), result.getFieldError().getCode());
    }

    @Test
    public void testVehicleWheelPressure() {
        vehicleDto.getWheelList().get(0).setPressure(new BigDecimal("44"));

        validator.validate(vehicleDto, result);

        assertTrue(result.hasErrors());
        assertEquals("wheelList", result.getFieldError().getField());
        assertEquals(ErrorMessage.INVALID_WHEEL_PRESSURE.getMessage(), result.getFieldError().getCode());
    }

    @Test
    public void testVehiclePostalCode() {
        vehicleDto.setPostalCode("j54o");
        validator.validate(vehicleDto, result);

        assertTrue(result.hasErrors());
        assertEquals("postalCode", result.getFieldError().getField());
        assertEquals(ErrorMessage.INVALID_POSTAL_CODE.getMessage(), result.getFieldError().getCode());
    }

    @Test
    public void testVehicleName() {
        vehicleDto.setVehicleName("");
        validator.validate(vehicleDto, result);

        assertTrue(result.hasErrors());
        assertEquals("vehicleName", result.getFieldError().getField());
        assertEquals(ErrorMessage.INVALID_VEHICLE_NAME.getMessage(), result.getFieldError().getCode());
    }

    @Test
    public void testWheelNullPressure() throws Exception {
        vehicleDto.getWheelList().get(0).setPressure(null);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(vehicleDto);

        this.mockMvc.perform(post("/api/vehicle/post")
                .contentType("application/json")
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
