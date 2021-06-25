package lk.easycarrental.service.impl;

import lk.easycarrental.dto.VehicleDTO;
import lk.easycarrental.entity.Vehicle;
import lk.easycarrental.exception.ValidateException;
import lk.easycarrental.repo.VehicleMaintenanceRepo;
import lk.easycarrental.repo.VehicleRepo;
import lk.easycarrental.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private VehicleMaintenanceRepo vehicleMaintenanceRepo;
    @Autowired
    private ModelMapper mapper;


    @Override
    public boolean addVehicle(VehicleDTO dto) {
        System.out.println(dto.toString() + " dto");
        if (vehicleRepo.existsById(dto.getVehicleNumber())) {
            throw new ValidateException("Vehicle Already Exist");
        }
        vehicleRepo.save(mapper.map(dto, Vehicle.class));
        return true;
    }

    @Override
    public boolean deleteVehicle(String vehicleNumber) {
        if (!vehicleRepo.existsById(vehicleNumber)) {
            throw new ValidateException("No Vehicle for Delete..!");
        }
        vehicleRepo.deleteById(vehicleNumber);
        return true;
    }

    @Override
    public VehicleDTO searchVehicle(String vehicleNumber) {
        Optional<Vehicle> vehicle = vehicleRepo.findById(vehicleNumber);
        if (vehicle.isPresent()) {
            return mapper.map(vehicle.get(), VehicleDTO.class);
        }
        throw new ValidateException("There is no vehicle for this vehicle number");
    }

    @Override
    public ArrayList<VehicleDTO> getAllVehicles() {
        List<Vehicle> all = vehicleRepo.findAll();
        return mapper.map(all, new TypeToken<ArrayList<VehicleDTO>>() {
        }.getType());
    }

    @Override
    public ArrayList<VehicleDTO> getAllVehiclesList(int pagination, int page) {
        Page<Vehicle> all = vehicleRepo.findAll(PageRequest.of(page, pagination));
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        for (Vehicle vehicle : all) {
            System.out.println(vehicle);
            vehicles.add(vehicle);
        }
        return mapper.map(vehicles, new TypeToken<ArrayList<VehicleDTO>>() {
        }.getType());
    }

    @Override
    public boolean updateVehicle(VehicleDTO dto) {
        if (vehicleRepo.existsById(dto.getVehicleNumber())) {
            vehicleRepo.save(mapper.map(dto, Vehicle.class));
            return true;
        } else {
            throw new ValidateException("There is no vehicle for this vehicle number");
        }
    }
}
