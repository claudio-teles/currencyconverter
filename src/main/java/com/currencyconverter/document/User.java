package com.currencyconverter.document;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document @NoArgsConstructor @AllArgsConstructor @Data @Builder
public class User {
	
	@Id
	private UUID idUser;
	private String nome;

}
