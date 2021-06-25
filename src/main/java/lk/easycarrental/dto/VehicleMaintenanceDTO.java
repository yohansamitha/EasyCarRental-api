package lk.easycarrental.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class VehicleMaintenanceDTO {
    private String maintenanceID;
    private String maintenanceTime;
    private String maintenanceDate;
    private String vehicle_number;
    private String status;

    private VehicleDTO vehicleNumber;
}
