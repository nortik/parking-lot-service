package assessment.parkinglot.controller;

import assessment.parkinglot.entity.Car;
import assessment.parkinglot.entity.Vehicle;
import assessment.parkinglot.entity.VehicleType;
import assessment.parkinglot.service.ParkingService;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ParkingControllerTest {
    @Mock
    private ParkingService parkingService;

    @InjectMocks
    private ParkingController parkingController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(parkingController).build();
    }

    @Test
    public void testParkVehicle() throws Exception {
        Vehicle car = new Car("CAR111");
        when(parkingService.parkVehicle(any(Vehicle.class))).thenReturn(true);

        mockMvc.perform(post("/parking/park")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numberPlate\": \"CAR111\", \"type\": \"CAR\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "Vehicle parked -successfully-"));
    }

    @Test
    public void testLeaveVehicle() throws Exception {
        when(parkingService.leaveVehicle("CAR111")).thenReturn(true);
        mockMvc.perform(post("/parking/leave/CAR111")).andExpect(status().isOk())
                .andExpect(content().string("Vehicle left the parking lot"));
    }

    @Test
    public void testGetRemainingSpots() throws Exception {
        when(parkingService.getRemainingSpots()).thenReturn(15);
        mockMvc.perform(get("/parking/remaining-spots")).andExpect(status().isOk())
                .andExpect(jsonPath("$", is(15)));
    }

    @Test
    public void testAllSpotsTakenForCar() throws Exception {
        when(parkingService.allSpotsTaken(VehicleType.CAR)).thenReturn(false);
        mockMvc.perform(get("/parking/all-spots-taken/CAR")).andExpect(status().isOk())
                .andExpect(jsonPath("$", is(false)));
    }
}

