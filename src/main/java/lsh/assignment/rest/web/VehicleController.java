package lsh.assignment.rest.web;

import lsh.assignment.rest.service.VehicleService;
import lsh.assignment.rest.web.dto.VehicleDto;
import lsh.assignment.rest.web.validator.VehicleValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/vehicle")
public class VehicleController {

    private final VehicleService service;
    private final VehicleValidator validator;

    public VehicleController(VehicleService service, VehicleValidator validator) {
        this.validator = validator;
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity postVehicle(@Valid @RequestBody VehicleDto vehicleDto, BindingResult result) {
        validator.validate(vehicleDto, result);

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(service.createVehicle(vehicleDto));
    }

    @GetMapping("{licensePlate}")
    public List<VehicleDto> getVehiclesByName(@PathVariable String licensePlate) {

        return service.findVehicleByLicensePlate(licensePlate);
    }

    @PutMapping("{id}")
    public ResponseEntity putVehicle(@Valid @RequestBody VehicleDto vehicleDto, @PathVariable Long id, BindingResult result) {
        validator.validate(vehicleDto, result);

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateVehicle(vehicleDto, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteVehicle(@PathVariable Long id) {
        service.deleteVehicle(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Vehicle id " + id + " deleted successfully.");
    }

    @GetMapping("")
    public List<VehicleDto> getVehicles() {

        return service.findVehicles();
    }
}
