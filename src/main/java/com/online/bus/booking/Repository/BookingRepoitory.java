package com.online.bus.booking.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.online.bus.booking.Entity.Booking;


@Repository
public interface BookingRepoitory extends JpaRepository<Booking,Long>{

}
