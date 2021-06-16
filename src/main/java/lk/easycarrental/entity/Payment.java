package lk.easycarrental.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class Payment {
    @Id
    String paymentID;
    Date payment_date;
    double full_payment;
    double standard_payment;
    double excess_amount;
    double excess;
    double damage_waiver;

}
