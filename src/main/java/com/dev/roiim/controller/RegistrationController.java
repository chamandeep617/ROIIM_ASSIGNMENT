package com.dev.roiim.controller;

import com.dev.roiim.dto.CustomerDto;
import com.dev.roiim.dto.ResponseDto;
import com.dev.roiim.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
public class RegistrationController {

    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping("/customer/register")
    public ResponseDto<String> registerCustomer(@Valid @RequestBody CustomerDto customerDto){

        String singleUseCustomerToken = customerService.registerCustomer(customerDto);

        System.out.println(singleUseCustomerToken);
        return new ResponseDto<>(
                HttpStatus.OK,
                singleUseCustomerToken
        );
    }
}
