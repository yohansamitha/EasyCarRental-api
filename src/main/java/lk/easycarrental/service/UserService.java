package lk.easycarrental.service;

import lk.easycarrental.dto.UserDTO;

public interface UserService {
    boolean addUser(UserDTO dto);

    UserDTO searchUser(String id);

    boolean deleteUser(String id);

    boolean updateUser(UserDTO dto);
}
