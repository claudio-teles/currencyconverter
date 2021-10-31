package com.currencyconverter.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.currencyconverter.document.Transaction;
import com.currencyconverter.service.TransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/transaction")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Transaction> create(@RequestBody Transaction transaction) {
		return transactionService.create(transaction);
	}
	
	@GetMapping("/transaction/{idTransaction}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Transaction> readOne(@PathVariable("idTransaction") UUID idTransaction) {
		return transactionService.readOne(idTransaction);
	}
	
	@GetMapping("/transactions")
	@ResponseStatus(HttpStatus.OK)
	public Flux<Transaction> readAll() {
		return transactionService.readAll();
	}

}
