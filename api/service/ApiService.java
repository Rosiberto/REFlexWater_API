package com.api.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.config.Configuration;
import com.api.util.Util;


@Service
public class ApiService implements IApiService{

	@Autowired
	protected Configuration configuration;



	@Override
	public String existEntity(String id) {
		String responseCode = null,
			   urlOrion		= configuration.getRemoteUrl(),
			   version  	= "/v2/",
			   filter 		= "entities/"+ id; 


		HttpClient httpclient = HttpClients.createDefault();
		HttpGet    get 		  = new HttpGet( urlOrion + version + filter );

		try {
			get.setHeader("Accept", "application/json");
			get.setHeader("fiware-service", "tese");	
			get.setHeader("fiware-servicepath", "/iot");
			HttpResponse response = httpclient.execute( get );

			responseCode =  Integer.toString( response.getStatusLine().getStatusCode() );

		}catch (Exception e) {
			e.printStackTrace();	            
		}		
		return responseCode;		
	}

	@Override
	public String existEntityIDTYPE(String id, String type) {
		String responseCode = null,
			   urlOrion 	= configuration.getRemoteUrl(),
			   version 		= "/v2/",
			   filter 		= "entities/"+ id,
			   args 		= "?type=" + type; 


		HttpClient httpclient = HttpClients.createDefault();
		HttpGet    get 		  = new HttpGet( urlOrion + version + filter + args);

		try {
			get.setHeader("Accept", "application/json");
			get.setHeader("fiware-service", "tese");	
			get.setHeader("fiware-servicepath", "/iot");
			HttpResponse response = httpclient.execute( get );

			responseCode =  Integer.toString( response.getStatusLine().getStatusCode() );

		}catch (Exception e) {
			e.printStackTrace();	            
		}		
		return responseCode;
	}



	@Override
	public String getAllEntitieOrion() {
		String resposta = "",
			   urlOrion	= configuration.getRemoteUrl(),
			   version  = "/v2/",
			   filter 	= "entities?limit=1000"; 

		HttpClient httpclient = HttpClients.createDefault();
		HttpGet get 		  = new HttpGet( urlOrion + version + filter );

		try {
			get.setHeader("Accept", "application/json");
			get.setHeader("fiware-service", "tese");	
			get.setHeader("fiware-servicepath", "/iot");
			HttpResponse response = httpclient.execute( get );

			resposta = Util.inputStreamToString( response.getEntity().getContent() ).toString();

		}catch (Exception e) {
			e.printStackTrace();	            
		}
		return resposta;	 
	}

	@Override
	public String getAllEntitieOrionKeyValues() {
		String resposta = "",
			   urlOrion	= configuration.getRemoteUrl(),
			   version  = "/v2/",
			   filter 	= "entities?options=keyValues"; 

		HttpClient httpclient = HttpClients.createDefault();
		HttpGet get 		  = new HttpGet( urlOrion + version + filter );

		try {
			get.setHeader("Accept", "application/json");
			get.setHeader("fiware-service", "tese");	
			get.setHeader("fiware-servicepath", "/iot");
			HttpResponse response = httpclient.execute( get );

			resposta = Util.inputStreamToString( response.getEntity().getContent() ).toString();

		}catch (Exception e) {
			e.printStackTrace();	            
		}
		return resposta;
	}

	@Override
	public String getAllEntitieOrionForTypeKeyValues(String type) {

		String resposta = "",
			   urlOrion	= configuration.getRemoteUrl(),
			   version  = "/v2/",
			   filter 	= "entities?type="+ type +"&options=keyValues"; 

		HttpClient httpclient = HttpClients.createDefault();
		HttpGet get 		  = new HttpGet( urlOrion + version + filter );

		try {
			get.setHeader("Accept", "application/json");
			get.setHeader("fiware-service", "tese");	
			get.setHeader("fiware-servicepath", "/iot");
			HttpResponse response = httpclient.execute( get );

			resposta = Util.inputStreamToString( response.getEntity().getContent() ).toString();

		}catch (Exception e) {
			e.printStackTrace();	            
		}
		return resposta;
	}

