package com.online.bus.booking.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Table(name = "booking")
@Data
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private String name;
    private String source;
    private String destination;
    private double fare;
    private Long number_of_ticket;
    private LocalDateTime bookingLocalDateTime;
    private String bookingDate;
    private String bookTime;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private MyUser user;

    @ManyToOne
    @JoinColumn(name = "bus_id",nullable = false)
    private Bus bus;
}
