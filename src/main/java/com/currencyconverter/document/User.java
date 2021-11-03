package com.currencyconverter.document;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document @NoArgsConstructor @AllArgsConstructor @Data @Builder
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8933463591026461384L;
	@Id
	private String idUser;
	@NonNull
	private String name;

}