	@Override
	public String getEntitieOrionId(String id) {
		
		String resposta = "",
			   urlOrion	= configuration.getRemoteUrl(),
			   version  = "/v2/",
			   filter 	= "entities/"+ id; 

		HttpClient httpclient = HttpClients.createDefault();
		HttpGet    get 		  = new HttpGet( urlOrion + version + filter );

		try {
			get.setHeader("Accept", "application/json");
			get.setHeader("fiware-service", "tese");	
			get.setHeader("fiware-servicepath", "/iot");
			HttpResponse response = httpclient.execute( get );

			resposta = Util.inputStreamToString( response.getEntity().getContent() ).toString();

		}catch (Exception e) {
			e.printStackTrace();	            
		}
		//System.out.println("resp -> "+ responseCode);
		return resposta;
	}

	@Override
	public String getEntitieOrionType(String type) {
		String resposta = "",
			   urlOrion	= configuration.getRemoteUrl(),
			   version  = "/v2/",
			   filter 	= "entities?type="+ type; 	 	

		HttpClient httpclient = HttpClients.createDefault();
		HttpGet get 		  = new HttpGet( urlOrion + version + filter );

		try {
			get.setHeader("Accept", "application/json");
			get.setHeader("fiware-service", "tese");	
			get.setHeader("fiware-servicepath", "/iot");
			HttpResponse response = httpclient.execute( get );

			resposta = Util.inputStreamToString( response.getEntity().getContent() ).toString();

		}catch (Exception e) {
			e.printStackTrace();	            
		}
		return resposta;
	}

	@Override
	public int countEntities(String type) {
		String resposta = "",
			   urlOrion	= configuration.getRemoteUrl(),
			   version  = "/v2/",
			   filter 	= "types/"+ type +"?options=count"; 

		int count = 0;

		HttpClient httpclient = HttpClients.createDefault();
		HttpGet get 		  = new HttpGet( urlOrion + version + filter );

		try {
			get.setHeader("Accept", "application/json");
			get.setHeader("fiware-service", "tese");	
			get.setHeader("fiware-servicepath", "/iot");
			HttpResponse response = httpclient.execute( get );

			resposta = Util.inputStreamToString(response.getEntity().getContent()).toString();

			//System.out.println("resp -> "+ resposta);

			JSONObject jo = new JSONObject(resposta);

			if (jo.has("error")) {
				return count;
			}else {
				count = Integer.parseInt( ( jo.get("count").toString() ) );
			}
		}catch (Exception e) {
			e.printStackTrace();	            
		}
		return count;
	}

	@Override
	public int createEntitieOrion(String entidade) {
		//formato da String entidade
		//id,type,attribute_1-attribute_2-attribute_n
		//Pump1,Pump,Flow-turb
		//System.out.println(entidade);

		String id, type, 
			   attribute, 
			   payload, 
			   aux[],//vetor que guarda a posicao de separacao de cada elemento da Entidade
			   atributo[],//vetor que guarda a posicao de separacao de cada atributo da Entidade
			   urlOrion = configuration.getRemoteUrl(),
			   version  = "/v2/",
			   filter 	= "entities";

		char x = '-';//caracter que separa os atributos

		int responseCode,
			executeCount = 0,
			cont 		 = 0; //armazena o total de atributos;				

		aux  	  = entidade.split(",");//separa id, type e atributo da Entidade
		id 	 	  = aux[0];//armazena o id da Entidade
		type 	  = aux[1];//armazena o type da Entidade
		attribute = aux[2];//armazena o(s) atributo(s) da Entidade		

		//laço utilizado para contar o numero de atributos presentes na Entidade
		for (int i = 0; i < attribute.length(); i++) {
			if (attribute.charAt(i) == x) { 
				cont++;
			}
		}

		payload = "{\"id\":\""+ id +"\",\"type\":\""+ type +"\"";//inicia o payload que será enviado para o ORION 

		for (int i = 0; i <= cont; i++) {
			if (attribute.indexOf("-") > 0) {
				atributo 	= attribute.split("-");//separa cada atributo
				atributo[i] = "\""+ atributo[i] + "\":{\"value\":" + 0.0 + ",\"type\":\"float\"}";				
				payload 	= payload + "," + atributo[i];
			}else{
				payload = payload + ",\"" + attribute +"\":{\"value\":" + 0.0 + ",\"type\":\"float\"}";				
			}
		}
		payload = payload + "}";//finaliza o payload

		HttpResponse response;
		BufferedReader rd;		

		System.out.println(payload);

		try{	
			HttpClient client = HttpClients.createDefault();
			HttpPost httppost = new HttpPost( urlOrion + version + filter );
			httppost.setHeader("Accept", "application/json");
			httppost.setHeader("fiware-service", "tese");	
			httppost.setHeader("fiware-servicepath", "/iot");

			StringEntity entityPost = new StringEntity( payload );
			entityPost.setContentType("application/json");
			httppost.setEntity( entityPost );			

			do{
				executeCount++;
				response 	 = client.execute( httppost );
				responseCode = response.getStatusLine().getStatusCode();

			}while (executeCount < 5 && responseCode == 408);

			rd = new BufferedReader(
					new InputStreamReader(
							response.getEntity().getContent()
							)
					);

			while ((rd.readLine()) != null){
			}
		}catch (Exception e) {
			responseCode = 408;
			e.printStackTrace();				
		}
		return responseCode;
	}

