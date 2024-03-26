package com.CSS.paymentservice.adapter;

import com.CSS.paymentservice.business.abstracts.PostService;
import org.springframework.stereotype.Service;

@Service
public class FakePosServiceAdapter implements PostService
{
    @Override
    public void pay()
    {
        boolean isPaymentSuccessfull = true; //new Random().nextBoolean();
        if(!isPaymentSuccessfull)
        {
            throw new RuntimeException("Payment invalid...");
        }
    }
}