package com.currencyconverter;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.currencyconverter.service.APIConsultationService;
import com.currencyconverter.util.ExchangeRate;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CurrencyconverterApplicationTests {
	
	@Autowired
	private APIConsultationService apiConsultationService;

	@Test @Order(1)
	void chargeFee() {
		try {
			ExchangeRate exchangeRate = apiConsultationService.seeRates();
			System.out.println("==================================================================================================================================");
			System.out.println("=============> "+exchangeRate);
			System.out.println("==================================================================================================================================");
			Assertions.assertNotNull(exchangeRate);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test @Order(2)
	void currencyConverterTest() {
		Float value = apiConsultationService.convertTwoCurrencies("BRL,USD", 500.00f);
		System.out.println("================> "+500.00f+" reais convertido em "+value+" dolares");
		Assertions.assertTrue(value >= 85f || value <= 95f);
	}

}
