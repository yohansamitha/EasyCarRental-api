package lk.easycarrental.controller;

import lk.easycarrental.dto.DriverDTO;
import lk.easycarrental.dto.UserDTO;
import lk.easycarrental.exception.NotFoundException;
import lk.easycarrental.service.DriverService;
import lk.easycarrental.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/driver")
public class DriverController {

    @Autowired
    DriverService driverService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> saveDriver(@RequestBody DriverDTO dto) {
        System.out.println("driver post method " + dto.toString() + "\n user : ");
        String validateCustomerData = validateCustomerData(dto);
        if (validateCustomerData.equals("true")) {
            String validateUserData = validateUserData(dto.getUser());
            if (validateUserData.equals("true")) {
                driverService.addDriver(dto);
                return new ResponseEntity<>(new StandardResponse("201", "Done", dto), HttpStatus.CREATED);
            } else {
                throw new NotFoundException(validateUserData);
            }
        } else {
            throw new NotFoundException(validateCustomerData);
        }
    }

    private String validateUserData(UserDTO userDTO) {
        if (userDTO == null) {
            return "No user details found";
        } else if (userDTO.getUser_Id().trim().length() <= 0) {
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

    private String validateCustomerData(DriverDTO dto) {
        if (dto.getDriverLicenseNumber().trim().length() <= 0) {
            return "Driver DriverLicenseNumber is missing";
        } else if (dto.getUser_Id().trim().length() <= 0) {
            return "Driver User_Id is missing";
        } else if (dto.getName().trim().length() <= 0) {
            return "Driver Name is missing";
        } else if (dto.getGmail().trim().length() <= 0) {
            return "Driver Gmail is missing";
        } else if (dto.getAddress().trim().length() <= 0) {
            return "Driver Address is missing";
        } else if (dto.getContact().trim().length() <= 0) {
            return "Driver Contact is missing";
        } else if (dto.getSalary().trim().length() <= 0) {
            return "Driver Salary is missing";
        } else {
            return "true";
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> getAllDriver() {
        ArrayList<DriverDTO> allDrivers = driverService.getAllDrivers();
        return new ResponseEntity<>(new StandardResponse("200", "", allDrivers), HttpStatus.OK);
    }

    @GetMapping(params = {"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> SearchDriver(@RequestParam String id) {
        if (id.trim().length() <= 0) {
            throw new NotFoundException("Driver id cannot be empty");
        } else {
            DriverDTO customerDTO = driverService.searchDriver(id);
            return new ResponseEntity<>(new StandardResponse("200", "", customerDTO), HttpStatus.OK);
        }
    }


    @DeleteMapping(params = {"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> deleteDriver(@RequestParam String id) {
        if (id.trim().length() <= 0) {
            throw new NotFoundException("Driver id cannot be empty");
        } else {
            boolean b = driverService.deleteDriver(id);
            return new ResponseEntity<>(new StandardResponse("200", "Driver Delete Successful", id + " deleted " + b), HttpStatus.OK);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> updateDriver(@RequestBody DriverDTO dto) {
        String validate = validateCustomerData(dto);
        if (validate.equals("true")) {
            if (dto.getUser_Id().trim().length() > 0) {
                if (driverService.updateDriver(dto)) {
                    return new ResponseEntity<>(new StandardResponse("200", "Driver Update Successful", dto), HttpStatus.OK);
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
