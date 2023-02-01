package com.deloitte.policy.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Policy")
public class Policy {

	

    @Id
	private Integer customerId;
	private String name;
	private String address;
	private String policies;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPolicies() {
		return policies;
	}

	public void setPolicies(String policies) {
		this.policies = policies;
	}

	public Policy(Integer customerId, String name, String address, String policies) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.address = address;
		this.policies = policies;
	}

	public Policy() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Policy [customerId=" + customerId + ", name=" + name + ", address=" + address + ", policies=" + policies
				+ "]";
	}
	
}
