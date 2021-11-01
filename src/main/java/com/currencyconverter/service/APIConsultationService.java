package com.currencyconverter.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public Map<String, Float> convertTwoCurrencies(String _2currencies, Float sourceValue) {
		List<String> listCurrency = Arrays.asList(_2currencies.split(","));
		List<Float> values = new ArrayList<>();
		
		Float destinationValue = null;
		Map<String, Float> result = new HashMap<>();
		
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
				destinationValue = (sourceValue * quotation);
				result.put("destinationValue", destinationValue);
				result.put("conversionRateUsed", quotation);
				
				return result;
			}
			if (quotation == 1) {
				destinationValue = sourceValue;
				result.put("destinationValue", destinationValue);
				result.put("conversionRateUsed", quotation);
				
				return result;
			}
			if (quotation > 1) {
				destinationValue = (sourceValue / quotation);
				result.put("destinationValue", destinationValue);
				result.put("conversionRateUsed", quotation);
				
				return result;
			}
		}
		
		return null;
	}

}
