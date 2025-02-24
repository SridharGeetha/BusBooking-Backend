package com.online.bus.booking.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.bus.booking.Dto.BookingResponse;
import com.online.bus.booking.Entity.Booking;
import com.online.bus.booking.Entity.Bus;
import com.online.bus.booking.Entity.MyUser;
import com.online.bus.booking.Repository.BookingRepoitory;
import com.online.bus.booking.Repository.BusRepository;
import com.online.bus.booking.Repository.UserRepository;

@Service
public class BookingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private BookingRepoitory bookingRepository; 

    @Autowired
    private BusStopService busStopService;

    public Double generateFare(String source,String destination,Long ticket){

        Double sourceAmount = busStopService.getFareByStopName(source);
        Double destinationAmount = busStopService.getFareByStopName(destination);

        Double fare = (ticket)*(Math.abs(destinationAmount - sourceAmount));

        return fare;
    }

    public BookingResponse createBooking(Long userId,Long busId,String source,String destination,Long ticket){

        BookingResponse response = new BookingResponse();

        MyUser user = userRepository.findById(userId).orElse(null);
        
        Bus bus = busRepository.findById(busId).orElse(null);

        Booking booking = new Booking();

        booking.setUser(user);
        booking.setSource(source);
        booking.setDestination(destination);
        booking.setBookingLocalDateTime(LocalDateTime.now());
        booking.setBookTime(LocalTime.now());
        booking.setBookingDate(LocalDate.now());
        booking.setBus(bus);
        booking.setNumber_of_ticket(ticket);


        // boolean isReturn = bus.isReturn();


        Double fare = generateFare(source, destination, ticket);

        // Double fare = 0.0;

        // if(!isReturn){

        //      fare = destinationAmount - sourceAmount;
        // }else{
        //      fare = sourceAmount - destinationAmount;
        // }

        booking.setFare(fare);



        // fare calculation is not done

        bookingRepository.save(booking);

        response.setBookingId(booking.getBookingId());
        response.setName(user.getUsername());
        response.setSource(source);
        response.setDestination(destination);
        response.setFare(fare);
        response.setBookingDate(booking.getBookingDate());
        response.setBookingTime(booking.getBookTime());
        // response.setBookingLocalDateTime(booking.getBookingLocalDateTime());
        

        return response;
    }

    
}
 