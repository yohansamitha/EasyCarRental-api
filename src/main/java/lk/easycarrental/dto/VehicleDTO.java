package lk.easycarrental.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class VehicleDTO {
    private String vehicleNumber;
    private String brand;
    private String description;
    private String frontView;
    private String backView;
    private String rightSideView;
    private String leftSideView;
    private String interior;
    private String numberOfPassengers;
    private String transmissionType;
    private String dailyRate;
    private String dailyFreeMileage;
    private String monthlyRate;
    private String monthlyFreeMileage;
    private String PriceForExtraKM;
    private String color;

//    private VehicleMaintenance vehicleMaintenance;

//    private List<Booking> booking;
}
