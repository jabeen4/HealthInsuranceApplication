package com.deloitte.policy.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.policy.entity.Policy;

@Repository
public interface PolicyRepo extends MongoRepository<Policy, Integer>{
	
	List<Policy> findByName(String name);
	List<Policy> findByPolicies(String policies);

}
