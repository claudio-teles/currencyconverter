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

import com.currencyconverter.document.User;
import com.currencyconverter.service.UserService;

import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "register a user")
	public Mono<User> create(@RequestBody User user) {
		return userService.create(user);
	}
	
	@GetMapping("/user/{idUser}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find a user by identifier")
	public Mono<User> readOne(@PathVariable(value="idUser") UUID idUser) {
		return userService.readOne(idUser);
	}
	
	@GetMapping("/users")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Find all users")
	public Flux<User> readAll() {
		return userService.readAll();
	}
}
