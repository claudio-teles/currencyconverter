package com.currencyconverter.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document @NoArgsConstructor @AllArgsConstructor @Data @Builder
public class Transaction {
	
	@Id
	private String idTransaction;
	private User idUser;
	private String originCurrency;
	private Float sourceValue;
	private String destinationCurrency;
	private Float destinationValue;
	private Float conversionRateUsed;
	private LocalDateTime data;

}
