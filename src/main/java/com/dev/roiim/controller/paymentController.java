package com.dev.roiim.controller;

import com.dev.roiim.dto.CustomerDto;
import com.dev.roiim.dto.PaymentDto;
import com.dev.roiim.dto.ResponseDto;
import com.dev.roiim.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
public class paymentController {

    @Autowired
    CustomerService customerService;


    @PostMapping("/payment")
    public ResponseDto<String> payment(@Valid @RequestBody PaymentDto paymentDto){

        String status = customerService.completePayment(paymentDto);
        return new ResponseDto<>(
                HttpStatus.OK,
                status
        );
    }
}
