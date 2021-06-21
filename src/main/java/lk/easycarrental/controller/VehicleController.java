package lk.easycarrental.controller;

import lk.easycarrental.dto.VehicleDTO;
import lk.easycarrental.exception.NotFoundException;
import lk.easycarrental.service.VehicleService;
import lk.easycarrental.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> saveVehicle(@RequestBody VehicleDTO dto) {
        System.out.println("Vehicle post method " + dto.toString());
        String validateVehicleData = validateVehicleData(dto);
        if (validateVehicleData.equals("true")) {
            if (vehicleService.addVehicle(dto)) {
                return new ResponseEntity<>(new StandardResponse("201", "Done", dto), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new StandardResponse("500", "Internal Server Error Custom", dto), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new NotFoundException(validateVehicleData);
        }
    }

//    private String validateUserData(UserDTO userDTO) {
//        if (userDTO == null) {
//            return "No user details found";
//        } else if (userDTO.getUser_Id().trim().length() <= 0) {
//            return "User User_Id is missing";
//        } else if (userDTO.getEmail().trim().length() <= 0) {
//            return "User Email is missing";
//        } else if (userDTO.getPassword().trim().length() <= 0) {
//            return "User Password is missing";
//        } else if (userDTO.getPost().trim().length() <= 0) {
//            return "User Post is missing";
//        } else {
//            return "true";
//        }
//    }

    private String validateVehicleData(VehicleDTO dto) {
        if (dto.getVehicleNumber().trim().length() <= 0) {
            return "Vehicle vehicle number is missing";
        } else if (dto.getBrand().trim().length() <= 0) {
            return "Vehicle Brand Name is missing";
        } else if (dto.getNumberOfPassengers().trim().length() <= 0) {
            return "Vehicle NumberOfPassengers is missing";
        } else if (dto.getTransmissionType().trim().length() <= 0) {
            return "Vehicle TransmissionType is missing";
        } else if (dto.getDailyRate().trim().length() <= 0) {
            return "Vehicle DailyRate is missing";
        } else if (dto.getDailyFreeMileage().trim().length() <= 0) {
            return "Vehicle DailyFreeMileage is missing";
        } else if (dto.getMonthlyRate().trim().length() <= 0) {
            return "Vehicle MonthlyRate is missing";
        } else if (dto.getMonthlyFreeMileage().trim().length() <= 0) {
            return "Vehicle MonthlyFreeMileage is missing";
        } else if (dto.getPriceForExtraKM().trim().length() <= 0) {
            return "Vehicle PriceForExtraKM is missing";
        } else if (dto.getColor().trim().length() <= 0) {
            return "Vehicle Color is missing";
        } else {
            return "true";
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> getAllVehicle() {
        ArrayList<VehicleDTO> allVehicles = vehicleService.getAllVehicles();
        return new ResponseEntity<>(new StandardResponse("200", "", allVehicles), HttpStatus.OK);
    }

    @GetMapping(params = {"vehicleNumber"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> SearchVehicle(@RequestParam String vehicleNumber) {
        if (vehicleNumber.trim().length() <= 0) {
            throw new NotFoundException("vehicle number cannot be empty");
        } else {
            VehicleDTO vehicleDTO = vehicleService.searchVehicle(vehicleNumber);
            return new ResponseEntity<>(new StandardResponse("200", "", vehicleDTO), HttpStatus.OK);
        }
    }


    @DeleteMapping(params = {"vehicleNumber"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> deleteVehicle(@RequestParam String vehicleNumber) {
        if (vehicleNumber.trim().length() <= 0) {
            throw new NotFoundException("vehicle number cannot be empty");
        } else {
            boolean b = vehicleService.deleteVehicle(vehicleNumber);
            return new ResponseEntity<>(new StandardResponse("200", "Vehicle Delete Successful", vehicleNumber + " deleted " + b), HttpStatus.OK);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody VehicleDTO dto) {
        String validate = validateVehicleData(dto);
        if (validate.equals("true")) {
            if (vehicleService.updateVehicle(dto)) {
                return new ResponseEntity<>(new StandardResponse("200", "Vehicle Update Successful", dto), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new StandardResponse("500", "Internal Server Error Custom", dto), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new NotFoundException(validate);
        }
    }
}
