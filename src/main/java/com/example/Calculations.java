package com.example;

public class Calculations {
	
	public String dmsToDm(String degrees, String minutes, String seconds) throws NumberFormatException {
		
		float D = Float.parseFloat(degrees);
		float M = Float.parseFloat(minutes);
		float S = Float.parseFloat(seconds);
				
		M += S / 60;
		
		return D + "*   " + M + "'";
	}
	
	public String dmToDms(String degrees, String minutes) throws NumberFormatException {
		
		float D = Float.parseFloat(degrees);
		float M = Float.parseFloat(minutes);
		
		float S = (M - (int) M) * 60;
		
		return D + "*   " + (int) M + "'   " + S + "\"";
	}
}