	@Override
	public int updateEntitieOrion(String id, String entidade) {
		//formato para atualização. O type passa a ser fixo nesse processo
		//	flow=15.5,Head=20,Pressure=33,Atributo=n
		// em 19-11-2020

		int responseCode = 0,
			posicao; //armazena a posição do sinal = do atributo;		

		String atributoValor[],//vetor que guarda a posicao de separacao de cada atributo da Entidade 
			   payload 	  = null, 
			   payloadAux = null,
			   urlOrion   = configuration.getRemoteUrl(),
			   version    = "/v2/",
			   filter 	  = "entities/"+ id +"/attrs"; 			

		atributoValor = entidade.split(",");

		for (int i=0; i< atributoValor.length ; i++) {
			posicao = atributoValor[i].indexOf("=");
			//System.out.println("xx - "+atributoValor[i].substring(0, posicao));

			if ( atributoValor[i].indexOf("=") > 0 ) {	
				payloadAux = "\""   + atributoValor[i].substring(0, posicao) +//pega o que está antes do =
							 "\":{" + 
							 "\"value\":" + atributoValor[i].substring(posicao + 1) +//pega o que está depois do =
							 ",\"type\":\"Float\"}";
			}

			if( payload != null ) {
				payload = payload + "," + payloadAux;				
			}else {
				payload = payloadAux;
			}
		}//fecha for

		payload = "{" + payload + "}";

		try {	 		
			HttpClient httpclient = HttpClients.createDefault();
			HttpPatch httpPatch	  = new HttpPatch( urlOrion + version + filter );	    

			httpPatch.setHeader("Accept", "application/json");
			httpPatch.setHeader("fiware-service", "tese");	
			httpPatch.setHeader("fiware-servicepath", "/iot");

			StringEntity entityPatch = new StringEntity( payload );

			System.out.println(payload + "\n");

			entityPatch.setContentType("application/json");
			httpPatch.setEntity( entityPatch );

			HttpResponse response = httpclient.execute( httpPatch );

			responseCode = response.getStatusLine().getStatusCode();

			System.out.println(response+"\n");

		} catch (Exception e) {
			e.printStackTrace();	            
		}
		return responseCode;
	}

	@Override
	public int deleteEntitieOrion(String id) {

		int responseCode = 0;
		
		String urlOrion = configuration.getRemoteUrl(),
			   version  = "/v2/",
			   filter 	= "entities/"+ id; 

		try {	 		
			HttpClient httpclient = HttpClients.createDefault();
			HttpDelete httpDelete = new HttpDelete( urlOrion + version + filter );	    

			httpDelete.setHeader("Content-Type", "");
			httpDelete.setHeader("fiware-service", "tese");	
			httpDelete.setHeader("fiware-servicepath", "/iot");

			//System.out.println(s+"\n");

			HttpResponse response = httpclient.execute( httpDelete );

			responseCode = response.getStatusLine().getStatusCode();

			System.out.println(response+"\n" + responseCode);

		} catch (Exception e) {
			e.printStackTrace();	            
		}
		return responseCode;
	}

	@Override
	public String deleteAllEntitieOrionType(String type) {

		String retorno = getEntitieOrionType ( type );

		//System.out.println("retorno -> "+ retorno);
		JSONArray ja = new JSONArray(new String( retorno ));

		if (ja.isEmpty()){
			return retorno;
		}else{
			List<String> listaEntidade = new ArrayList<String>();

			//monta a lista com todas as entidades  
			for(int i=0; i<ja.length() ; i++){
				JSONObject jo = ja.getJSONObject(ja.length()-i-1);

				listaEntidade.add( jo.get("id").toString());
			}

			//apaga as entidades uma a uma
			for (String id : listaEntidade) {

				deleteEntitieOrion(id);
			}
		}		
		return "Entidades Excluídas";
	}

