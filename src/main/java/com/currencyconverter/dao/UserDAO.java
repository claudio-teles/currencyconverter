package com.currencyconverter.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.currencyconverter.document.User;

@Repository
public interface UserDAO extends ReactiveMongoRepository<User, String> {

}
