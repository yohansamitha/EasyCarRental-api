package lk.easycarrental.service.impl;

import lk.easycarrental.dto.UserDTO;
import lk.easycarrental.entity.User;
import lk.easycarrental.exception.ValidateException;
import lk.easycarrental.repo.UserRepo;
import lk.easycarrental.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public boolean addUser(UserDTO dto) {
        if (userRepo.existsById(dto.getUser_Id())) {
            throw new ValidateException("user Already Exist");
        }
        userRepo.save(mapper.map(dto, User.class));
        return true;
    }

    @Override
    public boolean deleteUser(String id) {
        if (!userRepo.existsById(id)) {
            throw new ValidateException("No user for Delete..!");
        }
        userRepo.deleteById(id);
        return true;
    }

    @Override
    public UserDTO searchUser(String id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            return mapper.map(user.get(), UserDTO.class);
        }
        throw new ValidateException("There is no user for this user id");
    }

    /*
    @Override
    public ArrayList<userDTO> getAllUsers() {
        List<user> all = userRepo.findAll();
        return mapper.map(all, new TypeToken<ArrayList<userDTO>>() {
        }.getType());
    }*/

    @Override
    public boolean updateUser(UserDTO dto) {
        if (userRepo.existsById(dto.getUser_Id())) {
            userRepo.save(mapper.map(dto, User.class));
            return true;
        }
        return false;
    }
}