	@Override
	public int subscribeCygnus(String corpo) {

		System.out.println("corpo-> "+corpo);
		
		
		String[] aux   = corpo.split("=");
		String   porta = aux[1];//armazena o valor
		
		System.out.println("port-> "+porta);
		
		
		HttpResponse response;
		
		int responseCode = 0,
			executeCount = 0;

		String urlOrion = configuration.getRemoteUrl(),
				version = "/v2/",
				filter 	= "subscriptions",
				payload = "{"
							+"\"description\": \"cygnus\","
							+"\"subject\": {"
							+"\"entities\": [{"
							+"\"idPattern\": \".*\" "
							+"}]}, "
							+"\"notification\": { "
							+"\"http\": { "
							+"\"url\": \"http://cygnus:"+porta+"/notify\" }, "
							+"\"attrsFormat\":\"legacy\"}, "
							+"\"expires\": \"2050-01-01T14:00:00.00Z\", "
							+"\"throttling\": 5 "
							+"}";

		System.out.println("payload --> "+payload);
		
		if (existsSubscription() == 0 || existsSubscription() == 2) {
			try{	
				HttpClient client = HttpClients.createDefault();
				HttpPost httppost = new HttpPost( urlOrion + version + filter );
				httppost.setHeader("Accept", "application/json");
				httppost.setHeader("fiware-service", "tese");	
				httppost.setHeader("fiware-servicepath", "/iot");	

				StringEntity entityPost = new StringEntity( payload );
				entityPost.setContentType("application/json");
				httppost.setEntity( entityPost );			

				do{
					executeCount++;
					response 	 = client.execute( httppost );
					responseCode = response.getStatusLine().getStatusCode();
				}while (executeCount < 5 && responseCode == 408);

			}catch (Exception e) {
				responseCode = 408;
				e.printStackTrace();				
			}
		}
		return responseCode;
	}
	
	

	@Override
	public int subscribePerseo() {

		HttpResponse response;		

		int responseCode = 0,
			executeCount = 0;

		String urlOrion = configuration.getRemoteUrl(),
			   version  = "/v2/",
			   filter 	= "subscriptions",
			   payload 	= "{"
					   		+"\"description\": \"perseo\","
					   		+"\"subject\": {"
					   		+"\"entities\": [{"
					   		+"\"idPattern\": \".*\" "
					   		+"}]},"
					   		+"\"notification\": { "
					   		+"\"http\": { "
					   		+"\"url\": \"http://perseo-fe:9090/notices\" },"
					   		+"\"attrsFormat\":\"legacy\" }, "
					   		+"\"expires\": \"2040-01-01T14:00:00.00Z\", "
					   		+"\"throttling\": 5 "
					   		+"}";		

		if (existsSubscription() == 0 || existsSubscription() == 1) { 
			try{	
				HttpClient client = HttpClients.createDefault();
				HttpPost httppost = new HttpPost( urlOrion + version + filter );
				httppost.setHeader("Accept", "application/json");
				httppost.setHeader("fiware-service", "tese");	
				httppost.setHeader("fiware-servicepath", "/iot");	

				StringEntity entityPost = new StringEntity( payload );
				entityPost.setContentType("application/json");
				httppost.setEntity( entityPost );			

				do{
					executeCount++;
					response 	 = client.execute( httppost );
					responseCode = response.getStatusLine().getStatusCode();
				}while (executeCount < 5 && responseCode == 408);

			}catch (Exception e) {
				responseCode = 408;
				e.printStackTrace();				
			}
		}
		return responseCode; 
	}

