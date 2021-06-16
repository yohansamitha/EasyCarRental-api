package lk.easycarrental.entity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class VehicleMaintenance {
    @Id
    private String maintenanceID;
    private String maintenanceDate;
    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;

}
