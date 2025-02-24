package com.online.bus.booking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.bus.booking.Entity.BusStop;
import com.online.bus.booking.Service.BusStopService;

@RestController
public class BusStopController {

    @Autowired
    private BusStopService busStopService;
    
    @PostMapping("/admin/add-new-bus-stop")
    public ResponseEntity<String> addNewBusStop(
         @RequestParam("busId") Long busId,
         @RequestParam("stopName") String stopName,
         @RequestParam("fare") Double fare
    ){

       busStopService.AddNewBusStop(busId, stopName, fare); 

        return ResponseEntity.ok("Bus Stop Add Successfully!");
    }

    @GetMapping("/adminuser/busStops/{busId}")
    public ResponseEntity<List<BusStop>> getBusStops(@PathVariable Long busId) {
        return ResponseEntity.ok(busStopService.getBusStopsByBusId(busId));
    }

}
