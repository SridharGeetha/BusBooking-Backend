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

    public StripeResponse checkoutTickets(Double amount, Long qty, Long busId, String startPoint, String destination, Long userId) throws StripeException {
        Stripe.apiKey = secretKey;

        Long amountInCents = Math.round(amount * 100);

        if (amountInCents < 50) {
            throw new IllegalArgumentException("Minimum payment amount must be at least 50 cents in converted currency.");
        }

        // Ensure values are URL-encoded (optional, but safer)
        String successUrl = String.format(
            "http://localhost:5173/ticket?session_id={CHECKOUT_SESSION_ID}&busId=%d&startPoint=%s&destination=%s&qty=%d&userId=%d",
            busId, startPoint, destination, qty, userId
        );

        SessionCreateParams params = SessionCreateParams.builder()
            .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl(successUrl)
            .setCancelUrl("http://localhost:5173/cancel")   
            .addLineItem(
                SessionCreateParams.LineItem.builder()
                    .setQuantity(qty)
                    .setPriceData(
                        SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency("INR") 
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
            .sessionUrl(session.getUrl())
            .build();
    }
}
