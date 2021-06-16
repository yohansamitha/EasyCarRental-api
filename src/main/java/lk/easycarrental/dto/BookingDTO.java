package lk.easycarrental.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class BookingDTO {
    private String bookingID;
    private String driver_NIC;
    private String customer_NIC;
    private String booking_date;
    private String departure_date;
    private String vehicle_number;
    private String arrival_date;
    private String booking_type;
    private String start_mileage;
    private String end_mileage;
    private String booking_status;
}