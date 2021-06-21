package lk.easycarrental.dto;

import lk.easycarrental.entity.Booking;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class PaymentDTO {
    private String paymentID;
    private String bookingID;
    private Date payment_date;
    private double full_payment;
    private double standard_payment;
    private double excess_amount;
    private double excess;
    private double damage_waiver;

    private Booking booking;
}
