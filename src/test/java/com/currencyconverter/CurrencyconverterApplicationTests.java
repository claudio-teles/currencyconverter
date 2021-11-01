package com.currencyconverter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.currencyconverter.document.Transaction;
import com.currencyconverter.document.User;
import com.currencyconverter.service.APIConsultationService;
import com.currencyconverter.service.UserService;
import com.currencyconverter.util.ExchangeRate;

import reactor.core.publisher.Mono;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CurrencyconverterApplicationTests {
	
	@Autowired
	private APIConsultationService apiConsultationService;
	
	@Autowired
	private UserService userService;
	
    @LocalServerPort
    private int port;
    
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
		Map<String, Float> map = apiConsultationService.convertTwoCurrencies("BRL,USD", 500.00f);
		System.out.println("================> "+500.00f+" reais converted into "+map.get("destinationValue")+" dolares");
		Assertions.assertTrue(map.get("destinationValue") >= 85f || map.get("destinationValue") <= 95f);
	}
	
	@Test @Order(3)
	void createUserTest() {
		User user = new User(UUID.fromString("284c6496-3b49-11ec-8d3d-0242ac130003"), "User 1");
        
        WebTestClient.bindToServer()
        .baseUrl("http://localhost:" + port).build().post().uri("/api/v1/user")
        .body(BodyInserters.fromValue(user))
        .exchange()
        .expectAll(
            responseSpec -> responseSpec.expectStatus().isCreated(),
            responseSpec -> responseSpec.expectBody().jsonPath("$.name").isEqualTo("User 1")
        );
	}
	
	@Test @Order(4)
	void readOneUserTest() {
        WebTestClient.bindToServer()
        .baseUrl("http://localhost:" + port).build().get().uri("/api/v1/user/{idUser}/", "284c6496-3b49-11ec-8d3d-0242ac130003")
        .exchange()
        .expectAll(
            responseSpec -> responseSpec.expectStatus().isOk(),
            responseSpec -> responseSpec.expectBody().jsonPath("name").isEqualTo("User 1")
        );
	}
	
	@Test @Order(5)
	void readAllUserTest() {
        WebTestClient.bindToServer()
        .baseUrl("http://localhost:" + port).build().get().uri("/api/v1/users")
        .exchange()
        .expectAll(
            responseSpec -> responseSpec.expectStatus().isOk(),
            responseSpec -> responseSpec.expectBody().jsonPath("$.size()").isEqualTo(1)
        );
	}
	
	@Test @Order(6)
	void createTransactionTest() {
		Map<String, Float> map = apiConsultationService.convertTwoCurrencies("BRL,USD", 800.00f);
		User user = userService.readOne(UUID.fromString("284c6496-3b49-11ec-8d3d-0242ac130003")).block();
		
		Transaction transaction = new Transaction(UUID.fromString("171a6577-8452-4208-a55e-ca8a6e7d6654"), user, "BRL", 800.00f, "USD", map.get("destinationValue"), map.get("conversionRateUsed"), LocalDateTime.now());
        
        WebTestClient.bindToServer()
        .baseUrl("http://localhost:" + port).build().post().uri("/api/v1/transaction")
        .body(BodyInserters.fromValue(transaction))
        .exchange()
        .expectAll(
            responseSpec -> responseSpec.expectStatus().isCreated(),
            responseSpec -> responseSpec.expectBody().jsonPath("$.sourceValue").isEqualTo(String.valueOf(800.00f))
        );
        System.out.println("=====================> Transaction: "+transaction);
	}
	
	@Test @Order(7)
	void readOneTransactionTest() {
        WebTestClient.bindToServer()
        .baseUrl("http://localhost:" + port).build().get().uri("/api/v1/transaction/{idTransaction}/", "171a6577-8452-4208-a55e-ca8a6e7d6654")
        .exchange()
        .expectAll(responseSpec -> responseSpec.expectStatus().isOk());
	}
	
	@Test @Order(8)
	void readTransactionAllTest() {
        WebTestClient.bindToServer()
        .baseUrl("http://localhost:" + port).build().get().uri("/api/v1/transactions")
        .exchange()
        .expectAll(
            responseSpec -> responseSpec.expectStatus().isOk(),
            responseSpec -> responseSpec.expectBody().jsonPath("$.size()").isEqualTo(1)
        );
	}
	
	@Test @Order(9)
	void createUserExceptionTest() {
		User user = new User(null, null);
        
        WebTestClient.bindToServer()
        .baseUrl("http://localhost:" + port).build().post().uri("/api/v1/user")
        .body(BodyInserters.fromValue(user))
        .exchange()
        .expectAll(
            responseSpec -> responseSpec.expectStatus().is5xxServerError()
        );
	}
	
	@Test @Order(10)
	void readOneUserExceptionTest() {
        WebTestClient.bindToServer()
        .baseUrl("http://localhost:" + port).build().get().uri("/api/v1/user/{idUser}/", "304c6496-3b49-11ec-8d3d-0242ac130003")
        .exchange()
        .expectAll(
            responseSpec -> responseSpec.expectStatus().isOk(),
            responseSpec -> Mono.error(new IllegalArgumentException())
        );
	}
	
	@Test @Order(10)
	void readOneUserNullPointExceptionTest() {
		WebTestClient.bindToServer()
		.baseUrl("http://localhost:" + port).build().get().uri("/api/v1/user")
		.exchange()
		.expectAll(
					responseSpec -> responseSpec.expectStatus().is4xxClientError(),
					responseSpec -> Mono.error(new NullPointerException())
				);
	}
	
	@Test @Order(11)
	void readOneTransactionExceptionTest() {
        WebTestClient.bindToServer()
        .baseUrl("http://localhost:" + port).build().get().uri("/api/v1/transaction")
        .exchange()
		.expectAll(
				responseSpec -> responseSpec.expectStatus().is4xxClientError(),
				responseSpec -> Mono.error(new NullPointerException())
			);
	}

}
