package com.online.bus.booking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.bus.booking.Dto.BusResponse;
import com.online.bus.booking.Entity.Bus;
import com.online.bus.booking.Service.BusService;

@RestController
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping("/admin/add-new-bus")
    public ResponseEntity<String> addNewBus(
         @RequestParam("busId") Long busId,
         @RequestParam("route") String route,
         @RequestParam("startPoint") String startPoint,
         @RequestParam("destination") String destination,
         @RequestParam("totalFare") Double totalFare
    ){
        return ResponseEntity.ok(busService.AddBus(busId, route, startPoint, destination, totalFare));
    }

    @GetMapping("/adminuser/get-all-bus") 
    public ResponseEntity<List<Bus>> getAllBus(){
        
        return ResponseEntity.ok(busService.getAllBus());

    }

    @GetMapping("/adminuser/get-bus/{busId}")
    public ResponseEntity<BusResponse> getBusDetails(@PathVariable Long busId){

            return ResponseEntity.ok(busService.getBusDetails(busId));

    }
    
}
