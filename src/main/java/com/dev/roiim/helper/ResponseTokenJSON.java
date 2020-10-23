package com.dev.roiim.helper;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;

@Getter
@Setter
public class ResponseTokenJSON {

   private String singleUseCustomerToken;

}
