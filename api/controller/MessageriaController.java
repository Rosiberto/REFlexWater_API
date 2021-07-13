package com.api.controller;

import java.time.Duration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.entities.Messageria;
import com.api.messageria.MessageListenerContainerFactory;
import com.api.messageria.SendMessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/api/v2")
@Api(value="REFlex Water API")
public class MessageriaController {

	@Autowired
	private SendMessageService sendMessage;

	@Autowired
	private AmqpAdmin amqpAdmin;

	@Autowired
	private MessageListenerContainerFactory messageListenerContainerFactory;

	private static Logger log = LoggerFactory.getLogger(MessageriaController.class);


	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/notify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Serviço de Mensageria. Deve ser enviado o grau do alerta e a mensagem.")
	public void postMessage(@RequestBody Messageria messageria){	
		
		log.info("[I50] Message sent: ", messageria);
		sendMessage.pushMessage(messageria);	       
	}


	@PostConstruct
	public void setupQueueDestinationsCritical() {

		log.info("[I54] Creating directExchange: name={}, routingKey={}", "reflex.water.critical", "critical");

		Exchange ex = ExchangeBuilder.directExchange("reflex.water.critical")
				.durable(true)
				.build();

		amqpAdmin.declareExchange(ex);

		Queue q = QueueBuilder.durable("queue.critical").build();

		amqpAdmin.declareQueue(q);

		Binding b = BindingBuilder.bind(q)
				.to(ex)                    
				.with("critical")
				.noargs();

		amqpAdmin.declareBinding(b);

		log.info("[I70] Binding successfully created.");
	}

	
	@GetMapping(value = "/queue/critical", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ApiIgnore
	public Flux<?> receiveMessagesFromQueueCritical() {

		MessageListenerContainer mlc = messageListenerContainerFactory.createMessageListenerContainer("queue.critical");

		Flux<String> f = Flux.<String> create(emitter -> {

			log.info("[I168] Adding listener, queue={}","queue.critical");
			mlc.setupMessageListener((MessageListener) m -> {

				String qname = "queue.critical";

				log.info("[I137] Message received, queue={}", qname);

				if (emitter.isCancelled()) {
					log.info("[I166] cancelled, queue={}", qname);
					mlc.stop();
					return;
				}

				String payload = new String(m.getBody());
				emitter.next(payload);

				log.info("[I176] Message sent to client, queue={}", qname);		


			});

			emitter.onRequest(v -> {
				log.info("[I171] Starting container, queue={}", "queue.critical");
				mlc.start();
			});

			emitter.onDispose(() -> {
				log.info("[I176] onDispose: queue={}", "queue.critical");
				mlc.stop();
			});

			log.info("[I171] Container started, queue={}", "queue.critical");

		});

		return Flux.interval(Duration.ofSeconds(35))
				.map(v -> {
					log.info("[I209] sending No new message...");
					return "No new message";
				})
				.mergeWith(f);            
	}   

	@PostConstruct
	public void setupQueueDestinationsInfo() {

		log.info("[I55] Creating directExchange: name={}, routingKey={}", "reflex.water.info", "info");

		Exchange ex = ExchangeBuilder.directExchange("reflex.water.info")
				.durable(true)
				.build();

		amqpAdmin.declareExchange(ex);

		Queue q = QueueBuilder.durable("queue.info").build();

		amqpAdmin.declareQueue(q);

		Binding b = BindingBuilder.bind(q)
				.to(ex)                    
				.with("info")
				.noargs();

		amqpAdmin.declareBinding(b);

		log.info("[I70] Binding successfully created.");
	}

	@GetMapping(value = "/queue/info", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ApiIgnore
	public Flux<?> receiveMessagesFromQueueInfo() {

		MessageListenerContainer mlc = messageListenerContainerFactory.createMessageListenerContainer("queue.info");

		Flux<String> f = Flux.<String> create(emitter -> {

			log.info("[I168] Adding listener, queue={}","queue.info");
			mlc.setupMessageListener((MessageListener) m -> {

				String qname = "queue.info";

				log.info("[I137] Message received, queue={}", qname);

				if (emitter.isCancelled()) {
					log.info("[I166] cancelled, queue={}", qname);
					mlc.stop();
					return;
				}

				String payload = new String(m.getBody());
				emitter.next(payload);

				log.info("[I176] Message sent to client, queue={}", qname);		


			});

			emitter.onRequest(v -> {
				log.info("[I171] Starting container, queue={}", "queue.info");
				mlc.start();
			});

			emitter.onDispose(() -> {
				log.info("[I176] onDispose: queue={}", "queue.info");
				mlc.stop();
			});

			log.info("[I171] Container started, queue={}", "queue.info");

		});

		return Flux.interval(Duration.ofSeconds(35))
				.map(v -> {
					log.info("[I209] sending No new message...");
					return "No new message";
				})
				.mergeWith(f);            
	}


	@PostConstruct
	public void setupQueueDestinationsWarn() {

		log.info("[I56] Creating directExchange: name={}, routingKey={}", "reflex.water.warn", "warn");

		Exchange ex = ExchangeBuilder.directExchange("reflex.water.warn")
				.durable(true)
				.build();

		amqpAdmin.declareExchange(ex);

		Queue q = QueueBuilder.durable("queue.warn").build();

		amqpAdmin.declareQueue(q);

		Binding b = BindingBuilder.bind(q)
				.to(ex)                    
				.with("warn")
				.noargs();

		amqpAdmin.declareBinding(b);

		log.info("[I70] Binding successfully created.");
	}


	@GetMapping(value = "/queue/warn", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ApiIgnore
	public Flux<?> receiveMessagesFromQueueWarn() {

		MessageListenerContainer mlc = messageListenerContainerFactory.createMessageListenerContainer("queue.warn");

		Flux<String> f = Flux.<String> create(emitter -> {

			log.info("[I168] Adding listener, queue={}","queue.warn");
			mlc.setupMessageListener((MessageListener) m -> {

				String qname = "queue.warn";

				log.info("[I137] Message received, queue={}", qname);

				if (emitter.isCancelled()) {
					log.info("[I166] cancelled, queue={}", qname);
					mlc.stop();
					return;
				}

				String payload = new String(m.getBody());
				emitter.next(payload);

				log.info("[I176] Message sent to client, queue={}", qname);		


			});

			emitter.onRequest(v -> {
				log.info("[I171] Starting container, queue={}", "queue.warn");
				mlc.start();
			});

			emitter.onDispose(() -> {
				log.info("[I176] onDispose: queue={}", "queue.warn");
				mlc.stop();
			});

			log.info("[I171] Container started, queue={}", "queue.warn");

		});

		return Flux.interval(Duration.ofSeconds(35))
				.map(v -> {
					log.info("[I209] sending No new message...");
					return "No new message";
				})
				.mergeWith(f);            
	}  
}
