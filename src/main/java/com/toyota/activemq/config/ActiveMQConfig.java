package com.toyota.activemq.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.toyota.activemq.generated.model.Customer;

@Configuration
@EnableConfigurationProperties
public class ActiveMQConfig {

    @Value("${active-mq.broker-url}")
    private String brokerUrl;

    

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
      //  jmsTemplate.setMessageConverter(jacksonJmsMessageConverter(createJaxbMarshaller(contextPath, schemaLocation));
        jmsTemplate.setPubSubDomain(true);  // enable for Pub Sub to topic. Not Required for Queue.
        return jmsTemplate;
    }
    
   
    @Bean
    public ConnectionFactory connectionFactory(){
      	ActiveMQConnectionFactory activeMQConnectionFactory  = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
      //  activeMQConnectionFactory.setTrustedP	ackages(Arrays.asList("com.mailshine.springbootstandaloneactivemq"));
        return  activeMQConnectionFactory;
    }
    @Bean
    
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setPubSubDomain(true);
        return factory;
    }
    
     
   
    @Bean
    MessageConverter messageConverter()  throws Exception{
    	 Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
         marshaller.setClassesToBeBound(Customer.class);
         marshaller.afterPropertiesSet();

    	MarshallingMessageConverter converter = new MarshallingMessageConverter();
        converter.setMarshaller(marshaller);
        converter.setUnmarshaller(marshaller);
        converter.setTargetType(MessageType.BYTES);

        return converter;
    }
   /* @Bean
    MarshallingMessageConverter jacksonJmsMessageConverter(Jaxb2Marshaller marshaller) {
        MarshallingMessageConverter converter = new MarshallingMessageConverter();
        converter.setMarshaller(marshaller);
        converter.setUnmarshaller(marshaller);

        return converter;
    } */
}