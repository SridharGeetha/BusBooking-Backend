package com.online.bus.booking.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.online.bus.booking.Dto.StripeResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;


@Service
public class StripeService {

    @Value("${stripe.secretKey}")
    private String secretKey;


    public StripeResponse checkoutTickets(Double amount , Long qty) throws StripeException{
         Stripe.apiKey=secretKey;

         Long amountInCents = Math.round(amount * 100); 

         
         if (amountInCents < 50) {
             throw new IllegalArgumentException("Minimum payment amount must be at least 50 cents in converted currency.");
         }

         SessionCreateParams params = SessionCreateParams.builder()
         .setMode(SessionCreateParams.Mode.PAYMENT)
         .setSuccessUrl("http://localhost:8080/success") 
         .setCancelUrl("http://localhost:8080/cancel")   
         .addLineItem(
             SessionCreateParams.LineItem.builder()
                 .setQuantity(qty)
                 .setPriceData(
                     SessionCreateParams.LineItem.PriceData.builder()
                         .setCurrency("inr") 
                         .setUnitAmount(amountInCents)
                         .setProductData(
                             SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                 .setName("Bus Ticket")
                                 .build()
                         )
                         .build()
                 )
                 .build()
         )
         .build();

        
         Session session = Session.create(params);

        
           return StripeResponse.builder()
                        .status("SUCCESS")
                        .message("Payment Success")
                        .sessionId(session.getId())
                        .sessionUrl(session.getUrl()).build();
       

         
    }
    
}
