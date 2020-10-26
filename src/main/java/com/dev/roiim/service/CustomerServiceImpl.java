package com.dev.roiim.service;

import com.dev.roiim.dto.CustomerDto;
import com.dev.roiim.dto.PaymentDto;
import com.dev.roiim.helper.CustomerJSON;
import com.dev.roiim.helper.PaymentJSON.*;
import com.dev.roiim.helper.ResponseCustomerJSON;
import com.dev.roiim.helper.ResponseTokenJSON;
import com.dev.roiim.helper.TokenJSON;
import com.dev.roiim.model.Customer;
import com.dev.roiim.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpHeaders header;


    @Override
    public String registerCustomer(CustomerDto customerDto) {

        Customer customer = customerRepository.findByEmail(customerDto.getEmail());

        if(customer == null) createCustomer(new Customer(),customerDto);

        String str = createSingleUseToken(customerRepository.findByEmail(customerDto.getEmail()));
        return str;

    }


    private void createCustomer(Customer customer,CustomerDto customerDto) {
        String Url = "https://api.test.paysafe.com/paymenthub/v1/customers";
        CustomerJSON customerJSON = new CustomerJSON();

        customerJSON.setEmail(customerDto.getEmail());
        customerJSON.setMerchantCustomerId(customerDto.getEmail()+UUID.randomUUID());

        HttpEntity<CustomerJSON> entity = new HttpEntity<CustomerJSON>(customerJSON,header);
        ResponseCustomerJSON responseCustomerJSON = (ResponseCustomerJSON) restTemplate.postForObject(
                Url, entity, ResponseCustomerJSON.class);

        customer.setEmail(customerDto.getEmail());
        customer.setCustomerId(responseCustomerJSON.getId());
        customerRepository.save(customer);
    }

    private String createSingleUseToken(Customer customer){
        String Url = "https://api.test.paysafe.com/paymenthub/v1/customers/"+customer.getCustomerId()+"/singleusecustomertokens";
        TokenJSON tokenJSON = new TokenJSON();

        tokenJSON.setMerchantRefNum(""+UUID.randomUUID());

        HttpEntity<TokenJSON> entity = new HttpEntity<TokenJSON>(tokenJSON,header);
        ResponseTokenJSON responseTokenJSON = (ResponseTokenJSON) restTemplate.postForObject(
                Url,  entity, ResponseTokenJSON.class);

        return responseTokenJSON.getSingleUseCustomerToken();
    }

    @Override
    public String completePayment(PaymentDto paymentDto) {
        String Url = "https://api.test.paysafe.com/paymenthub/v1/payments";
        PaymentJSON paymentJSON = new PaymentJSON();

        if (paymentDto.getCustomerOperation() != null && paymentDto.getCustomerOperation().equals("ADD")) {
            Customer customer = customerRepository.findByEmail(paymentDto.getEmail());
            customer.setCustomerOperation("ADD");
            paymentJSON.setCustomerId(customerRepository.findByEmail(paymentDto.getEmail()).getCustomerId());
            customerRepository.save(customer);
        }


        paymentJSON.setPaymentHandleToken(paymentDto.getPaymentHandleToken());
        paymentJSON.setAmount(paymentDto.getAmount());
        paymentJSON.setMerchantRefNum(UUID.randomUUID() + "");
        HttpEntity<PaymentJSON> entity = new HttpEntity<PaymentJSON>(paymentJSON, header);

        try {
            ResponsePaymentJSON responsePaymentJSON = (ResponsePaymentJSON) restTemplate.postForObject(
                    Url, entity, ResponsePaymentJSON.class);
            if (responsePaymentJSON.getStatus().equals("COMPLETED")) return responsePaymentJSON.getStatus();
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() != HttpStatus.BAD_REQUEST) {
                return null;
            }
        }
        return null;
    }



}

