package lk.easycarrental.service;

import lk.easycarrental.dto.CustomerDTO;
import lk.easycarrental.dto.UserDTO;

import java.util.ArrayList;

public interface CustomerService {
    boolean addCustomer(CustomerDTO dto);

    boolean deleteCustomer(String id);

    CustomerDTO searchCustomer(String id);

    CustomerDTO validateCustomer(UserDTO user_id);

    ArrayList<CustomerDTO> getAllCustomers();

    boolean updateCustomer(CustomerDTO dto);

}
