package com.currencyconverter.document;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document @NoArgsConstructor @AllArgsConstructor @Data @Builder
public class Transaction {
	
	private UUID idTransaction;
	private User idUser;
	private String originCurrency;
	private Float sourceValue;
	private String destinationCurrency;
	private Float destinationValue;
	private Float conversionRateUsed;
	private LocalDateTime data;

}
