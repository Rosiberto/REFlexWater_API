package com.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.api.entities.Entities;

public final class Util {

	public static Entities parseEntity(String s) {
    	
		return null;
    }
	
	
	public static String inputStreamToString(InputStream is) {
		String linha = "";
        String total = "";
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
         
        try {
          while ((linha = rd.readLine()) != null) {
        		total += linha;
          }
        } catch (IOException e) {
        	e.printStackTrace();
        }        
       return total;
	}
}
