package lk.easycarrental.service;

import lk.easycarrental.dto.DriverDTO;

import java.util.ArrayList;

public interface DriverService {

    boolean addDriver(DriverDTO dto);

    ArrayList<DriverDTO> getAllDrivers();

    DriverDTO searchDriver(String id);

    boolean deleteDriver(String id);

    boolean updateDriver(DriverDTO dto);
}
