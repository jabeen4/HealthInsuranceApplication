package com.deloitte.policy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.stream.Collectors;
import java.util.Optional;
import java.util.stream.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.deloitte.policy.entity.Policy;
import com.deloitte.policy.repo.PolicyRepo;
import com.deloitte.policy.service.PolicyService;

@RunWith(SpringRunner.class)
@SpringBootTest
class HealthInsuranceApplicationTests {

	@Autowired
	private PolicyService policyService;

	@MockBean
	private PolicyRepo policyRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void fetchPoliciesTest() {

		when(policyRepo.findAll()).thenReturn(Stream.of(new Policy(1, "jebi", "8th cross", "family plan"),
				new Policy(2, "fami", "kannadi palli", "individual")).collect(Collectors.toList()));

		assertEquals(2, policyService.fetchPolicies().size()); // comparing
	}


	@Test
	public void getByNameTest() {

		String name = "Jebi";
		when(policyRepo.findByName(name))
				.thenReturn(Stream.of(new Policy(1, "jebi", "8th cross", "family plan")).collect(Collectors.toList()));
		assertEquals(1, policyService.getByName(name).size());

	}

	@Test
	public void getByAddressTest() {

		String policies = "sector 7,Koramangala";
		when(policyRepo.findByPolicies(policies))
				.thenReturn(Stream.of(new Policy(1, "jebi", "8th cross", "family plan")).collect(Collectors.toList()));
	
		assertEquals(1, policyService.getByPolicies(policies).size());
	}
	
	@Test
	public void savePolicies() {
		
		Policy policy = new Policy(2, "aysha", "7th cross road", "404 plan");
		when(policyRepo.save(policy)).thenReturn(policy);
		assertEquals(policy, policyService.savePolicies(policy));
	}
	
	public void getPolicyById() {
		
		Integer id = 1;
		when(policyRepo.findById(id)).thenReturn(Optional.of(new Policy(1, "jebi", "8th cross", "family plan")));
		
		assertEquals(1, policyService.getPolicyById(id));
		
	}

	public void deletePolicies() {
		Integer id = 1;
		when(policyRepo.findById(id))
		.thenReturn(Optional.of(new Policy(1, "jebi", "8th cross", "family plan")));
		policyService.deletePolicies(id);
		verify(policyRepo,times(1)).deleteById(id);
		

		
	}
	
	@Test
	public void updatePolicies() {
		
		Policy updatedPolicy = new Policy(2,"aysha","27th cross road","404 plan");
		
		when(policyRepo.findById(2)).thenReturn(Optional.of(new Policy(2, "jebi", "8th cross", "404 plan")));
		when(policyRepo.save(updatedPolicy)).thenReturn(updatedPolicy);
		assertEquals(updatedPolicy,policyService.updatePolicies(updatedPolicy,2));
	}
	
}



