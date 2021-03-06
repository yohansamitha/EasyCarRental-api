package lk.easycarrental.controller;

import lk.easycarrental.dto.CustomerDTO;
import lk.easycarrental.dto.UserDTO;
import lk.easycarrental.exception.NotFoundException;
import lk.easycarrental.service.CustomerService;
import lk.easycarrental.service.UserService;
import lk.easycarrental.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> saveUser(@RequestBody UserDTO dto) {
        String validate = validateData(dto);
        if (validate.equals("true")) {
            userService.addUser(dto);
            return new ResponseEntity<>(new StandardResponse("201", "Done", dto), HttpStatus.CREATED);
        } else {
            throw new NotFoundException(validate);
        }
    }

    private String validateData(UserDTO dto) {
        if (dto.getUser_Id().trim().length() <= 0) {
            return "User User_Id is missing";
        } else if (dto.getEmail().trim().length() <= 0) {
            return "User Email is missing";
        } else if (dto.getPassword().trim().length() <= 0) {
            return "User Password is missing";
        } else if (dto.getPost().trim().length() <= 0) {
            return "User Post is missing";
        } else {
            return "true";
        }
    }

    /*@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> getAllUser() {
        ArrayList<UserDTO> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(new StandardResponse("200", "", allUsers), HttpStatus.OK);
    }*/

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> SearchUser(@RequestHeader("email") String email, @RequestHeader("password") String password) {
        System.out.println(email + " username login ");
        System.out.println(password + " password login ");
        if (email.trim().length() <= 0) {
            throw new NotFoundException("userName cannot be empty");
        } else {
            UserDTO userDTO = userService.validateUser(email, password);
            String[] split = userDTO.getUser_Id().split("");
            if (split[0].equals("C")) {
                System.out.println("this is a customer");
                CustomerDTO validCustomer = customerService.validateCustomer(userDTO);
                System.out.println(validCustomer);
                if (validCustomer == null) {
                    return new ResponseEntity<>(new StandardResponse("500", "Internal server Error", null), HttpStatus.INTERNAL_SERVER_ERROR);
                } else {
                    return new ResponseEntity<>(new StandardResponse("200", "Validate Customer", validCustomer), HttpStatus.CREATED);
                }
            } else {
                System.out.println("this is a driver");
                return new ResponseEntity<>(new StandardResponse("500", "driver login is under construction", null), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }


    @DeleteMapping(params = {"user_Id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> deleteUser(@RequestParam String id) {
        if (id.trim().length() <= 0) {
            throw new NotFoundException("User id cannot be empty");
        } else {
            boolean b = userService.deleteUser(id);
            return new ResponseEntity<>(new StandardResponse("200", "", id + " deleted " + b), HttpStatus.OK);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> updateUser(@RequestBody UserDTO dto) {
        String validate = validateData(dto);
        if (validate.equals("true")) {
            if (userService.updateUser(dto)) {
                return new ResponseEntity<>(new StandardResponse("200", "", dto), HttpStatus.OK);
            } else {
                throw new RuntimeException("Something went in User Update");
            }
        } else {
            throw new NotFoundException(validate);
        }

    }
}
