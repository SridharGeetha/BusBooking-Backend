package com.online.bus.booking.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.bus.booking.Dto.BusStopRequestResponse;
import com.online.bus.booking.Entity.Bus;
import com.online.bus.booking.Entity.BusStop;
import com.online.bus.booking.Repository.BusRepository;
import com.online.bus.booking.Repository.BusStopRepository;

@Service
public class BusStopService {

    @Autowired
    private BusStopRepository busStopRepository;

    @Autowired 
    private BusRepository busRepository;

    public String AddNewBusStop( Long busId,String stopName,Double fare){

        Bus bus = busRepository.findById(busId).orElseThrow(()->new RuntimeException("Bus Not Found!"));

        BusStop busStop = new BusStop();

        busStop.setBus(bus);
        busStop.setStopName(stopName);
        busStop.setFareFromStart(fare);

        busStopRepository.save(busStop);


        return "Bus Stop added successfully";
    }

    public Double getFareByStopName(String stopName){
        BusStop busStop = busStopRepository.findByStopName(stopName).orElseThrow(()-> new RuntimeException("Stop not found!"));
        return busStop.getFareFromStart();
    }

    public List<BusStop> getBusStopsByBusId(Long busId) {
        return busStopRepository.findByBus_BusId(busId);
    }

    public String updateBusStop(Long busId,BusStopRequestResponse request){
        BusStop busStop = busStopRepository.findById(busId).orElseThrow(()-> new RuntimeException("bus stop can't found"));
        if(request.getStopName() != null){
            busStop.setStopName(request.getStopName());
        }
        if(request.getFareFromStart() != 0.0){
            busStop.setFareFromStart(request.getFareFromStart());
        }

        busStopRepository.save(busStop);


        return "BusStop update SuccessFully"+busStop.getStopName();
    }
     
}
