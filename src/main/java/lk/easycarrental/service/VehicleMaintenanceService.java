package lk.easycarrental.service;

import lk.easycarrental.dto.VehicleMaintenanceDTO;

import java.util.ArrayList;

public interface VehicleMaintenanceService {
    boolean addVehicleMaintenance(VehicleMaintenanceDTO dto);

    ArrayList<VehicleMaintenanceDTO> getAllVehicleMaintenance();

    VehicleMaintenanceDTO searchVehicleMaintenance(String vehicleNumber);

    boolean deleteVehicleMaintenance(String vehicleNumber);

    boolean updateVehicleMaintenance(VehicleMaintenanceDTO dto);
}
