package com.toyota.activemq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.toyota.activemq.generated.model.Customer;
import com.toyota.activemq.producer.JmsProducer;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProduceMessageController {

    @Autowired
    JmsProducer jmsProducer;

    @PostMapping(value="/api/customer")
    public Customer sendMessage(@RequestBody Customer employee){
        jmsProducer.sendMessage(employee);
        return employee;
    }
}