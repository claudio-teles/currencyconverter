package com.currencyconverter.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.currencyconverter.dao.TransactionDAO;
import com.currencyconverter.document.Transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionDAO transactionDAO;
	
	public Mono<Transaction> create(Transaction transaction) {
		return transactionDAO.save(transaction);
	}
	
	public Mono<Transaction> readOne(UUID idTransaction) {
		return transactionDAO.findById(idTransaction);
	}
	
	public Flux<Transaction> readAll() {
		return transactionDAO.findAll();
	}

}
