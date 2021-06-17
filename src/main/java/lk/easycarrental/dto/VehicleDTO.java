package lk.easycarrental.dto;


import lk.easycarrental.entity.VehicleMaintenance;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class VehicleDTO {
    private String vehicle_number;
    private String brand;
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

    @OneToOne(cascade = CascadeType.ALL, mappedBy ="vehicle")
    VehicleMaintenance vehicleMaintenance;
}
