package lk.easycarrental.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class Booking {
    @Id
    private String bookingID;
    private String driverLicenseNumber;
    private String customerNIC;
    private String vehicle_number;
    private String booking_date;
    private String departure_date;
    private String arrival_date;
    private String booking_type;
    private String start_mileage;
    private String end_mileage;
    private String booking_status;
}
