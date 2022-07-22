package com.learning.Bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.Bank.common.ApiRestResponse;
import com.learning.Bank.entity.Customer;
import com.learning.Bank.exception.BankException;
import com.learning.Bank.repository.CustomerRepository;
import com.learning.Bank.service.AppUserService;

@RestController
public class AppUserController {

	@Autowired
	AppUserService customerService;

	// customer registration
	// to register the user with basic details like
	@PostMapping("/customer/register")
	@ResponseBody
	public ApiRestResponse register(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("fullname") String fullname) throws BankException {
		customerService.register(username, password, fullname);
		return ApiRestResponse.success(201);
	}

	// customer authenticate
	// to validate the customer is registered in the system
	@PostMapping("/customer/authenticate")
	public Customer authenticate(@RequestBody String username, String password) {
		return customerRepository.findByUsernameAndPassword(username, password);
	}

	//

}
