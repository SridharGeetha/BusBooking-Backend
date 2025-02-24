package com.online.bus.booking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.bus.booking.Dto.BookingResponse;
import com.online.bus.booking.Service.BookingService;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/user/booking-ticket")
    public ResponseEntity<BookingResponse> createBooking(
         @RequestParam("userId") Long userId,
         @RequestParam("busId") Long busId,
         @RequestParam("startPoint") String startPoint,
         @RequestParam("destination") String destination,
         @RequestParam("ticket") Long ticket
    ){
        return ResponseEntity.ok(bookingService.createBooking(userId, busId, startPoint, destination,ticket));
    }

    @GetMapping("/adminuser/get-fare")
    public ResponseEntity<Double> generateFare(
        @RequestParam("source") String source,
        @RequestParam("destination") String destination,
        @RequestParam("ticket") Long ticket){

        return ResponseEntity.ok(bookingService.generateFare(source, destination, ticket));

    }
    
}
