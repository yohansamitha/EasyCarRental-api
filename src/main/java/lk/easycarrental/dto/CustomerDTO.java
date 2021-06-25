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
    private String user_Id;
    private String firstName;
    private String lastName;
    private String address;
    private String contact;
    private String email;
    private String licenceNumber;
    private String nicImagePath;
    private String licenseImagePath;

    private UserDTO user;

//    private List<BookingDTO> booking;

}
