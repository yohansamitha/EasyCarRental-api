package lk.easycarrental.repo;

import lk.easycarrental.entity.VehicleMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleMaintenanceRepo extends JpaRepository<VehicleMaintenance, String> {
}
