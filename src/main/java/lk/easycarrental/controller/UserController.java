package lk.easycarrental.controller;

import lk.easycarrental.dto.UserDTO;
import lk.easycarrental.exception.NotFoundException;
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

    @GetMapping(params = {"user_Id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> SearchUser(@RequestParam String user_Id) {
        if (user_Id.trim().length() <= 0) {
            throw new NotFoundException("User userName cannot be empty");
        } else {
            UserDTO UserDTO = userService.searchUser(user_Id);
            return new ResponseEntity<>(new StandardResponse("200", "", UserDTO), HttpStatus.OK);
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
