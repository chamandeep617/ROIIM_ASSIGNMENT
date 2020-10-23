package com.dev.roiim.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.RandomAccess;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "customers")
public class Customer {

    @Id
    private String email;

    private String customerId;

    private String CustomerOperation;

}
