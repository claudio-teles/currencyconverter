package com.currencyconverter;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.currencyconverter.document.User;
import com.currencyconverter.service.APIConsultationService;
import com.currencyconverter.util.ExchangeRate;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CurrencyconverterApplicationTests {
	
	@Autowired
	private APIConsultationService apiConsultationService;
	@LocalServerPort
	private int port;
	@Autowired
	private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    
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
		System.out.println("================> "+500.00f+" reais converted into "+value+" dolares");
		Assertions.assertTrue(value >= 85f || value <= 95f);
	}
	
	@Test @Order(3)
	void createUserTest() {
		User user = new User(null, "User 1");
		
		try {
			this.mockMvc
			.perform(
					  MockMvcRequestBuilders
			    	      .post("/api/v1/user")
			    	      .content(objectMapper.writeValueAsString(user))
			    	      .contentType(MediaType.APPLICATION_JSON)
			    	      .accept(MediaType.APPLICATION_JSON)
			    	   )
				      .andExpect(MockMvcResultMatchers.status().isCreated())
				      .andExpect(MockMvcResultMatchers.jsonPath("$.idUser").exists());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test @Order(4)
//	void readOneUserTest() {
//		try {
//			this.mockMvc
//			.perform(
//					  MockMvcRequestBuilders
//			    	      .post("/user/{idUser}", "")
//			    	      .contentType(MediaType.APPLICATION_JSON)
//			    	      .accept(MediaType.APPLICATION_JSON)
//			    	   )
//				      .andExpect(MockMvcResultMatchers.status().isCreated())
//				      .andExpect(MockMvcResultMatchers.jsonPath("$.idUser").exists());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test @Order(5)
//	void readAllUserTest() {
//		
//	}

}
