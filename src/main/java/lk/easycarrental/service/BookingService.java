package lk.easycarrental.service;

import lk.easycarrental.dto.BookingDTO;

import java.util.ArrayList;

public interface BookingService {
    boolean addBooking(BookingDTO dto);

    boolean deleteBooking(String bookingID);

    BookingDTO searchBooking(String bookingID);

    ArrayList<BookingDTO> getAllBookings();

    boolean updateBooking(BookingDTO dto);
}
