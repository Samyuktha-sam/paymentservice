package com.CSS.paymentservice.api.controller;

import com.CSS.paymentservice.business.abstracts.PaymentService;
import com.CSS.paymentservice.business.dto.requests.create.CreatePaymentRequest;
import com.CSS.paymentservice.business.dto.requests.create.CreateRentalPaymentRequest;
import com.CSS.paymentservice.business.dto.requests.update.UpdatePaymentRequest;
import com.CSS.paymentservice.business.dto.responses.create.CreatePaymentResponse;
import com.CSS.paymentservice.business.dto.responses.get.ClientResponse;
import com.CSS.paymentservice.business.dto.responses.get.GetAllPaymentsResponse;
import com.CSS.paymentservice.business.dto.responses.get.GetPaymentResponse;
import com.CSS.paymentservice.business.dto.responses.update.UpdatePaymentResponse;
import com.CSS.paymentservice.stripepayment.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/payments")
public class PaymentsController
{
    private final PaymentService service;
    private final StripeService stripeService;

    @PostMapping("/charge")
    public ResponseEntity< String > chargeCreditCard(@RequestParam("token") String token, @RequestParam("amount") double amount) {
        try {
            Charge charge = stripeService.chargeCreditCard(token, amount);
            return ResponseEntity.ok("Payment successful! Charge ID: " + charge.getId());
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment failed: " + e.getMessage());
        }
    }


    @GetMapping
    public List<GetAllPaymentsResponse> getAll()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable UUID id)
    {
        return service.getById(id);
    }

    @PostMapping("/process-payment")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatePaymentResponse add( @RequestBody CreatePaymentRequest request)
    {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdatePaymentResponse update(@PathVariable UUID id,  @RequestBody UpdatePaymentRequest request)
    {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id)
    {
        service.delete(id);
    }

//    @PostMapping("/process-rental-payment")
//    public ClientResponse processRentalPayment(@RequestBody CreateRentalPaymentRequest request)
//    {
//        return service.processRentalPayment(request);
//    }
}