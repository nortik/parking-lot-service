package assessment.parkinglot;

import assessment.parkinglot.entity.ParkingSpot;
import assessment.parkinglot.entity.VehicleType;
import assessment.parkinglot.repository.ParkingSpotRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingLotServiceApplication implements CommandLineRunner {

	@Autowired
	private ParkingSpotRepository parkingSpotRepository;

	public static void main(String[] args) {
		SpringApplication.run(ParkingLotServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<ParkingSpot> spots = new ArrayList<>();

		//MOTORCYCLE spots
		for (int i = 0; i < 5; i++) {
			spots.add(new ParkingSpot(VehicleType.MOTORCYCLE));
		}

		//CAR spots
		for (int i = 0; i < 8; i++) {
			spots.add(new ParkingSpot(VehicleType.CAR));
		}

		//VAN spots
		for (int i = 0; i < 12; i++) {
			spots.add(new ParkingSpot(VehicleType.VAN));
		}

		parkingSpotRepository.saveAll(spots);
	}
}
