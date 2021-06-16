package lk.easycarrental.service.impl;

import lk.easycarrental.dto.CustomerDTO;
import lk.easycarrental.entity.Customer;
import lk.easycarrental.exception.ValidateException;
import lk.easycarrental.repo.CustomerRepo;
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
    private ModelMapper mapper;


    @Override
    public boolean addCustomer(CustomerDTO dto) {
        if (customerRepo.existsById(dto.getCustomerNIC())) {
            throw new ValidateException("Customer Already Exist");
        }
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
            return mapper.map(customer.get(), CustomerDTO.class);
        }
        return null;
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
            customerRepo.save(mapper.map(dto, Customer.class));
            return true;
        }
        return false;
    }
}
