package com.dislinkt.offersapi.service;

import static com.dislinkt.offersapi.constants.CompanyConstants.NEW_COMPANY_ADDRESS;
import static com.dislinkt.offersapi.constants.CompanyConstants.NEW_COMPANY_DESCRIPTION;
import static com.dislinkt.offersapi.constants.CompanyConstants.NEW_COMPANY_NAME;
import static com.dislinkt.offersapi.constants.CompanyConstants.NEW_COMPANY_PHONE;
import static com.dislinkt.offersapi.constants.CompanyConstants.NEW_COMPANY_UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.dislinkt.offersapi.domain.company.Company;
import com.dislinkt.offersapi.repository.CompanyRepository;
import com.dislinkt.offersapi.service.company.CompanyService;
import com.dislinkt.offersapi.web.rest.company.payload.CompanyDTO;


@SpringBootTest
@RunWith(SpringRunner.class)
@TestExecutionListeners(
	    listeners = {TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class},
	    inheritListeners = false
	    
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CompanyServiceUnitTest {

	@Autowired
	@InjectMocks
	private CompanyService service;
	
	@Mock
	private CompanyRepository repository; 
	
	@BeforeAll
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void testInsertCompany() {
		
		CompanyDTO dto = new CompanyDTO();
		dto.setAddress(NEW_COMPANY_ADDRESS);
		dto.setDescription(NEW_COMPANY_DESCRIPTION);
		dto.setName(NEW_COMPANY_NAME);
		dto.setPhone(NEW_COMPANY_PHONE);
		dto.setUuid(NEW_COMPANY_UUID);
		
		
		Company company = new Company();

        company.setName(dto.getName());
        company.setPhone(dto.getPhone());
        company.setAddress(dto.getAddress());
        company.setDescription(dto.getDescription());
        company.setUuid(dto.getUuid());
        when(repository.save(company)).thenReturn(company);
		
        service.insertCompany(dto);
        
		verify(repository,  times(1)).save(company);
		
	}
	
}


