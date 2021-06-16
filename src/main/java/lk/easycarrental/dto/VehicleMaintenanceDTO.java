package lk.easycarrental.dto;


import lk.easycarrental.entity.Vehicle;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class VehicleMaintenanceDTO {
    private String maintenanceID;
    private String maintenanceDate;
    private String status;
    private Vehicle vehicle;
}
