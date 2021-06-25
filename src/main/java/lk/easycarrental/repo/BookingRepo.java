package lk.easycarrental.repo;

import lk.easycarrental.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, String> {
}
