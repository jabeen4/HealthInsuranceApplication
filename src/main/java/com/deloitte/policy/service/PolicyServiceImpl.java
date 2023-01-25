package com.deloitte.policy.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.deloitte.policy.custom.exception.BusinessException;
import com.deloitte.policy.custom.exception.ControllerException;
import com.deloitte.policy.custom.exception.DAOException;
import com.deloitte.policy.entity.Policy;
import com.deloitte.policy.repo.PolicyRepo;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	private PolicyRepo policyRepo;

	@Override
	public Policy savePolicies(Policy policy) {

		if (policy.getName().isEmpty() || policy.getName().length() == 0) {
			throw new BusinessException("601", "Please add proper name, this column cannot be blank");
		}
		if (policy.getAddress().isEmpty() || policy.getAddress().length() == 0) {
			throw new BusinessException("601", "Please add proper address, this column cannot be blank");
		}
		if (policy.getPolicies().isEmpty() || policy.getPolicies().length() == 0) {
			throw new BusinessException("601", "Please add proper policy details, this column cannot be blank");
		}
		try {
			return policyRepo.save(policy);

		} catch (IllegalArgumentException e) {
			throw new BusinessException("602", "Given data is null,please add valid details" + e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("603", "Something went wrong in Service layer,Try again!!" + e.getMessage());
		}
	}

	@Override
	public Policy updatePolicies(Policy policy, Integer customerId) {

		Policy updPolicy = policyRepo.findById(customerId).get();
		if (Objects.nonNull(policy.getName()) && !"".equalsIgnoreCase(policy.getName())) {
			updPolicy.setName(policy.getName());
		} else {
			throw new BusinessException("610", "data name is null please provide valid details");
		}

		if (Objects.nonNull(policy.getAddress()) && !"".equalsIgnoreCase(policy.getAddress())) {
			updPolicy.setAddress(policy.getAddress());
		} else {
			throw new BusinessException("611", "data address is null please provide valid details");
		}

		if (Objects.nonNull(policy.getPolicies()) && !"".equalsIgnoreCase(policy.getPolicies())) {
			updPolicy.setPolicies(policy.getPolicies());
		} else {
			throw new BusinessException("612", "data policy is null please provide valid details");
		}
		try {

			return policyRepo.save(updPolicy);
		} catch (Exception e) {
			throw new BusinessException("613",
					"Something happened in the service layer while updating" + e.getMessage());
		}
	}

	@Override
	public void deletePolicies(Integer customerId) {
		
		boolean delPolicies = policyRepo.existsById(customerId);
        if(delPolicies) {
        	policyRepo.deleteById(customerId);
        }else {
        	throw new BusinessException("605",
					"Policy id does not exist in the database");
        }
		
	}

	@Override
	public List<Policy> fetchPolicies() {

		List<Policy> list = null;

		try {
			list = policyRepo.findAll();
		} catch (Exception e) {
			throw new BusinessException("605",
					"Something happened in Service layer while fetching all the policies" + e.getMessage());
		}
		if (list.isEmpty())
			throw new BusinessException("604", "The list is empty,there is nothing to display");
		return list;
	}

	@Override
	public Policy getPolicyById(Integer customerId) {
		try {
			return policyRepo.findById(customerId).get();
		} catch (IllegalArgumentException e) {
			throw new BusinessException("606", "Given id is null,please add valid details" + e.getMessage());
		} catch (java.util.NoSuchElementException e) {
			throw new BusinessException("607", "Given id does not exist in database" + e.getMessage());
		}
	}

	@Override
	public List<Policy> getByName(String name) {

		return policyRepo.findByName(name);
	}

	@Override
	public List<Policy> getByPolicies(String policies) {
		return policyRepo.findByPolicies(policies);

	}

}
