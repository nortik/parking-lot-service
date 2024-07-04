package assessment.parkinglot.service;

import assessment.parkinglot.entity.*;
import assessment.parkinglot.repository.ParkingSpotRepository;
import assessment.parkinglot.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public boolean parkVehicle(Vehicle vehicle) {

        // checking if vehicle exist already in h2
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(vehicle.getNumberPlate());
        if (!existingVehicle.isPresent()) {
            vehicleRepository.save(vehicle);
        }

        // Parking VAN
        if (vehicle instanceof Van) {
            List<ParkingSpot> availableSpots = parkingSpotRepository.findBySpotTypeAndOccupied(VehicleType.VAN, false);
            if (availableSpots.size() >= 3) {
                for (int i = 0; i < 3; i++) {
                    ParkingSpot spot = availableSpots.get(i);
                    spot.park(vehicle);
                    parkingSpotRepository.save(spot);
                }
                return true;
            } else {
                return false; // Not enough space for VAN
            }
        } else {
            List<ParkingSpot> availableSpots = parkingSpotRepository.findBySpotTypeAndOccupied(vehicle.getType(), false);
            if (!availableSpots.isEmpty()) {
                ParkingSpot spot = availableSpots.get(0);
                spot.park(vehicle);
                parkingSpotRepository.save(spot);
                return true;
            } else {
                return false; // Not enough space for MOTO or CAR
            }
        }
    }

    public boolean leaveVehicle(String numberPlate) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(numberPlate);
        if (vehicleOpt.isPresent()) {
            Vehicle vehicle = vehicleOpt.get();
            List<ParkingSpot> occupiedSpots = parkingSpotRepository.findByVehicle(vehicle);
            for (ParkingSpot spot : occupiedSpots) {
                spot.leave();
                parkingSpotRepository.save(spot);
            }
            return true;
        }
        return false;
    }

    public int getRemainingSpots() {
        List<ParkingSpot> availableSpots = parkingSpotRepository.findByOccupied(false);
        return availableSpots.size();
    }

    public boolean allSpotsTaken(VehicleType type) {
        List<ParkingSpot> availableSpots = parkingSpotRepository.findBySpotTypeAndOccupied(type, false);
        return availableSpots.isEmpty();
    }
}