package com.CSS.paymentservice.business.abstracts;

import com.CSS.paymentservice.business.dto.requests.create.CreatePaymentRequest;
import com.CSS.paymentservice.business.dto.requests.create.CreateRentalPaymentRequest;
import com.CSS.paymentservice.business.dto.requests.update.UpdatePaymentRequest;
import com.CSS.paymentservice.business.dto.responses.create.CreatePaymentResponse;
import com.CSS.paymentservice.business.dto.responses.get.ClientResponse;
import com.CSS.paymentservice.business.dto.responses.get.GetAllPaymentsResponse;
import com.CSS.paymentservice.business.dto.responses.get.GetPaymentResponse;
import com.CSS.paymentservice.business.dto.responses.update.UpdatePaymentResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService
{
    List<GetAllPaymentsResponse> getAll();
    GetPaymentResponse getById(UUID id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    UpdatePaymentResponse update(UUID id, UpdatePaymentRequest request);
    void delete(UUID id);
    ClientResponse processRentalPayment(CreateRentalPaymentRequest request);

}