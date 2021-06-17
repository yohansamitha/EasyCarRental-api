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
public class Customer {
    @Id
    private String customerNIC;
    private String user_Id;
    private String firstName;
    private String lastName;
    private String address;
    private String contact;
    private String email;
    private String licenceNumber;
    private String nICImagePath;
    private String licenseImagePath;
}
