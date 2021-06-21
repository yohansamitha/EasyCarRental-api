package lk.easycarrental.entity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class Vehicle {
    @Id
    private String vehicleNumber;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle")
    private List<VehicleMaintenance> vehicleMaintenance;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Booking> booking;
}
