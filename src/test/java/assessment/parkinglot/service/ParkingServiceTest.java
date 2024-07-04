package assessment.parkinglot.service;

import assessment.parkinglot.entity.*;
import assessment.parkinglot.repository.ParkingSpotRepository;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import assessment.parkinglot.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ParkingServiceTest {
    @Mock
    private ParkingSpotRepository parkingSpotRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private ParkingService parkingService;

    private List<ParkingSpot> motorcycleSpots;
    private List<ParkingSpot> carSpots;
    private List<ParkingSpot> vanSpots;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        motorcycleSpots = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            motorcycleSpots.add(new ParkingSpot(VehicleType.MOTORCYCLE));
        }
        carSpots = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            carSpots.add(new ParkingSpot(VehicleType.CAR));
        }
        vanSpots = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            vanSpots.add(new ParkingSpot(VehicleType.VAN));
        }
        when(parkingSpotRepository.findBySpotTypeAndOccupied(VehicleType.MOTORCYCLE, false)).thenReturn(motorcycleSpots);
        when(parkingSpotRepository.findBySpotTypeAndOccupied(VehicleType.CAR, false)).thenReturn(carSpots);
        when(parkingSpotRepository.findBySpotTypeAndOccupied(VehicleType.VAN, false)).thenReturn(vanSpots);
    }

    @Test
    public void testParkVehicleTrue() {
        Vehicle motorcycle = new Motorcycle("MOTO123");
        assertTrue(parkingService.parkVehicle(motorcycle));
        verify(parkingSpotRepository, times(1)).save(any(ParkingSpot.class));
    }

    @Test
    public void testParkVehicleFalse() {
        when(parkingSpotRepository.findBySpotTypeAndOccupied(VehicleType.MOTORCYCLE, false)).thenReturn(new ArrayList<>());
        Vehicle motorcycle = new Motorcycle("MOTO111");
        assertFalse(parkingService.parkVehicle(motorcycle));
        verify(parkingSpotRepository, never()).save(any(ParkingSpot.class));
    }

    @Test
    public void testLeaveVehicleFalse() {
        when(parkingSpotRepository.findByOccupied(true)).thenReturn(new ArrayList<>());

        assertFalse(parkingService.leaveVehicle("CAR111"));
        verify(parkingSpotRepository, never()).save(any(ParkingSpot.class));
    }

    @Test
    public void testGetRemainingSpots() {
        when(parkingSpotRepository.findByOccupied(false)).thenReturn(motorcycleSpots);
        assertEquals(5, parkingService.getRemainingSpots());
    }

    @Test
    public void testallSpotsTaken() {
        assertFalse(parkingService.allSpotsTaken(VehicleType.MOTORCYCLE));
        assertFalse(parkingService.allSpotsTaken(VehicleType.CAR));
        assertFalse(parkingService.allSpotsTaken(VehicleType.VAN));
    }
}
