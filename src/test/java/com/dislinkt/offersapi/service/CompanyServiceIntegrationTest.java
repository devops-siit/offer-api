package com.dislinkt.offersapi.service;

import static com.dislinkt.offersapi.constants.CompanyConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dislinkt.offersapi.domain.company.Company;
import com.dislinkt.offersapi.service.company.CompanyService;
import com.dislinkt.offersapi.web.rest.company.payload.CompanyDTO;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CompanyServiceIntegrationTest {

	@Autowired
	private CompanyService service;
	
	@Test
	public void testFindOneByUuidOrElseThrowException() {
		Company found = service.findOneByUuidOrElseThrowException(DB_COMPANY_UUID_1);
		assertEquals(DB_COMPANY_NAME_1, found.getName());
	}
	
	@Test
	public void testFindOneByUuidThrowException() {
		Throwable exception = assertThrows(
	            Exception.class, () -> {
	        		service.findOneByUuidOrElseThrowException(DoesntExist_COMPANY_UUID);
	            }
	    );
	    assertEquals("Company not found", exception.getMessage());
    
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
		
		service.insertCompany(dto);
		Company created = service.findOneByUuidOrElseThrowException(NEW_COMPANY_UUID);
		assertEquals(dto.getName(), created.getName());
	}
	
	@Test
	public void testInsertCompanyNameExists() {
		CompanyDTO dto = new CompanyDTO();
		dto.setAddress(NEW_COMPANY_ADDRESS);
		dto.setDescription(NEW_COMPANY_DESCRIPTION);
		dto.setName(DB_COMPANY_NAME_1);
		dto.setPhone(NEW_COMPANY_PHONE);
		dto.setUuid(NEW_COMPANY_UUID);
		Throwable exception = assertThrows(
	            Exception.class, () -> {
	        		service.insertCompany(dto);
	            }
	    );
	    assertEquals("Company already exists", exception.getMessage());
	}
}
