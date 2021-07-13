/*
 * Copyright (C) 2015 Orange
 *
 * This software is distributed under the terms and conditions of the 'GNU GENERAL PUBLIC LICENSE
 * Version 2' license which can be found in the file 'LICENSE.txt' in this package distribution or
 * at 'http://www.gnu.org/licenses/gpl-2.0-standalone.html'.
 */

package com.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Configuration properties to access ligth broker Orion
 */
@Component
public class Configuration {

    /**
     * Url to the remote broker Orion
     */
    @Value("${remote.url:}")
    private String remoteUrl;
    
    @Value("${local.url:}")
    private String localUrl;
    
    @Value("${spring.rabbitmq.port:}")
    private String rabbitmqPort;
	
	@Value("${spring.rabbitmq.host:}")
	private String rabbitmqHost;
   
    
    public Configuration() {
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }
    
    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }    
    
    public void setRabbitPort(String rabbitmqPort) {
        this.rabbitmqPort = rabbitmqPort;
    }
    
    public String getRabbitPort() {
        return rabbitmqPort;
    }
        
    @Override
    public String toString() {
        return "Configuration{" +
               ", remoteUrl='" + remoteUrl + '\'' +
               ", localUrl='" + localUrl + '\'' +
               ", rabbitmqPort='"+ rabbitmqPort + '\''+
               '}';
    }
}