	@Override
	public int existsSubscription() {
		//se retornar 0 - não tem subscription
		//se retornar 1 - tem subscription cygnus
		//se retornar 2 - tem subscription perseo
		//se retornar 3 - tem subscription cygnus + perseo

		int retorno = 0, 
			cygnus  = 0, 
			perseo  = 0;

		String resposta = "",
			   urlOrion = configuration.getRemoteUrl(),
			   version  = "/v2/",
			   filter 	= "subscriptions";

		HttpClient httpclient = HttpClients.createDefault();
		HttpGet get		   	  = new HttpGet( urlOrion + version + filter );

		try {
			get.setHeader("fiware-service", "tese");	
			get.setHeader("fiware-servicepath", "/iot");
			HttpResponse response = httpclient.execute(get);

			resposta 	   = Util.inputStreamToString( response.getEntity().getContent() ).toString();
			JSONArray ja = new JSONArray(new String( resposta ));

			if (ja.isEmpty()){
				return retorno;
			}else{
				for(int i=0; i<ja.length() ; i++){
					JSONObject jo = ja.getJSONObject(ja.length()-i-1);

					if (jo.get("description").toString().equalsIgnoreCase("cygnus")) {
						cygnus = 1;
					}else if (jo.get("description").toString().equalsIgnoreCase("perseo")) {
						perseo = 1;
					}
				}
			}

			if (cygnus == 1 && perseo == 0) {
				retorno = 1;
			}else if(cygnus == 0 && perseo == 1) {
				retorno = 2;
			}else if (cygnus == 1 && perseo == 1){
				retorno = 3;
			}
		}catch (Exception e){ 
			e.printStackTrace();   
		}

		return retorno; 
	}

	@Override
	public String getSubscriptionCygnusID() {
		String idCygnus = null, 
			   cygnus 	= "cygnus",
			   resposta = "",
			   urlOrion = configuration.getRemoteUrl(),
			   version  = "/v2/",
			   filter 	= "subscriptions";

		HttpClient httpclient = HttpClients.createDefault();
		HttpGet get		   	  = new HttpGet( urlOrion + version + filter );

		if (existsSubscription() == 1 || existsSubscription() == 3) {

			try {
				get.setHeader("fiware-service", "tese");	
				get.setHeader("fiware-servicepath", "/iot");
				HttpResponse response = httpclient.execute( get );

				resposta 	 = Util.inputStreamToString( response.getEntity().getContent() ).toString();
				JSONArray ja = new JSONArray(new String( resposta ));

				for(int i=0; i<ja.length(); i++){
					JSONObject jo = ja.getJSONObject(ja.length()-i-1);

					if ( jo.get("description").toString().equalsIgnoreCase(cygnus) ) {
						idCygnus = jo.get("id").toString();
					}
				}
			} catch (Exception e){
				e.printStackTrace();
			}

			return idCygnus; 
		}
		return idCygnus;
	}

	@Override
	public int deleteSubscriptionCygnus() {

		int responseCode = 0;

		if (existsSubscription() == 1 || existsSubscription() == 3) {
			String idCygnus = getSubscriptionCygnusID();

			String urlOrion = configuration.getRemoteUrl(),
					version  = "/v2/",
					filter 	= "subscriptions",
					params	= "/"+ idCygnus;

			HttpClient httpclient = HttpClients.createDefault();
			HttpDelete delete  	  = new HttpDelete( urlOrion + version + filter + params );

			try {
				delete.setHeader("Content-Type", "");
				delete.setHeader("fiware-service", "tese");	
				delete.setHeader("fiware-servicepath", "/iot");
				HttpResponse response = httpclient.execute( delete );

				responseCode = response.getStatusLine().getStatusCode();

			} catch (Exception e){
				e.printStackTrace();
			}
			return responseCode; 
		}	
		return responseCode;
	}

	@Override
	public String getSubscriptionPerseoID() {

		String idPerseo = null, 
			   perseo   = "perseo",
			   resposta = "",
			   urlOrion = configuration.getRemoteUrl(),
			   version  = "/v2/",
			   filter 	= "subscriptions";

		
		HttpClient httpclient = HttpClients.createDefault();
		HttpGet get		   	  = new HttpGet( urlOrion + version + filter );

		if (existsSubscription() == 2 || existsSubscription() == 3) {
			try {
				get.setHeader("fiware-service", "tese");	
				get.setHeader("fiware-servicepath", "/iot");
				HttpResponse response = httpclient.execute( get );

				resposta 	 = Util.inputStreamToString( response.getEntity().getContent() ).toString();
				JSONArray ja = new JSONArray(new String( resposta ));

				for(int i=0; i<ja.length(); i++){
					JSONObject jo = ja.getJSONObject(ja.length()-i-1);

					if ( jo.get("description").toString().equalsIgnoreCase( perseo ) ) {
						idPerseo = jo.get("id").toString();
					}
				}
			} catch (Exception e){
				e.printStackTrace();
			}

			return idPerseo; 
		}
		return idPerseo;
	}

