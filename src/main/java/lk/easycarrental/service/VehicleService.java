package lk.easycarrental.service;

import lk.easycarrental.dto.VehicleDTO;

import java.util.ArrayList;

public interface VehicleService {
    boolean addVehicle(VehicleDTO dto);

    ArrayList<VehicleDTO> getAllVehicles();

    VehicleDTO searchVehicle(String vehicleNumber);

    boolean deleteVehicle(String vehicleNumber);

    boolean updateVehicle(VehicleDTO dto);
}
