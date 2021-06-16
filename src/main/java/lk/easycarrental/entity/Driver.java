package lk.easycarrental.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class Driver {
    @Id
    private String driverLicenseNumber;
    private String name;
    private String gmail;
    private String address;
    private String contact;

    @OneToOne(mappedBy = "driver")
    private Salary salary;
}