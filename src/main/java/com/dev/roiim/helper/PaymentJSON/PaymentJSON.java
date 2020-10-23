package com.dev.roiim.helper.PaymentJSON;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;

@Getter
@Setter
public class PaymentJSON {

//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )
    private String merchantRefNum;

    private String customerId;

    private Long amount;

    private String currencyCode = "USD";

    private String paymentHandleToken;
 }
