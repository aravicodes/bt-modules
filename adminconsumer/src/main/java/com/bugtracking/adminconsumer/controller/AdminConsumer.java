package com.bugtracking.adminconsumer.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bugtracking.adminconsumer.bean.Project;
import com.bugtracking.adminconsumer.dto.BugDto;
import com.bugtracking.adminconsumer.dto.EmployeeDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
public class AdminConsumer {
	RestTemplate rt = new RestTemplate();

	String endpointBug = "http://localhost:8091/bugs/";
	String endpointEmployee = "http://localhost:8092/employees/";
	String endpointProject = "http://localhost:8093/projects/";
	
	String createdMessage = "created successfully";
	String updatedMessage = "updated successfully";
	String deletedMessage = "deleted successfully";

	@GetMapping("/admin/bugs")
	@ApiOperation("used to fetch all bugs(BY ADMIN)")
	public List<BugDto> getAllBugs() {
		return Arrays.asList(rt.getForObject(endpointBug, BugDto[].class));
	}

	@ApiOperation("Used to fetch bug with particular id(BY ADMIN)")
	@GetMapping("/admin/bugs/{bugId}")
	public BugDto getBug(@PathVariable long bugId) {
		String emp = endpointBug + bugId;
		return rt.getForObject(emp, BugDto.class);
	}

	@ApiOperation("Used to delete bug with particular id(BY ADMIN)")
	@DeleteMapping("/admin/bugs/{bugId}")
	public String deleteBug(@PathVariable long bugId) {
		String emp1 = endpointBug + bugId;
		rt.delete(emp1);
		return deletedMessage;
	}

	@ApiOperation("Used to update bug(BY ADMIN)")
	@PutMapping("/admin/bugs/{bugId}")
	public String updateBug(@PathVariable long bugId, @Valid @RequestBody BugDto b) {
		String bug = endpointBug + bugId;
		rt.put(bug, b);
		return updatedMessage;
	}

	@ApiOperation("Used to fetch bugs by status(BY ADMIN)")
	@GetMapping("/admin/bystatus/{bugStatus}")
	public List<BugDto> getBugsByStatus(@PathVariable String bugStatus) {
		String endpointBugStatus = endpointBug + "/bystatus/" + bugStatus;
		return Arrays.asList(rt.getForObject(endpointBugStatus, BugDto[].class));
	}

	@ApiOperation("Used to fetch all employees(BY ADMIN)")
	@GetMapping("/admin/employees")
	public List<EmployeeDto> getAllEmployees() {
		return Arrays.asList(rt.getForObject(endpointEmployee, EmployeeDto[].class));
	}

	@ApiOperation("Used to fetch employee with particular id(BY ADMIN)")
	@GetMapping("/admin/employees/{empId}")
	public EmployeeDto getEmployee(@PathVariable long empId) {
		String emp1 = endpointEmployee + empId;
		return rt.getForObject(emp1, EmployeeDto.class);
	}

	@ApiOperation("Used to delete employee with particular id(BY ADMIN)")
	@DeleteMapping("/admin/employees/{empId}")
	public String deleteEmployee(@PathVariable long empId) {
		String emp2 = endpointEmployee + empId;
		rt.delete(emp2);
		return deletedMessage;
	}

	@ApiOperation("Used to create employee(BY ADMIN)")
	@PostMapping("/admin/employees")
	public String createEmployee(@Valid @RequestBody EmployeeDto e) {
		rt.postForLocation(endpointEmployee, e);
		return createdMessage;
	}

	@ApiOperation("Used to update employees(BY ADMIN)")
	@PutMapping("/admin/employees/{empId}")
	public String updateEmployee(@PathVariable("empId") long empId, @Valid @RequestBody EmployeeDto e) {
		String emp3 = endpointEmployee + empId;
		rt.put(emp3, e);
		return updatedMessage;
	}

	@ApiOperation("Used to fetch all projects(BY ADMIN)")
	@GetMapping("/admin/projects")
	public List<Project> getAllProjects() {
		return Arrays.asList(rt.getForObject(endpointProject, Project[].class));

	}

	@ApiOperation("Used to fetch project with particular id(BY ADMIN)")
	@GetMapping("/admin/projects/{projId}")
	public Project getProject(@PathVariable long projId) {
		String prj = endpointProject + projId;
		return rt.getForObject(prj, Project.class);
	}

	@ApiOperation("Used to delete project with particular id(BY ADMIN)")
	@DeleteMapping("/admin/projects/{projId}")
	public String deleteProject(@PathVariable long projId) {
		String prj1 = endpointProject + projId;
		rt.delete(prj1);
		return deletedMessage;
	}

	@ApiOperation("Used to create project(BY ADMIN)")
	@PostMapping("/admin/projects")
	public String createProject(@Valid @RequestBody Project p) {
		rt.postForLocation(endpointProject, p);
		return createdMessage;
	}

	@ApiOperation("Used to update project(BY ADMIN)")
	@PutMapping("/admin/projects/{projId}")
	public String updateProject(@PathVariable("projId") long projId, @Valid @RequestBody Project p) {
		String prj2 = endpointProject + projId;
		rt.put(prj2, p);
		return updatedMessage;
	}
}
