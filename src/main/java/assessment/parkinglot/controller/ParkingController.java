package assessment.parkinglot.controller;

import assessment.parkinglot.entity.Vehicle;
import assessment.parkinglot.entity.VehicleType;
import assessment.parkinglot.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/parking")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @PostMapping("/park")
    public ResponseEntity<String> parkVehicle(@Valid @RequestBody Vehicle vehicle) {
        if (parkingService.parkVehicle(vehicle)) {
            return ResponseEntity.ok("Vehicle parked -successfully-");
        }
        return ResponseEntity.badRequest().body("No spots for this vehicle type");
    }

    @PostMapping("/leave/{numberPlate}")
    public ResponseEntity<String> leaveVehicle(@PathVariable String numberPlate) {
        if (parkingService.leaveVehicle(numberPlate)) {
            return ResponseEntity.ok("Vehicle left the parking lot");
        }
        return ResponseEntity.badRequest().body("Vehicle not found");
    }

    @GetMapping("/remaining-spots")
    public ResponseEntity<Integer> getRemainingSpots() {
        int remainingSpots = parkingService.getRemainingSpots();
        return ResponseEntity.ok(remainingSpots);
    }

    @GetMapping("/all-spots-taken/{vehicleType}")
    public ResponseEntity<Boolean> allSpotsTaken(@PathVariable VehicleType vehicleType) {
        boolean allTaken = parkingService.allSpotsTaken(vehicleType);
        return ResponseEntity.ok(allTaken);
    }
}

