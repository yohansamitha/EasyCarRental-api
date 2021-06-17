package lk.easycarrental.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class CustomerDTO {
    private String customerNIC;
    private String name;
    private String address;
    private String contact;
    private String email;
    private String password;
    private String nicImagePath;
    private String licenseImagePath;

}
