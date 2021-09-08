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
    
    @Value("${sth.host:}")
    private String sthUrl;
    
      
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
    
    public String getSthUrl() {
		return sthUrl;
	}

	public void setSthUrl(String sthUrl) {
		this.sthUrl = sthUrl;
	}

	@Override
    public String toString() {
        return "Configuration{" +
               ", remoteUrl='" + remoteUrl + '\'' +
               ", localUrl='" + localUrl + '\'' +
               ", sthUrl='" + sthUrl + '\'' +
               '}';
    }
}
