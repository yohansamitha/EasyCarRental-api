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
    public ResponseEntity saveCustomer(@RequestBody CustomerDTO dto) {
        if (dto.getCustomerNIC().trim().length() <= 0) {
            throw new NotFoundException("Customer id cannot be empty");
        }
        customerService.addCustomer(dto);
        return new ResponseEntity(new StandardResponse("201", "Done", dto), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllCustomer() {
        ArrayList<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity(new StandardResponse("200", "", allCustomers), HttpStatus.OK);
    }

    @GetMapping(params = {"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity SearchCustomer(@RequestParam String id) {
        CustomerDTO customerDTO = customerService.searchCustomer(id);
        return new ResponseEntity(new StandardResponse("200", "", customerDTO), HttpStatus.OK);
    }


    @DeleteMapping(params = {"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteCustomer(@RequestParam String id) {
        boolean b = customerService.deleteCustomer(id);
        return new ResponseEntity(new StandardResponse("200", "", b), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCustomer(@RequestBody CustomerDTO dto) {
        boolean response = customerService.updateCustomer(dto);
        if (response) {
            return new ResponseEntity(new StandardResponse("200", "", dto), HttpStatus.OK);
        } else {
            throw new RuntimeException("Something went in Customer Customer Update");
        }
    }
}
