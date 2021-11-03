package com.currencyconverter.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.currencyconverter.dao.TransactionDAO;
import com.currencyconverter.dao.UserDAO;
import com.currencyconverter.document.Transaction;
import com.currencyconverter.dto.TransactionDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionDAO transactionDAO;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private APIConsultationService api;
	
	public Mono<Transaction> create(TransactionDTO transactionDto) throws InterruptedException, ExecutionException {
		String _2currencies = "";
		_2currencies += transactionDto.getOriginCurrency();
		_2currencies += ",";
		_2currencies += transactionDto.getDestinationCurrency();
		
		Map<String, Float> map = new HashMap<>();
		map = api.convertTwoCurrencies(_2currencies, transactionDto.getSourceValue());
		System.out.println("======================> map destinationValue: "+map.get("destinationValue"));
		System.out.println("======================> map conversionRateUsed: "+map.get("conversionRateUsed"));
		
		Transaction transaction = new Transaction();
		transaction.setIdUser(userDao.findById(transactionDto.getIdUser()).toFuture().get());
		transaction.setOriginCurrency(transactionDto.getOriginCurrency().toUpperCase());
		transaction.setDestinationCurrency(transactionDto.getDestinationCurrency().toUpperCase());
		transaction.setSourceValue(transactionDto.getSourceValue());
		transaction.setDestinationValue(map.get("destinationValue"));
		transaction.setConversionRateUsed(map.get("conversionRateUsed"));
		transaction.setData(LocalDateTime.now());
		return transactionDAO.save(transaction);
	}
	
	public Mono<Transaction> readOne(String idTransaction) {
		return transactionDAO.findById(idTransaction);
	}
	
	public Flux<Transaction> readAll() {
		return transactionDAO.findAll();
	}

}
