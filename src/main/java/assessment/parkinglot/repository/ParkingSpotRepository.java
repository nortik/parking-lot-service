package assessment.parkinglot.repository;

import assessment.parkinglot.entity.ParkingSpot;
import assessment.parkinglot.entity.Vehicle;
import assessment.parkinglot.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    List<ParkingSpot> findBySpotTypeAndOccupied(VehicleType spotType, boolean occupied);
    List<ParkingSpot> findByOccupied(boolean occupied);
    List<ParkingSpot> findByVehicle(Vehicle vehicle);
}
