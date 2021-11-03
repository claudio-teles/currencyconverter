package com.currencyconverter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.currencyconverter.dao.UserDAO;
import com.currencyconverter.document.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	public Mono<User> create(User user) {
		return userDAO.save(user);
	}
	
	public Mono<User> readOne(String idUser) {
		return userDAO.findById(idUser);
	}
	
	public Flux<User> readAll() {
		return userDAO.findAll();
	}

}
