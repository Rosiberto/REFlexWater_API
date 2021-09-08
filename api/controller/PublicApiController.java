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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping(value = "/api/v2")
@Api(value="REFlex Water API")
public class PublicApiController {
	
		
		@Autowired
	    private ApiService apiService;
		
		
		//verifica se existe uma determinada entidade registrada no orion
			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = "/entitie/exist/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			@ApiIgnore
			public String exist(@PathVariable(value = "id") String id){
				return apiService.existEntity( id );
			}
		
		//verifica se existe uma determinada entidade de um determinado tipo registrada no orion
			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = "/entitie/exist/{id}/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			@ApiIgnore
			public String existIDTYPE(@PathVariable(value = "id") String id, @PathVariable(value = "type") String type){
				return apiService.existEntityIDTYPE(id, type);
			}
				
		
		//lista todas as entidades registradas no orion
			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = "/entitie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			@ApiOperation(value="Retorna TODAS Entidades registradas.")
			//@ApiOperation(value="Retorna TODAS Entidades registradas", response=Entities.class )
			public String findAll(){
				return apiService.getAllEntitieOrion();
			}
			
		//lista todas as entidades registradas no orion compactada
			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = "/entitie/key", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			@ApiIgnore
			public String findAllKeyValues(){
				return apiService.getAllEntitieOrionKeyValues();
			}
			
		//lista todas as entidades do orion  compactada por type
			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = "/entitie/key/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			@ApiOperation(value="Retorna TODAS Entidades passando como parâmetro seu tipo.")
			public String findAllTypeKeyValues(@PathVariable(value = "type") String type){
				return apiService.getAllEntitieOrionForTypeKeyValues( type );
			}
		
		//lista uma entidade do orion de acordo com o id passado
			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = "/entitie/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			@ApiOperation(value="Retorna uma Entidade passando como parâmetro seu identificador.")
			public String getId(@PathVariable(value = "id") String id){
				return apiService.getEntitieOrionId( id );
			}
			
		//lista todas as entidades do orion de acordo com o type passado 
			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = "/entitie/type/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			@ApiOperation(value="Retorna uma Entidade passando como parâmetro seu Type.")
			public String getType(@PathVariable(value = "type") String type){
				return apiService.getEntitieOrionType( type );
			}

		//retorna a quantidade de entidade existentes por tipo
			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = "/count/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			@ApiIgnore
			public int getCountEntities(@PathVariable(value = "type") String type){
				return apiService.countEntities(type);
			}

		//registra uma entidade no orion	
		    @ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = "/entitie", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
			@ApiOperation(value="Registra uma Entidade.")
		    public int create(@RequestBody String entidade){
		        	return apiService.createEntitieOrion( entidade );
			}

		//atualiza os atributos de uma entidade já criada no orion
			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = "/entitie/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
			@ApiOperation(value="Atualiza uma Entidade passando como parâmetros os seus atributos.")
			public int update(@PathVariable(value = "id") String id, @RequestBody String s){
			   return apiService.updateEntitieOrion( id, s );
			}
				
		//exclui Todas as entidades existentes por tipo
			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = "/entitie/{type}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
			@ApiOperation(value="Exclui TODAS Entidades passando como parâmetro seu tipo.")
			public String delete(@PathVariable(value = "type") String type){
				return apiService.deleteAllEntitieOrionType(type);
			}
					
		//lista todas as regras cep
			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = "/rules", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			@ApiOperation(value="Retorna TODAS as Regras registradas.")
			public String getRules(){
				return apiService.getRulesPerseo();
			}

		//Salva regra CEP
			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = "/rules", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
			@ApiIgnore
			public int createRuleCEP(@RequestBody String rule){
				return apiService.postRulePerseo( rule );
			}
			
			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = "/rules/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
			@ApiOperation(value="Exclui uma Regra passando como parâmetro seu ID.")
			public int deleteRule(@PathVariable(value = "id") String id){
				return apiService.deleteRulesPerseo(id);
			}	
			
			//retorna a quantidade de entidade existentes por tipo
			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = "/min/{id}/{type}/{attribute}/{period}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			@ApiIgnore
			public float getMinSTH(@PathVariable(value = "id") String id, @PathVariable(value = "type") String type, 
								  @PathVariable(value = "attribute") String attribute,@PathVariable(value = "period") String period){
				return apiService.valueMinSTH(id, type, attribute, period);
			}
			
			//retorna a quantidade de entidade existentes por tipo
			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = "/max/{id}/{type}/{attribute}/{period}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			@ApiIgnore
			public float getMaxSTH(@PathVariable(value = "id") String id, @PathVariable(value = "type") String type, 
								  @PathVariable(value = "attribute") String attribute,@PathVariable(value = "period") String period){
				return apiService.valueMaxSTH(id, type, attribute, period);
			}
		
			//retorna a quantidade de entidade existentes por tipo
			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = "/avg/{id}/{type}/{attribute}/{period}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			@ApiIgnore
			public float getAvgSTH(@PathVariable(value = "id") String id, @PathVariable(value = "type") String type, 
								  @PathVariable(value = "attribute") String attribute,@PathVariable(value = "period") String period){
				return apiService.valueAvgSTH(id, type, attribute, period);
			}
			
			//retorna a quantidade de entidade existentes por tipo
			@ResponseStatus(HttpStatus.OK)
			@RequestMapping(value = "/raw/{id}/{type}/{attribute}/{hlimit}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			@ApiIgnore
			public String getBrutoSTH(@PathVariable(value = "id") String id, @PathVariable(value = "type") String type, 
								  @PathVariable(value = "attribute") String attribute,@PathVariable(value = "hlimit") String hlimit){
				return apiService.bruto(id, type, attribute, hlimit);
			}
			
}
