package com.currencyconverter.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document @NoArgsConstructor @AllArgsConstructor @Data @Builder
public class TransactionDTO {
	
	@NonNull
	private String idUser;
	@NonNull
	private String originCurrency;
	@NonNull
	private Float sourceValue;
	@NonNull
	private String destinationCurrency;

}
