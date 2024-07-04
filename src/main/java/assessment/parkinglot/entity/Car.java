package assessment.parkinglot.entity;

import jakarta.persistence.Entity;

@Entity
public class Car extends Vehicle {
    public Car(String numberPlate) {
        super(numberPlate, VehicleType.CAR);
    }

    public Car() {
        super(null, VehicleType.CAR);
    }
}