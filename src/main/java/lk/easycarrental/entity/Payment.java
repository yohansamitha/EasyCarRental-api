package lk.easycarrental.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class Payment {
    @Id
    private String paymentID;
    private Date payment_date;
    private double full_payment;
    private double standard_payment;
    private double excess_amount;
    private double excess;
    private double damage_waiver;

    @OneToOne
    @JoinColumn(name = "bookingID", referencedColumnName = "bookingID", insertable = false, updatable = false)
    private Booking bookingID;

}
