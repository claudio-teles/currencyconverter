package com.currencyconverter.dao;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.currencyconverter.document.Transaction;

@Repository
public interface TransactionDAO extends ReactiveMongoRepository<Transaction, UUID> {

}
