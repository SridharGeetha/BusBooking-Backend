package com.online.bus.booking.Dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.online.bus.booking.Entity.Bus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class BusResponse {

    private Long busId;
    private String route;
    private String startingPoint;
    private String endingPoint;
    private Double totalFare;

    private Bus bus;
    private List<Bus> allBus;

    public BusResponse(Bus bus) {
        this.busId = bus.getBusId();
        this.route = bus.getRoute();
        this.startingPoint = bus.getStartingPoint();
        this.endingPoint = bus.getEndingPoint();
        this.totalFare = bus.getTotalFare();
    }
}
