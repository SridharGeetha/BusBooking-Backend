package com.online.bus.booking.Entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity 
@Data
public class Bus {

    @Id
    private Long busId;
    private String route;
    private String startingPoint;
    private String endingPoint;
    private Double totalFare;
    private boolean isReturn;

    @OneToMany(mappedBy = "bus",cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "bus",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<BusStop> stops;

    private void reverseRoute(){
        String temp = this.startingPoint;
        this.startingPoint = this.endingPoint;
        this.endingPoint=temp;
        this.isReturn = !this.isReturn;
    }
}
