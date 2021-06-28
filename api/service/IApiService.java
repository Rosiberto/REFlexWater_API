package com.api.service;

public interface IApiService {

	String getAllEntitieOrion();
	
	String getAllEntitieOrionKeyValues();
	
	String getAllEntitieOrionForTypeKeyValues(String type);
	
	String getEntitieOrionId(String id);
	
	String getEntitieOrionType(String type);
	
	String existEntity(String id);
	
	String existEntityIDTYPE(String id, String type);
	
	int deleteEntitieOrion(String id);
	
	String deleteAllEntitieOrionType(String type);
	
	int countEntities(String type);
	
	int createEntitieOrion(String entidade);
	
	int updateEntitieOrion(String id, String s);
	
	int subscribeCygnus();
	
	int subscribePerseo();
	
	int existsSubscription();
	
	String getSubscriptionCygnusID();
	
	int deleteSubscriptionCygnus();
	
	String getSubscriptionPerseoID();
	
	int deleteSubscriptionPerseo();
	
	String getRulesPerseo();
	
	int postRulePerseo(String rule);
	
	int deleteRulesPerseo(String id);
	
	void notifyRabbitmq(String serverit, String message);

	

}
