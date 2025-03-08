package com.online.bus.booking.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.bus.booking.Dto.BusResponse;
import com.online.bus.booking.Entity.Bus;
import com.online.bus.booking.Repository.BusRepository;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;
    public String AddBus(Long busId , String route,String startPoint,String destination,Double totalFare){
  
    Bus bus = new Bus();
    bus.setBusId(busId);
    bus.setRoute(route);
    bus.setStartingPoint(startPoint);
    bus.setEndingPoint(destination);
    bus.setTotalFare(totalFare);

    busRepository.save(bus);

    if(bus.getBusId()>0){
        return "Bus Added SuccessFully";
    }else{
        return "Error in Added new Bus";
    }
}

public List<BusResponse> getAllBus(){

    List<Bus> buses = busRepository.findAll();

    return buses.stream().map(bus -> new BusResponse(bus)).collect(Collectors.toList());

}

public BusResponse getBusDetails(Long busId){
    Bus bus = busRepository.findById(busId).orElse(null);

    BusResponse response = new BusResponse();

    response.setBusId(busId);
    response.setRoute(bus.getRoute());
    response.setStartingPoint(bus.getStartingPoint());
    response.setEndingPoint(bus.getEndingPoint());
    response.setTotalFare(bus.getTotalFare());

    return response;

}

public String updateBus(Long busId,BusResponse request){

    Bus busData = busRepository.findById(busId).orElseThrow(()-> new RuntimeException("Bus Not Found"));

    if(request.getBusId() != null){
        busData.setBusId(request.getBusId());
    }
    if(request.getRoute() != null){
        busData.setRoute(request.getRoute());;
    }
    if(request.getStartingPoint() != null){
        busData.setStartingPoint(request.getStartingPoint());
    }
    if(request.getEndingPoint() != null){
        busData.setEndingPoint(request.getEndingPoint());
    }
    if(request.getTotalFare() != null){
        busData.setTotalFare(request.getTotalFare());
    }

    busRepository.save(busData);

    return "Bus Updated SuccessFully with ID :"+busData.getBusId()+" "+busData.getRoute();
}

public String deleteBus(Long busId){
    busRepository.deleteById(busId);
    return "Bus Id with "+busId+" deleted Successfully";
}

}