	@Override
	public int deleteSubscriptionPerseo() {

		int responseCode = 0;

		if (existsSubscription() == 2 || existsSubscription() == 3) {
			String idPerseo = getSubscriptionPerseoID();

			String urlOrion = configuration.getRemoteUrl(),
				   version  = "/v2/",
				   filter 	= "subscriptions",
				   params	= "/"+ idPerseo;


			HttpClient httpclient = HttpClients.createDefault();
			HttpDelete delete  	  = new HttpDelete( urlOrion + version + filter + params );

			try {
				delete.setHeader("Content-Type", "");
				delete.setHeader("fiware-service", "tese");	
				delete.setHeader("fiware-servicepath", "/iot");
				HttpResponse response = httpclient.execute( delete );

				responseCode = response.getStatusLine().getStatusCode();

			} catch (Exception e){
				e.printStackTrace();
			}
			return responseCode; 
		}	
		return responseCode;
	}

	@Override
	public String getRulesPerseo() {

		String resposta  	= "",
			   responseCode = null,
			   resource		= "/rules",
			   urlPerseo	= configuration.getLocalUrl();

		HttpClient httpclient = HttpClients.createDefault();
		HttpGet get		  	  = new HttpGet( urlPerseo + resource );

		try {
			get.setHeader("fiware-service", "tese");	
			get.setHeader("fiware-servicepath", "/iot");

			HttpResponse response = httpclient.execute( get );

			responseCode = Integer.toString( response.getStatusLine().getStatusCode() );
			resposta 	 = Util.inputStreamToString( response.getEntity().getContent() ).toString();

		}catch (Exception e) {
			System.out.println( responseCode );
			e.printStackTrace();	            
		}		
		return resposta;			
	}

	@Override
	public int postRulePerseo(String rule) {

		String payload	 = "",
				resource	 = "/rules",
				urlPerseo = configuration.getLocalUrl();

		int executeCount = 0,
				responseCode = 0;


		HttpClient client = HttpClients.createDefault();
		HttpPost httppost = new HttpPost( urlPerseo + resource );

		try{
			httppost.setHeader("Accept", "application/json");
			httppost.setHeader("fiware-service", "tese");	
			httppost.setHeader("fiware-servicepath", "/iot");	

			//System.out.println( "rule spring - " + URLDecoder.decode( rule, "UTF-8" ) );

			payload = URLDecoder.decode( rule, "UTF-8" );//decodifica url
			StringEntity entityPost = new StringEntity( payload );

			entityPost.setContentType("application/json");
			httppost.setEntity( entityPost );			

			HttpResponse response;

			do{
				executeCount++;
				response 	 = client.execute( httppost );
				responseCode = response.getStatusLine().getStatusCode();

			}while (executeCount < 5 && responseCode == 408);

			//System.out.println( "\nresponse " + response);
		}catch(Exception e) {
			System.out.println( responseCode );
			e.printStackTrace();
		}

		return responseCode;
	}

	@Override
	public int deleteRulesPerseo(String id) {

		int responseCode = 0;

		String urlPerseo = configuration.getLocalUrl(),
				resource = "/rules/"+ id;  


		try {	 		
			HttpClient httpclient = HttpClients.createDefault();
			HttpDelete httpDelete = new HttpDelete( urlPerseo + resource );	    

			httpDelete.setHeader("Content-Type", "");
			httpDelete.setHeader("fiware-service", "tese");	
			httpDelete.setHeader("fiware-servicepath", "/iot");

			HttpResponse response = httpclient.execute( httpDelete );

			responseCode = response.getStatusLine().getStatusCode();

		} catch (Exception e) {
			e.printStackTrace();	            
		}
		return responseCode;
	}

