package com.online.bus.booking.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity 
@Data 
public class BusStop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long stopId;
  private String stopName;
  private double fareFromStart;

  @ManyToOne
  @JoinColumn(name = "bus_id", nullable = false)
  @JsonBackReference
  private Bus bus;

  public BusStop(){
    
  }

  public BusStop(String stopName){
    this.stopName = stopName;
  }
}
