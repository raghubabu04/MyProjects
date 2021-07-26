package com.toyota.activemq.consumer;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.toyota.activemq.generated.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JmsConsumer implements MessageListener {

@Autowired
MessageConverter messageConverter;
	
    @Override
    @JmsListener(destination = "${active-mq.topic}")
    public void onMessage(Message message) {
        try{
        	Customer cutomer = (Customer) messageConverter.fromMessage(message);
            
            //do additional processing 
           log.info("Received Message: "+ cutomer.toString());
        } catch(Exception e) {
          log.error("Received Exception : "+ e);
        }

    }
}