package com.bugtracking.employeeconsumer.controller;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bugtracking.employeeconsumer.dto.BugDto;

import io.swagger.annotations.ApiOperation;

@RestController
public class EmployeeConsumer {

	RestTemplate rt = new RestTemplate();

	String endpointBug = "http://localhost:8091/bugs/";

	@ApiOperation("Used to create bug(BY EMPLOYEE)")
	@PostMapping("/employee/bugs")
	public String createBug(@Valid @RequestBody BugDto b) {
		rt.postForLocation(endpointBug, b);
		return "created successfully";
	}

	@ApiOperation("Used to fetch bugs by status(BY EMPLOYEE)")
	@GetMapping("/employees/bugs/bystatus/{bugStatus}")
	public List<BugDto> bugsByStatus(@PathVariable("bugStatus") String bugStatus) {
		String endpointEmpBug = endpointBug + bugStatus;
		return Arrays.asList(rt.getForObject(endpointEmpBug, BugDto[].class));
	}

}
