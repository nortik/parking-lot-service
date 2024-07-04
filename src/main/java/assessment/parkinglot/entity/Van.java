package assessment.parkinglot.entity;

import jakarta.persistence.Entity;

@Entity
public class Van extends Vehicle {
    public Van(String numberPlate) {
        super(numberPlate, VehicleType.VAN);
    }

    public Van() {
        super(null, VehicleType.VAN);
    }
}
