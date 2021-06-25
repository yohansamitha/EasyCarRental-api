package lk.easycarrental.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class Booking {
    @Id
    private String bookingID;
    private String booking_date;
    private String departure_date;
    private String arrival_date;
    private String booking_type;
    private String start_mileage;
    private String end_mileage;
    private String booking_status;

    @ManyToOne
    @JoinColumn(name = "customerNIC", referencedColumnName = "customerNIC", insertable = false, updatable = false)
    private Customer customerNIC;

    @ManyToOne
    @JoinColumn(name = "driverLicenseNumber", referencedColumnName = "driverLicenseNumber", insertable = false, updatable = false)
    private Driver driverLicenseNumber;

    @ManyToOne
    @JoinColumn(name = "vehicleNumber", referencedColumnName = "vehicleNumber", insertable = false, updatable = false)
    private Vehicle vehicleNumber;

}
