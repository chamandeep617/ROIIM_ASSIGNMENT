package com.dev.roiim.helper;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.GeneratedValue;
import java.util.UUID;

@Getter
@Setter
public class CustomerJSON {

//    @GeneratedValue(generator = "UUID")
    private String merchantCustomerId;

    private DateOfBirthJSON dateOfBirth = new DateOfBirthJSON();

    private String locale = "en_US";

    private String email;


}