	@Override
	public float valueMinSTH(String id, String type, String attribute, String period) {
		//url:port/STH/v2/entities/Bomba1/attrs/Flow?type=Pump&aggrMethod=min&aggrPeriod=hour
		
		String urlSTH	  = configuration.getSthUrl(),
			   version    = "/STH/v2/",
			   filter 	  = "entities/" + id,
			   attrs 	  = "/attrs/" + attribute,
			   types 	  = "?type=" + type,
			   aggrMethod = "&aggrMethod=min",
			   aggrPeriod = "&aggrPeriod=" + period,
			   dateFrom   = "&dateFrom=",
			   dateTo	  = "&dateTo=",
			   resposta   = null;
					   
		float result = 0;
				
		dateFrom = dateFrom + LocalDate.now().atStartOfDay();  
		dateTo 	 = dateTo + OffsetDateTime.now().toString();
				
		HttpClient httpclient = HttpClients.createDefault();
		HttpGet    get 		  = new HttpGet( urlSTH + version 	 + 
											 filter + attrs    	 + 
											 types  + aggrMethod +
											 aggrPeriod 		 + 
											 dateFrom 			 + 
											 dateTo);		
						
		try{
			get.setHeader("Accept", "application/json");
			get.setHeader("fiware-service", "tese");	
			get.setHeader("fiware-servicepath", "/iot");
			//get.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
		    //get.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		    //get.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
		
			HttpResponse response = httpclient.execute( get );
						
			resposta = Util.inputStreamToString( response.getEntity().getContent() ).toString();

			//System.out.println("resp -> "+ resposta);
			JSONObject jo = new JSONObject(resposta);
					
			if ( !jo.getJSONArray("value").isEmpty() ) {
				result = Float.parseFloat( jo.getJSONArray("value")
											 .getJSONObject(0)
											 .getJSONArray("points")
											 .getJSONObject(0)
											 .get("min").toString() );

				System.out.println("\n"+id + " min resultado: " + result);
			}
		}catch (Exception e) {
			e.printStackTrace();	            
		}		
					
	  return result;
	}

	@Override
	public float valueMaxSTH(String id, String type, String attribute, String period) {
		//url:port/STH/v2/entities/Bomba1/attrs/Flow?type=Pump&aggrMethod=min&aggrPeriod=hour
		
		String urlSTH	  = configuration.getSthUrl(),
			   version    = "/STH/v2/",
			   filter 	  = "entities/" + id,
			   attrs 	  = "/attrs/" + attribute,
			   types 	  = "?type=" + type,
			   aggrMethod = "&aggrMethod=max",
			   aggrPeriod = "&aggrPeriod=" + period,
			   dateFrom   = "&dateFrom=",
			   dateTo	  = "&dateTo=",
			   resposta   = null;
					   
		float result = 0;
				
		dateFrom = dateFrom + LocalDate.now().atStartOfDay();  
		dateTo 	 = dateTo + OffsetDateTime.now().toString();
				
		HttpClient httpclient = HttpClients.createDefault();
		HttpGet    get 		  = new HttpGet( urlSTH + version 	 + 
											 filter + attrs    	 + 
											 types  + aggrMethod +
											 aggrPeriod 		 + 
											 dateFrom 			 +
											 dateTo);		
						
		try{
			get.setHeader("Accept", "application/json");
			get.setHeader("fiware-service", "tese");	
			get.setHeader("fiware-servicepath", "/iot");
			//get.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
	        //get.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
	        //get.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
					
			HttpResponse response = httpclient.execute( get );
						
			resposta = Util.inputStreamToString( response.getEntity().getContent() ).toString();

			//System.out.println("resp -> "+ resposta);
			JSONObject jo = new JSONObject(resposta);
					
			if ( !jo.getJSONArray("value").isEmpty() ) {
				result = Float.parseFloat( jo.getJSONArray("value")
											 .getJSONObject(0)
											 .getJSONArray("points")
											 .getJSONObject(0)
											 .get("max").toString() );

				System.out.println(id + " max resultado: " + result);
			}
		}catch (Exception e) {
			e.printStackTrace();	            
		}		
					
		return result;	
	}

