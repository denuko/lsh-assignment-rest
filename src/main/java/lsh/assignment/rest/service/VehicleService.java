package lsh.assignment.rest.service;

import lsh.assignment.rest.dao.model.Vehicle;
import lsh.assignment.rest.dao.repository.VehicleRepository;
import lsh.assignment.rest.web.dto.VehicleDto;
import lsh.assignment.rest.web.mapper.VehicleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class VehicleService {


    private final VehicleRepository repository;

    private final VehicleMapper mapper;

    public VehicleService(VehicleRepository repository, VehicleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public VehicleDto createVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = mapper.mapToEntity(vehicleDto);
        vehicle.getWheelList()
                .forEach(wheel -> wheel.setVehicle(vehicle));

        repository.save(vehicle);

        return mapper.mapToDto(vehicle);
    }

    public Vehicle findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public List<VehicleDto> findVehicleByLicensePlate(String licensePlate) {
        List<Vehicle> vehicles = repository.findByLicensePlate(licensePlate);

        return mapper.mapToDto(vehicles);
    }

    public VehicleDto updateVehicle(VehicleDto vehicleDto, Long id) {
        Vehicle vehicle = findById(id);
        mapper.mapToExistingEntity(vehicleDto, vehicle);
        vehicle.getWheelList()
                .forEach(wheel -> wheel.setVehicle(vehicle));

        return mapper.mapToDto(vehicle);
    }

    public void deleteVehicle(Long id) {
        Vehicle vehicle = findById(id);
        repository.delete(vehicle);
    }

    public List<VehicleDto> findVehicles() {
        List<Vehicle> vehicles = repository.findAll();

        return mapper.mapToDto(vehicles);
    }
}
