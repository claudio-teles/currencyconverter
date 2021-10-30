package com.currencyconverter.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	public Float convertTwoCurrencies(String _2currencies, Float sourceValue) {
		List<String> listCurrency = Arrays.asList(_2currencies.split(","));
		List<Float> values = new ArrayList<>();
		
		listCurrency.forEach(currency -> {
			try {
				seeRates().getRates().forEach((key, value) -> {
					if (key.equals(currency)) {
						values.add(value);
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		if (values.size() == 2) {
			Float quotation = values.get(0) / values.get(1);
			if (quotation < 1) {
				return (sourceValue * quotation);
			}
			if (quotation == 1) {
				return sourceValue;
			}
			if (quotation > 1) {
				return (sourceValue / quotation);
			}
		}
		
		return null;
	}

}
