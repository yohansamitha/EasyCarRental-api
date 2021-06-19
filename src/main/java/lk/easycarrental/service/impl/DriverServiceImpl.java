package lk.easycarrental.service.impl;

import lk.easycarrental.dto.DriverDTO;
import lk.easycarrental.dto.UserDTO;
import lk.easycarrental.entity.Driver;
import lk.easycarrental.entity.User;
import lk.easycarrental.exception.ValidateException;
import lk.easycarrental.repo.DriverRepo;
import lk.easycarrental.repo.UserRepo;
import lk.easycarrental.service.DriverService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepo driverRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper mapper;


    @Override
    public boolean addDriver(DriverDTO dto) {
        System.out.println("dto : " + dto.toString());
        System.out.println("user : " + dto.getUser().toString());
        if (driverRepo.existsById(dto.getDriverLicenseNumber())) {
            throw new ValidateException("Driver Already Exist");
        }
        userRepo.save(mapper.map(dto.getUser(), User.class));
        driverRepo.save(mapper.map(dto, Driver.class));
        return true;
    }

    @Override
    public boolean deleteDriver(String id) {
        if (!driverRepo.existsById(id)) {
            throw new ValidateException("No Driver for Delete..!");
        }
        driverRepo.deleteById(id);
        return true;
    }

    @Override
    public DriverDTO searchDriver(String id) {
        Optional<Driver> driver = driverRepo.findById(id);
        if (driver.isPresent()) {
            DriverDTO map = mapper.map(driver.get(), DriverDTO.class);
            map.setUser(mapper.map(driver.get().getUser(), UserDTO.class));
            return map;
        }
        throw new ValidateException("There is no driver for this driver license number");
    }

    @Override
    public ArrayList<DriverDTO> getAllDrivers() {
        List<Driver> all = driverRepo.findAll();
        return mapper.map(all, new TypeToken<ArrayList<DriverDTO>>() {
        }.getType());
    }

    @Override
    public boolean updateDriver(DriverDTO dto) {
        if (driverRepo.existsById(dto.getDriverLicenseNumber())) {
            Optional<User> user = userRepo.findById(dto.getUser_Id());
            if (user.isPresent()) {
                dto.setUser(mapper.map(user.get(), UserDTO.class));
                driverRepo.save(mapper.map(dto, Driver.class));
                return true;
            } else {
                throw new ValidateException("There is no User for this Driver provided user id");
            }
        } else {
            throw new ValidateException("There is no driver for this driver license number");
        }
    }
}
