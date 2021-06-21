package lk.easycarrental.service.impl;

import lk.easycarrental.dto.VehicleMaintenanceDTO;
import lk.easycarrental.entity.VehicleMaintenance;
import lk.easycarrental.exception.ValidateException;
import lk.easycarrental.repo.VehicleMaintenanceRepo;
import lk.easycarrental.repo.VehicleRepo;
import lk.easycarrental.service.VehicleMaintenanceService;
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
public class VehicleMaintenanceServiceImpl implements VehicleMaintenanceService {

    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private VehicleMaintenanceRepo vehicleMaintenanceRepo;
    @Autowired
    private ModelMapper mapper;


    //  be update
    @Override
    public boolean addVehicleMaintenance(VehicleMaintenanceDTO dto) {
        System.out.println(dto.toString() + " dto");
        if (vehicleMaintenanceRepo.existsById(dto.getMaintenanceID())) {
            throw new ValidateException("Vehicle maintain Already Exist");
        }
        vehicleMaintenanceRepo.save(mapper.map(dto, VehicleMaintenance.class));
        return true;
    }

    @Override
    public boolean deleteVehicleMaintenance(String maintenanceID) {
        if (!vehicleMaintenanceRepo.existsById(maintenanceID)) {
            throw new ValidateException("No Vehicle maintenance for Delete..!");
        }
        vehicleMaintenanceRepo.deleteById(maintenanceID);
        return true;
    }

    @Override
    public VehicleMaintenanceDTO searchVehicleMaintenance(String maintenanceID) {
        Optional<VehicleMaintenance> vehicle = vehicleMaintenanceRepo.findById(maintenanceID);
        if (vehicle.isPresent()) {
            return mapper.map(vehicle.get(), VehicleMaintenanceDTO.class);
        }
        throw new ValidateException("There is no vehicle maintenance for this vehicle maintenanceID");
    }

    @Override
    public ArrayList<VehicleMaintenanceDTO> getAllVehicleMaintenance() {
        List<VehicleMaintenance> all = vehicleMaintenanceRepo.findAll();
        return mapper.map(all, new TypeToken<ArrayList<VehicleMaintenanceDTO>>() {
        }.getType());
    }

    @Override
    public boolean updateVehicleMaintenance(VehicleMaintenanceDTO dto) {
        if (vehicleMaintenanceRepo.existsById(dto.getMaintenanceID())) {
            vehicleMaintenanceRepo.save(mapper.map(dto, VehicleMaintenance.class));
            return true;
        } else {
            throw new ValidateException("There is no vehicle maintenance for this vehicle maintenance");
        }
    }
}
