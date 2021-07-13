package com.api.messageria;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entities.Messageria;

@Service
public class SendMessageService {

	@Autowired
    private final RabbitTemplate rabbitTemplate;
	
	public SendMessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void pushMessage(Messageria messageria) {
        
      try{
    	switch(messageria.getSeverit()) {
    		case "critical": this.rabbitTemplate.convertAndSend("reflex.water.critical","critical", messageria.getMessage());
    						 break;
    		case "info": this.rabbitTemplate.convertAndSend("reflex.water.info","info", messageria.getMessage());
    					 break;
    		case "warn": this.rabbitTemplate.convertAndSend("reflex.water.warn","warn", messageria.getMessage());
    				 	 break;
    	}  
      }catch (Exception e){
    	  e.printStackTrace();
      }
      System.out.println("enviou: Severit="+messageria.getSeverit() +" Message=" + messageria.getMessage());
    }
}
