package com.online.bus.booking.Dto;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {

    private Long bookingId;
    private int statusCode;
    private String error;
    private String message;
    private String name;
    private String source;
    private String destination;
    private Long qty;
    private double fare;
    private String bookingDate;
    private String bookingTime;

}
