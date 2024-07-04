package assessment.parkinglot.entity;

import jakarta.persistence.*;

@Entity
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VehicleType spotType;

    private boolean occupied;

    @ManyToOne
    @JoinColumn(name = "vehicle_number_plate")
    private Vehicle vehicle;

    public ParkingSpot() {}

    public ParkingSpot(VehicleType spotType) {
        this.spotType = spotType;
        this.occupied = false;
    }

    public void park(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.occupied = true;
    }

    public void leave() {
        this.vehicle = null;
        this.occupied = false;
    }

    public VehicleType getSpotType() {
        return spotType;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}