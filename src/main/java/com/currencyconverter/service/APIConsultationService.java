package com.currencyconverter.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.currencyconverter.util.ExchangeRate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class APIConsultationService {
	
	public ExchangeRate seeRates() throws IOException {
		URL path = new URL("http://api.exchangeratesapi.io/v1/latest?access_key=2e521db38caefc3bef5b44867a19904c");
		HttpURLConnection connection = (HttpURLConnection) path.openConnection();
		connection.setRequestProperty("accept", "application/json");
		InputStream responseStream = connection.getInputStream();
		return new ObjectMapper().readValue(responseStream, ExchangeRate.class);
	}

}
