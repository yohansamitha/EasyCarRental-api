package lk.easycarrental.entity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class Vehicle {
    @Id
    private String vehicle_number;
    private String brand;
    private String frontView;
    private String backView;
    private String sideView;
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
