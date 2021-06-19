package lk.easycarrental.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class DriverDTO {
    private String driverLicenseNumber;
    private String user_Id;
    private String name;
    private String gmail;
    private String address;
    private String contact;
    private String salary;
    private UserDTO user;
}
