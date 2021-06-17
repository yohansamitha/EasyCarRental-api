package lk.easycarrental.controller;

import lk.easycarrental.dto.CustomerDTO;
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
        System.out.println("customer post method " + dto.toString());
        String validate = validateData(dto);
        if (validate.equals("true")) {
            customerService.addCustomer(dto);
            return new ResponseEntity<>(new StandardResponse("201", "Done", dto), HttpStatus.CREATED);
        } else {
            throw new NotFoundException(validate);
        }
    }

    private String validateData(CustomerDTO dto) {
        if (dto.getCustomerNIC().trim().length() <= 0) {
            return "Customer CustomerNIC is missing";
        } else if (dto.getName().trim().length() <= 0) {
            return "Customer Name is missing";
        } else if (dto.getAddress().trim().length() <= 0) {
            return "Customer Address is missing";
        } else if (dto.getContact().trim().length() <= 0) {
            return "Customer Contact is missing";
        } else if (dto.getEmail().trim().length() <= 0) {
            return "Customer Email is missing";
        } else if (dto.getPassword().trim().length() <= 0) {
            return "Customer Password is missing";
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
            return new ResponseEntity<>(new StandardResponse("200", "", id+" deleted "+b), HttpStatus.OK);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerDTO dto) {
        String validate = validateData(dto);
        if (validate.equals("true")) {
            if (customerService.updateCustomer(dto)) {
                return new ResponseEntity<>(new StandardResponse("200", "", dto), HttpStatus.OK);
            } else {
                throw new RuntimeException("Something went in Customer Update");
            }
        } else {
            throw new NotFoundException(validate);
        }

    }
}
