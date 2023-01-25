package com.deloitte.policy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.policy.custom.exception.BusinessException;
import com.deloitte.policy.custom.exception.ControllerException;
import com.deloitte.policy.custom.exception.DAOException;
import com.deloitte.policy.entity.Policy;
import com.deloitte.policy.service.PolicyService;
import com.deloitte.policy.service.PolicyServiceImpl;

@RestController
@RequestMapping("/policy")
public class PolicyController {

	@Autowired
	private PolicyService policyService;

	@PostMapping("/addpolicy")
	public ResponseEntity<?> savePolicies(@RequestBody Policy policy) {

		try {
			Policy response1 = policyService.savePolicies(policy);

			return new ResponseEntity<Policy>(response1, HttpStatus.CREATED);

		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("614", "Something went wrong in the controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/updatepolicy/{id}")
	public ResponseEntity<?> updatePolicies(@RequestBody Policy policy, @PathVariable("id") Integer customerId) {

		try {
			Policy response2 = policyService.updatePolicies(policy, customerId);

			return new ResponseEntity<Policy>(response2, HttpStatus.OK);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("617", "Something went wrong in the controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/deletepolicy/{id}")
	public ResponseEntity<?> deletePolicies(@PathVariable("id") Integer customerId) {
		try {
			policyService.deletePolicies(customerId);

			String response3 = "Policy " + customerId + " is cancelled";

			return new ResponseEntity<String>(response3, HttpStatus.OK);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("616", "Something went wrong in the controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/allpolicy")
	public ResponseEntity<?> fetchPolicies() {

		try {

			List response4 = policyService.fetchPolicies();

			System.out.println("Getting from db: " + response4);

			return new ResponseEntity<List<Policy>>(response4, HttpStatus.OK);

		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("618", "Something went wrong in the controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/policy/{id}")
	public ResponseEntity<?> getPolicyById(@PathVariable("id") Integer customerId) {
		try {
			Policy response5 = policyService.getPolicyById(customerId);

			return new ResponseEntity<Policy>(response5, HttpStatus.OK);

		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("615", "Something went wrong in the controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/name/{name}")
	public ResponseEntity<?> getByName(@PathVariable("name") String name) {

		List<Policy> response6 = policyService.getByName(name);
		if (response6.size() > 0) {
			return new ResponseEntity<List<Policy>>(response6, HttpStatus.OK);
		} else {
			ControllerException ce = new ControllerException("626", "No Data Found");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/policies/{policies}")
	public ResponseEntity<?> getByPolicies(@PathVariable("policies") String policies) {

		List<Policy> response7 = policyService.getByPolicies(policies);

		if (response7.size() > 0) {
			return new ResponseEntity<List<Policy>>(response7, HttpStatus.OK);
		} else {
			ControllerException ce = new ControllerException("627", "No Data Found");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}

	}
}