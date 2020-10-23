package com.dev.roiim.helper;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;

@Getter
@Setter
public class TokenJSON {

//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )
    private String merchantRefNum;

    private String[] paymentTypes = {"CARD"};
}
