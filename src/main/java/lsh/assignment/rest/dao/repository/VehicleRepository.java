package lsh.assignment.rest.dao.repository;

import lsh.assignment.rest.dao.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByLicensePlate(String licensePlate);
}
