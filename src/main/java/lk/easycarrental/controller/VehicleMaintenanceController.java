package lk.easycarrental.controller;

import lk.easycarrental.dto.VehicleDTO;
import lk.easycarrental.dto.VehicleMaintenanceDTO;
import lk.easycarrental.exception.NotFoundException;
import lk.easycarrental.service.VehicleMaintenanceService;
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
@RequestMapping("/api/v1/vehiclemaintenance")
public class VehicleMaintenanceController {

    @Autowired
    private VehicleMaintenanceService service;

    @Autowired
    private VehicleService vehicleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> saveVehicleMaintenance(@RequestBody VehicleMaintenanceDTO dto) {
        System.out.println("Vehicle post method " + dto.toString());
        String validateVehicleData = validateVehicleMaintenanceData(dto);
        if (validateVehicleData.equals("true")) {
            VehicleDTO vehicleDTO = vehicleService.searchVehicle(dto.getVehicle_number());
            dto.setVehicle(vehicleDTO);
            if (service.addVehicleMaintenance(dto)) {
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

    private String validateVehicleMaintenanceData(VehicleMaintenanceDTO dto) {
        if (dto.getMaintenanceID().trim().length() <= 0) {
            return "Vehicle Maintenance MaintenanceID is missing";
        } else if (dto.getMaintenanceTime().trim().length() <= 0) {
            return "Vehicle Maintenance MaintenanceTime is missing";
        } else if (dto.getMaintenanceDate().trim().length() <= 0) {
            return "Vehicle Maintenance MaintenanceDate is missing";
        } else if (dto.getVehicle_number().trim().length() <= 0) {
            return "Vehicle Maintenance Vehicle_number is missing";
        } else if (dto.getStatus().trim().length() <= 0) {
            return "Vehicle Maintenance Status is missing";
        } else {
            return "true";
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> getAllVehicleMaintenance() {
        ArrayList<VehicleMaintenanceDTO> allVehicles = service.getAllVehicleMaintenance();
        return new ResponseEntity<>(new StandardResponse("200", "", allVehicles), HttpStatus.OK);
    }

    @GetMapping(params = {"maintenanceID"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> SearchVehicleMaintenance(@RequestParam String maintenanceID) {
        if (maintenanceID.trim().length() <= 0) {
            throw new NotFoundException("vehicle maintenanceID cannot be empty");
        } else {
            VehicleMaintenanceDTO vehicleDTO = service.searchVehicleMaintenance(maintenanceID);
            return new ResponseEntity<>(new StandardResponse("200", "", vehicleDTO), HttpStatus.OK);
        }
    }


    @DeleteMapping(params = {"maintenanceID"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> deleteVehicleMaintenance(@RequestParam String maintenanceID) {
        if (maintenanceID.trim().length() <= 0) {
            throw new NotFoundException("vehicle maintenanceID cannot be empty");
        } else {
            boolean b = service.deleteVehicleMaintenance(maintenanceID);
            return new ResponseEntity<>(new StandardResponse("200", "Vehicle maintenanceID Delete Successful", maintenanceID + " deleted " + b), HttpStatus.OK);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> updateVehicleMaintenance(@RequestBody VehicleMaintenanceDTO dto) {
        String validate = validateVehicleMaintenanceData(dto);
        if (validate.equals("true")) {
            if (service.updateVehicleMaintenance(dto)) {
                return new ResponseEntity<>(new StandardResponse("200", "Vehicle Maintenance Update Successful", dto), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new StandardResponse("500", "Internal Server Error Custom", dto), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            throw new NotFoundException(validate);
        }
    }
}
