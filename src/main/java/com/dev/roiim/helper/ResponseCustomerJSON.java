package com.dev.roiim.helper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@Component
public class ResponseCustomerJSON {

    private String id;

    private String merchantCustomerId;

    private String email;

}
