package com.deloitte.policy.service;

import java.util.List;

import com.deloitte.policy.entity.Policy;


public interface PolicyService {
	
	public Policy savePolicies(Policy policy);
	public Policy updatePolicies(Policy policy,Integer customerId);
	public void deletePolicies(Integer customerId);
	public List<Policy> fetchPolicies();
	public Policy getPolicyById(Integer customerId);
	public List<Policy> getByName(String name);
	public List<Policy> getByPolicies(String policies);
	

}
