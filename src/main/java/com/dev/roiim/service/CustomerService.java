package com.dev.roiim.service;

import com.dev.roiim.dto.CustomerDto;
import com.dev.roiim.dto.PaymentDto;

public interface CustomerService {

    public String registerCustomer(CustomerDto customerDto);

    public String completePayment(PaymentDto paymentDto);
}
