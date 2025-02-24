package com.online.bus.booking.Repository;

import java.util.List;
import java.util.Optional;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.bus.booking.Entity.BusStop;

@Repository
public interface BusStopRepository extends JpaRepository<BusStop,Long>{

    // List<BusStop> findBybusIdBusStops(Long busId);
    List<BusStop> findByBus_BusId(Long busId);

    Optional<BusStop> findByStopName(String stopName);
    // List<BusStop> findByBusId(Long id);
    
}
 