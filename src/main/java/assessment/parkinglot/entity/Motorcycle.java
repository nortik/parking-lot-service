package assessment.parkinglot.entity;

import jakarta.persistence.Entity;

@Entity
public class Motorcycle extends Vehicle {
    public Motorcycle(String numberPlate) {
        super(numberPlate, VehicleType.MOTORCYCLE);
    }

    public Motorcycle() {
        super(null, VehicleType.MOTORCYCLE);
    }
}
