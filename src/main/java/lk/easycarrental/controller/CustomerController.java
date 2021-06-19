package lk.easycarrental.controller;

import lk.easycarrental.dto.CustomerDTO;
import lk.easycarrental.dto.UserDTO;
import lk.easycarrental.exception.NotFoundException;
import lk.easycarrental.service.CustomerService;
import lk.easycarrental.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> saveCustomer(@RequestBody CustomerDTO dto) {
        System.out.println("customer post method " + dto.toString() + "\n user : ");
        String validateCustomerData = validateCustomerData(dto);
        if (validateCustomerData.equals("true")) {
            String validateUserData = validateUserData(dto.getUserDTO());
            if (validateUserData.equals("true")) {
                customerService.addCustomer(dto);
                return new ResponseEntity<>(new StandardResponse("201", "Done", dto), HttpStatus.CREATED);
            } else {
                throw new NotFoundException(validateUserData);
            }
        } else {
            throw new NotFoundException(validateCustomerData);
        }
    }

    private String validateUserData(UserDTO userDTO) {
        if (userDTO.getUser_Id().trim().length() <= 0) {
            return "User User_Id is missing";
        } else if (userDTO.getEmail().trim().length() <= 0) {
            return "User Email is missing";
        } else if (userDTO.getPassword().trim().length() <= 0) {
            return "User Password is missing";
        } else if (userDTO.getPost().trim().length() <= 0) {
            return "User Post is missing";
        } else {
            return "true";
        }
    }

    private String validateCustomerData(CustomerDTO dto) {
        if (dto.getCustomerNIC().trim().length() <= 0) {
            return "Customer CustomerNIC is missing";
        } else if (dto.getFirstName().trim().length() <= 0) {
            return "Customer First Name is missing";
        } else if (dto.getLastName().trim().length() <= 0) {
            return "Customer Last Name is missing";
        } else if (dto.getAddress().trim().length() <= 0) {
            return "Customer Address is missing";
        } else if (dto.getContact().trim().length() <= 0) {
            return "Customer Contact is missing";
        } else if (dto.getEmail().trim().length() <= 0) {
            return "Customer Email is missing";
        } else if (dto.getNicImagePath().trim().length() <= 0) {
            return "Customer NicImagePath is missing";
        } else if (dto.getLicenseImagePath().trim().length() <= 0) {
            return "Customer LicenseImagePath is missing";
        } else {
            return "true";
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> getAllCustomer() {
        ArrayList<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<>(new StandardResponse("200", "", allCustomers), HttpStatus.OK);
    }

    @GetMapping(params = {"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> SearchCustomer(@RequestParam String id) {
        if (id.trim().length() <= 0) {
            throw new NotFoundException("Customer id cannot be empty");
        } else {
            CustomerDTO customerDTO = customerService.searchCustomer(id);
            return new ResponseEntity<>(new StandardResponse("200", "", customerDTO), HttpStatus.OK);
        }
    }


    @DeleteMapping(params = {"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> deleteCustomer(@RequestParam String id) {
        if (id.trim().length() <= 0) {
            throw new NotFoundException("Customer id cannot be empty");
        } else {
            boolean b = customerService.deleteCustomer(id);
            return new ResponseEntity<>(new StandardResponse("200", "Customer Delete Successful", id + " deleted " + b), HttpStatus.OK);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerDTO dto) {
        String validate = validateCustomerData(dto);
        if (validate.equals("true")) {
            if (dto.getUser_Id().trim().length() > 0) {
                if (customerService.updateCustomer(dto)) {
                    return new ResponseEntity<>(new StandardResponse("200", "Customer Update Successful", dto), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new StandardResponse("500", "Internal Server Error Custom", dto), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                throw new NotFoundException("No User Id is Provided");
            }
        } else {
            throw new NotFoundException(validate);
        }
    }
}
