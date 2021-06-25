package lk.easycarrental.service.impl;

import lk.easycarrental.dto.BookingDTO;
import lk.easycarrental.entity.Booking;
import lk.easycarrental.exception.ValidateException;
import lk.easycarrental.repo.BookingRepo;
import lk.easycarrental.repo.CustomerRepo;
import lk.easycarrental.repo.UserRepo;
import lk.easycarrental.repo.VehicleRepo;
import lk.easycarrental.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper mapper;


    @Override
    public boolean addBooking(BookingDTO dto) {
        String bookingID = generateBookingID();
        System.out.println(bookingID);
        dto.setBookingID(bookingID);
        System.out.println(dto.toString() + " dto");
        if (bookingRepo.existsById(dto.getBookingID())) {
            throw new ValidateException("Booking Already Exist");
        } else {
//            vehicleRepo.save(mapper.map(dto.getVehicleNumber(), Vehicle.class));
            Booking map = mapper.map(dto, Booking.class);
            System.out.println(
                    "\n ========================== \n " +
                            map.toString() +
                            "\n ========================== \n "
            );
            Booking save = bookingRepo.save(map);
            System.out.println(
                    "\n ========================== \n " +
                            "save booking " + save +
                            "\n ========================== \n "
            );
            return true;
        }
    }

    private String generateBookingID() {
        long count = bookingRepo.count();
        System.out.println("booking count : " + count);
        if (count == -1) {
            return "B001";
        } else if (count < 9) {
            return "B00" + (count + 1);
        } else if (count < 99) {
            return "B0" + (count + 1);
        } else {
            return "B" + (count + 1);
        }
    }

    @Override
    public boolean deleteBooking(String bookingID) {
        /*if (!customerRepo.existsById(id)) {
            throw new ValidateException("No Customer for Delete..!");
        }
        customerRepo.deleteById(id);
        */
        return false;
    }

    @Override
    public BookingDTO searchBooking(String bookingID) {
        /*Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            CustomerDTO map = mapper.map(customer.get(), CustomerDTO.class);
            map.setUser(mapper.map(customer.get().getUser(), UserDTO.class));
            return map;
        }
        throw new ValidateException("There is no customer for this customer id");*/
        return null;
    }

    @Override
    public ArrayList<BookingDTO> getAllBookings() {
        /*List<Customer> all = customerRepo.findAll();
        return mapper.map(all, new TypeToken<ArrayList<CustomerDTO>>() {
        }.getType());*/
        return null;
    }

    @Override
    public boolean updateBooking(BookingDTO dto) {
        /*if (customerRepo.existsById(dto.getCustomerNIC())) {
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
        }*/
        return false;
    }
}
