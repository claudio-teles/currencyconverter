package com.currencyconverter.util;

import java.util.Date;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class ExchangeRate {
	
	private String success;
	private boolean timestamp;
	private String base;
	private Date date;
	private Map<String, Float> rates;

}
