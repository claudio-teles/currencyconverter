package com.currencyconverter.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.currencyconverter.document.Transaction;

@Repository
public interface TransactionDAO extends ReactiveMongoRepository<Transaction, String> {

}