	@Override
	public float valueAvgSTH(String id, String type, String attribute, String period) {
		//url:port/STH/v2/entities/Bomba1/attrs/Flow?type=Pump&aggrMethod=avg&aggrPeriod=hour
		
		String urlSTH	  = configuration.getSthUrl(),
			   version    = "/STH/v2/",
			   filter 	  = "entities/" + id,
			   attrs 	  = "/attrs/" + attribute,
			   types 	  = "?type=" + type,
			   aggrMethod = "&aggrMethod=sum",
			   aggrPeriod = "&aggrPeriod=" + period,
			   dateFrom   = "&dateFrom=",
			   dateTo	  = "&dateTo=",
			   resposta   = null;
					
		int count;
		float sum, result = 0;

		dateFrom = dateFrom + LocalDate.now().atStartOfDay();  
		dateTo 	 = dateTo + OffsetDateTime.now().toString();
				
		HttpClient httpclient = HttpClients.createDefault();
		HttpGet    get 		  = new HttpGet( urlSTH + version 	 + 
											 filter + attrs    	 + 
											 types  + aggrMethod +
											 aggrPeriod 		 + 
											 dateFrom 			 +
											 dateTo);		
					
		try{
			get.setHeader("Accept", "application/json");
			get.setHeader("fiware-service", "tese");	
			get.setHeader("fiware-servicepath", "/iot");
			//get.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
		    //get.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		    //get.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
			        
					
			HttpResponse response = httpclient.execute( get );
					
			resposta = Util.inputStreamToString( response.getEntity().getContent() ).toString();

			//System.out.println(id +" avg resp -> "+ resposta + "\n");				
			JSONObject jo = new JSONObject(resposta);
					
			if ( !jo.getJSONArray("value").isEmpty()) {
				sum = Float.parseFloat( jo.getJSONArray("value")
										  .getJSONObject(0)
										  .getJSONArray("points")
										  .getJSONObject(0)
										  .get("sum").toString() );

				count = Integer.parseInt( jo.getJSONArray("value")
											.getJSONObject(0)
											.getJSONArray("points")
											.getJSONObject(0)
											.get("samples").toString() );
					
				//-1 usado para remover o registro
				//da entidade que é null para value
				result = (float) (Math.round( (sum / (count) ) *100.0f)/100.0f);
					
				System.out.println(id + " med resultado: " + result);
			}	
			//LocalDate.now().atStartOfDay() - pega a primeira hora do dia atual
			//LocalDateTime.now()); - pega a data e hora autal
		}catch (Exception e) {
			e.printStackTrace();	            
		}		
					
	  return result;
	}

	@Override
	public String bruto(String id, String type, String attribute, String limit) {
		//http://<sth-host>:<sth-port>/STH/v2/entities/<entityId>/attrs/<attrName>?type=<entityType>&hLimit=3&hOffset=0&dateFrom=2016-01-01T00:00:00.000Z&dateTo=2016-01-31T23:59:59.999Z

		
		String urlSTH	= configuration.getSthUrl(),
			   version  = "/STH/v2/",
			   filter 	= "entities/" + id,
			   attrs 	= "/attrs/" + attribute,
			   types 	= "?type=" + type,
			   hLimit 	= "&hLimit=" + Integer.parseInt(limit),
			   hOffset 	= "&hOffset=0",
			   dateFrom = "&dateFrom=",
			   dateTo	= "&dateTo=",
			   resposta = null,
			   result	= null;
					
		dateFrom = dateFrom + LocalDate.now().atStartOfDay();  
		dateTo 	 = dateTo + OffsetDateTime.now().toString();
										
					
		HttpClient httpclient = HttpClients.createDefault();
		HttpGet    get 		  = new HttpGet( urlSTH + version + 
											 filter + attrs   + 
											 types  + hLimit  +
											 hOffset);// 	  + 
											//	 dateFrom 	  +
											//	 dateTo);		
							
		try{
			get.setHeader("Accept", "application/json");
			get.setHeader("fiware-service", "tese");	
			get.setHeader("fiware-servicepath", "/iot");
			//get.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
	        //get.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
	        //get.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
				        
			HttpResponse response = httpclient.execute( get );
							
			resposta = Util.inputStreamToString( response.getEntity().getContent() ).toString();

			//System.out.println(id +" bruto resp -> "+ resposta + "\n");

			JSONObject jo = new JSONObject(resposta);
						
			if ( !jo.getJSONArray("value").isEmpty()) {
				for (int i = 0; i < jo.getJSONArray("value").length(); i++) {
					if ( !jo.getJSONArray("value") 
							.getJSONObject(i)
							.get("attrValue").toString().equals("null") ) {

						/*	String valor = jo.getJSONArray("value")
											 .getJSONObject(i)
											 .get("attrValue").toString();

							String dataHora = jo.getJSONArray("value")
												.getJSONObject(i)
												.get("recvTime").toString();
								*/
						//System.out.println("{"+valor + "," + dataHora + "}\n");	
						//System.out.println(jo.getJSONArray("value").toString());
									
						result = jo.getJSONArray("value").toString();		
					}
				}
			}
			System.out.println("bruto: "+result);
		}catch (Exception e) {
			e.printStackTrace();	            
		}
		
		return result;
	}
}
