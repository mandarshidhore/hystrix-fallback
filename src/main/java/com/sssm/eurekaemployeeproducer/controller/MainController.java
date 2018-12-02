package com.sssm.eurekaemployeeproducer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sssm.eurekaemployeeproducer.model.Employee;

@RestController
public class MainController {

	// note that eureka server must be up
	@GetMapping(value = "/employee/{empID}")
	@HystrixCommand(fallbackMethod = "getEmployeeFallBack")
	public Employee getEmployee(@PathVariable("empID") Integer empID) {
		if (empID == 0) {
			throw new RuntimeException();
		}
		System.out.println("==> called getEmployee()");
		Employee emp = new Employee();
		emp.setEmpId(empID);
		emp.setFirstName("John");
		emp.setSalary(20000);
		return emp;
	}

	public Employee getEmployeeFallBack(@PathVariable("empID") Integer empID) {
		System.out.println("==> called getEmployeeFallBack()");
		Employee emp = new Employee();
		emp.setEmpId(empID);
		emp.setFirstName("FallBack Employee");
		emp.setSalary(40000);
		return emp;
	}

}
