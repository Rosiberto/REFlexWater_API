package com.api.messageria;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMessage {

	@Autowired
    private final RabbitTemplate rabbitTemplate;
	
	public SendMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void pushMessage(String severit, String message) {
        
      try{
    	  
    	switch(severit) {
    		case "critical": this.rabbitTemplate.convertAndSend("reflex.water.critical","critical", message);
    						 break;
    		case "info": this.rabbitTemplate.convertAndSend("reflex.water.info","info", message);
    					 break;
    		case "warn": this.rabbitTemplate.convertAndSend("reflex.water.warn","warn", message);
    				 	 break;
    	}  
      }catch (Exception e){
    	  System.out.println( message );
    	  e.printStackTrace();
      }
      System.out.println("enviou: "+ message);
    }
}
