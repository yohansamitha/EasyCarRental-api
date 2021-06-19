package lk.easycarrental.service.impl;

import lk.easycarrental.dto.CustomerDTO;
import lk.easycarrental.dto.UserDTO;
import lk.easycarrental.entity.Customer;
import lk.easycarrental.entity.User;
import lk.easycarrental.exception.ValidateException;
import lk.easycarrental.repo.CustomerRepo;
import lk.easycarrental.repo.UserRepo;
import lk.easycarrental.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper mapper;


    @Override
    public boolean addCustomer(CustomerDTO dto) {
        System.out.println(dto.toString() + " dto");
        System.out.println(dto.getUser().toString() + " user");
        if (customerRepo.existsById(dto.getCustomerNIC())) {
            throw new ValidateException("Customer Already Exist");
        }
        userRepo.save(mapper.map(dto.getUser(), User.class));
        customerRepo.save(mapper.map(dto, Customer.class));
        return true;
    }

    @Override
    public boolean deleteCustomer(String id) {
        if (!customerRepo.existsById(id)) {
            throw new ValidateException("No Customer for Delete..!");
        }
        customerRepo.deleteById(id);
        return true;
    }

    @Override
    public CustomerDTO searchCustomer(String id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            CustomerDTO map = mapper.map(customer.get(), CustomerDTO.class);
            map.setUser(mapper.map(customer.get().getUser(), UserDTO.class));
            return map;
        }
        throw new ValidateException("There is no customer for this customer id");
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() {
        List<Customer> all = customerRepo.findAll();
        return mapper.map(all, new TypeToken<ArrayList<CustomerDTO>>() {
        }.getType());
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) {
        if (customerRepo.existsById(dto.getCustomerNIC())) {
            Optional<User> user = userRepo.findById(dto.getUser_Id());
            if (user.isPresent()) {
                dto.setUser(mapper.map(user.get(), UserDTO.class));
                customerRepo.save(mapper.map(dto, Customer.class));
                return true;
            } else {
                throw new ValidateException("There is no User for this customer provided user id");
            }
        } else {
            throw new ValidateException("There is no customer for this customer id");
        }
    }
}
