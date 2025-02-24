package com.online.bus.booking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.bus.booking.Dto.StripeResponse;
import com.online.bus.booking.Service.StripeService;
import com.stripe.exception.StripeException;

@RestController
public class PaymentController {
    
    @Autowired
    private StripeService stripeService;

    @PostMapping("/user/payment")
    public StripeResponse checkoutPayment(
        @RequestParam("amount") Double amount , 
        @RequestParam("qty") Long qty
    ) throws StripeException{
        return stripeService.checkoutTickets(amount, qty);
    }
}
