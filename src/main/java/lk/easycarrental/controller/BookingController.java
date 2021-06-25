package lk.easycarrental.controller;

import lk.easycarrental.dto.BookingDTO;
import lk.easycarrental.dto.CustomerDTO;
import lk.easycarrental.dto.DriverDTO;
import lk.easycarrental.dto.VehicleDTO;
import lk.easycarrental.exception.NotFoundException;
import lk.easycarrental.service.BookingService;
import lk.easycarrental.service.CustomerService;
import lk.easycarrental.service.DriverService;
import lk.easycarrental.service.VehicleService;
import lk.easycarrental.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private DriverService driverService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> saveBooking(@RequestBody BookingDTO dto) {
        System.out.println(dto + " save booking");
        String validateCustomerData = validateBookingData(dto);
        if (validateCustomerData.equals("true")) {

            CustomerDTO customerDTO = customerService.searchCustomer(dto.getCustomerNIC());
            dto.setCustomer(customerDTO);
            VehicleDTO vehicleDTO = vehicleService.searchVehicle(dto.getVehicle_number());
            dto.setVehicle(vehicleDTO);
            DriverDTO d001 = driverService.searchDriver("D001");
            dto.setDriver(d001);
            System.out.println("booking : " + dto);
            if (bookingService.addBooking(dto)) {
                return new ResponseEntity<>(new StandardResponse("201", "Done", dto), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new StandardResponse("500", "Internal Server Error Custom", dto), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            throw new NotFoundException(validateCustomerData);
        }
    }

    private String validateBookingData(BookingDTO dto) {
        if (dto.getCustomerNIC().trim().length() <= 0) {
            return "booking CustomerNIC is missing";
        } else if (dto.getVehicle_number().trim().length() <= 0) {
            return "booking Vehicle number is missing";
        } else if (dto.getBooking_date().trim().length() <= 0) {
            return "booking Booking date is missing";
        } else if (dto.getDeparture_date().trim().length() <= 0) {
            return "booking Departure date is missing";
        } else if (dto.getArrival_date().trim().length() <= 0) {
            return "booking Arrival date is missing";
        } else if (dto.getBooking_type().trim().length() <= 0) {
            return "booking Booking type is missing";
        } else if (dto.getBooking_status().trim().length() <= 0) {
            return "booking Booking status is missing";
        } else {
            return "true";
        }
    }

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<StandardResponse> getAllCustomer() {
//        ArrayList<CustomerDTO> allCustomers = customerService.getAllCustomers();
//        return new ResponseEntity<>(new StandardResponse("200", "", allCustomers), HttpStatus.OK);
//    }

//    @GetMapping(params = {"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<StandardResponse> SearchCustomer(@RequestParam String id) {
//        if (id.trim().length() <= 0) {
//            throw new NotFoundException("Customer id cannot be empty");
//        } else {
//            CustomerDTO customerDTO = customerService.searchCustomer(id);
//            return new ResponseEntity<>(new StandardResponse("200", "", customerDTO), HttpStatus.OK);
//        }
//    }


//    @DeleteMapping(params = {"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<StandardResponse> deleteCustomer(@RequestParam String id) {
//        if (id.trim().length() <= 0) {
//            throw new NotFoundException("Customer id cannot be empty");
//        } else {
//            boolean b = customerService.deleteCustomer(id);
//            return new ResponseEntity<>(new StandardResponse("200", "Customer Delete Successful", id + " deleted " + b), HttpStatus.OK);
//        }
//    }

//    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerDTO dto) {
//        String validate = validateCustomerData(dto);
//        if (validate.equals("true")) {
//            if (dto.getUser_Id().trim().length() > 0) {
//                if (customerService.updateCustomer(dto)) {
//                    return new ResponseEntity<>(new StandardResponse("200", "Customer Update Successful", dto), HttpStatus.OK);
//                } else {
//                    return new ResponseEntity<>(new StandardResponse("500", "Internal Server Error Custom", dto), HttpStatus.INTERNAL_SERVER_ERROR);
//                }
//            } else {
//                throw new NotFoundException("No User Id is Provided");
//            }
//        } else {
//            throw new NotFoundException(validate);
//        }
//    }
}
