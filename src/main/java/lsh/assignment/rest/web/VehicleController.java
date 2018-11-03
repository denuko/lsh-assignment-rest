package lsh.assignment.rest.web;

import lsh.assignment.rest.dao.model.Vehicle;
import lsh.assignment.rest.service.VehicleService;
import lsh.assignment.rest.web.dto.VehicleDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vehicle")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @PostMapping("post")
    public ResponseEntity<VehicleDto> postVehicle(@RequestBody VehicleDto vehicleDto) {

        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(service.createVehicle(vehicleDto));
    }

    @GetMapping("get/{licensePlate}")
    public List<VehicleDto> getVehiclesByName(@PathVariable String licensePlate) {

        return service.findVehicleByLicensePlate(licensePlate);
    }

    @PutMapping("put/{id}")
    public ResponseEntity<VehicleDto> putVehicle(@RequestBody VehicleDto vehicleDto, @PathVariable Long id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateVehicle(vehicleDto, id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteVehicle(@PathVariable Long id) {
        service.deleteVehicle(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Vehicle id " + id + " deleted successfully.");
    }

    @GetMapping("get")
    public List<VehicleDto> getVehicles() {

        return service.findVehicles();
    }
}
