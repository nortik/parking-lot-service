package assessment.parkinglot.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Car.class, name = "CAR"),
        @JsonSubTypes.Type(value = Motorcycle.class, name = "MOTORCYCLE"),
        @JsonSubTypes.Type(value = Van.class, name = "VAN")
})
public abstract class Vehicle {
    @Id
    @NotBlank(message = "Plate number is mandatory")
    private String numberPlate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Vehicle type is mandatory")
    private VehicleType type;

    public Vehicle() {}

    public Vehicle(String numberPlate, VehicleType type) {
        this.numberPlate = numberPlate;
        this.type = type;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public VehicleType getType() {
        return type;
    }
}
