package lk.easycarrental.repo;

import lk.easycarrental.entity.Customer;
import lk.easycarrental.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, String> {
    Optional<Customer> findCustomerByUser(User user_user_Id);
}
