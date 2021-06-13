package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.service.ApiService;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/api/v1")
@ApiIgnore
public class ApiController {

	@Autowired
    private ApiService apiService;

	
	//verifica se existe uma determinada entidade registrada no orion
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/entitie/exist/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public String exist(@PathVariable(value = "id") String id){
			return apiService.existEntity( id );
		}
		
	//verifica se existe uma determinada entidade de um determinado tipo registrada no orion
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/entitie/exist/{id}/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public String existIDTYPE(@PathVariable(value = "id") String id, @PathVariable(value = "type") String type){
			return apiService.existEntityIDTYPE(id, type);
		}
			
	//lista todas as entidades registradas no orion
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/entitie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public String findAll(){
			return apiService.getAllEntitieOrion();
		}
		
	//lista todas as entidades registradas no orion compactada
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/entitie/key", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public String findAllKeyValues(){
			return apiService.getAllEntitieOrionKeyValues();
		}
		
	//lista todas as entidades do orion  compactada por type
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/entitie/key/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public String findAllTypeKeyValues(@PathVariable(value = "type") String type){
			return apiService.getAllEntitieOrionForTypeKeyValues( type );
		}
	
	//lista uma entidade do orion de acordo com o id passado
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/entitie/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public String getId(@PathVariable(value = "id") String id){
			return apiService.getEntitieOrionId( id );
		}
		
	//lista todas as entidades do orion de acordo com o type passado 
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/entitie/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public String getType(@PathVariable(value = "type") String type){
			return apiService.getEntitieOrionType( type );
		}

	//retorna a quantidade de entidade existentes por tipo
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/count/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public int getCountEntities(@PathVariable(value = "type") String type){
			return apiService.countEntities(type);
		}

	//registra uma entidade no orion	
		@ResponseStatus(HttpStatus.OK)
	    @RequestMapping(value = "/entitie", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	    public int create(@RequestBody String entidade){
	        return apiService.createEntitieOrion( entidade );
	    }

	//atualiza os atributos de uma entidade já criada no orion
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/entitie/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
		public int update(@PathVariable(value = "id") String id, @RequestBody String s){
		   return apiService.updateEntitieOrion( id, s );
		}
			
	//exclui Todas as entidades existentes por tipo
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/entitie/{type}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
		public String delete(@PathVariable(value = "type") String type){
			return apiService.deleteAllEntitieOrionType(type);
		}
				
	//Subscreve os Dados Históricos
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/subscription/cygnus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		public int subscribeHistoricalData(){
			return apiService.subscribeCygnus();
		}

	//Subscreve o CEP
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/subscription/perseo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		public int subscribeCEP(){
			return apiService.subscribePerseo();
		}

	//pega ID da subscription Cygnus
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/subscription/cygnus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public String getCygnusID(){
			return apiService.getSubscriptionCygnusID();
		}
			
	//Deleta a subscription do CEP
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/subscription/cygnus", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
		public int deleteSubscriptionCygnus(){
			return apiService.deleteSubscriptionCygnus();
		}
				
	//pega ID da subscription Perseo
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/subscription/perseo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public String getPerseoID(){
			return apiService.getSubscriptionPerseoID();
		}

	//Deleta a subscription do CEP
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/subscription/perseo", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
		public int deleteSubscriptionPerseo(){
			return apiService.deleteSubscriptionPerseo();
		}

		
	//verifica se existe alguma subscription
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/subscription", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public int getExists(){
			return apiService.existsSubscription();
		}
		
	//lista todas as regras cep
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/rules", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public String getRules(){
			return apiService.getRulesPerseo();
		}

	//Salva regra CEP
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/rules", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		public int createRuleCEP(@RequestBody String rule){
			return apiService.postRulePerseo( rule );
		}
		
		@ResponseStatus(HttpStatus.OK)
		@RequestMapping(value = "/rules/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
		public int deleteRule(@PathVariable(value = "id") String id){
			return apiService.deleteRulesPerseo(id);
		}

		



		
		/*	
		@GetMapping("/")
		public ModelAndView listRule(){
			
			String resposta  	= "";
			String responseCode = null;
		 	//String url = "http://195.220.224.146:1026/v2/entities?options=keyValues";
			String url = "http://195.220.224.146:1026/v2/entities?options=keyValues";
			
			HttpClient httpclient = HttpClients.createDefault();
			HttpGet get		   = new HttpGet( url );
			 
			try {
				get.setHeader("fiware-service", "iot");
		        get.setHeader("fiware-servicepath", "/");
		        
			    HttpResponse response = httpclient.execute(get);
			        
			    responseCode = Integer.toString( response.getStatusLine().getStatusCode() );
			    resposta 	 = Util.inputStreamToString(response.getEntity().getContent()).toString();
			        
			    JSONArray ja = new JSONArray(new String(resposta));
		        
		        Entities e;
		        List l = new ArrayList();
		        
		        for(int i=0; i< ja.length() ; i++){
		            JSONObject jo = ja.getJSONObject(ja.length()-i-1);
		            e = new Entities();
		            e.setId(jo.getString("id"));
		            e.setType(jo.getString("type"));
		            
		            l.add(e.getId()+" - "+e.getType());
		            
		        }
		        
		        while(!l.isEmpty()){
		            System.out.println(l.remove(0));
		        }
		        
			    System.out.println(resposta);
			    System.out.println(responseCode);
			        
			    }catch (Exception e) {
			     	System.out.println(e);
			        e.printStackTrace();	            
			    }
			
			ModelAndView mv = new ModelAndView("index");
			mv.addObject("resposta", resposta);
			
			 return mv;
				
		}
		*/

}
