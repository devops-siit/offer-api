package com.dislinkt.offersapi.service;

import static com.dislinkt.offersapi.constants.OfferConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dislinkt.offersapi.service.offer.OfferService;
import com.dislinkt.offersapi.web.rest.offer.payload.OfferDTO;
import com.dislinkt.offersapi.web.rest.offer.payload.request.NewOfferRequest;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class OfferServiceIntegrationTest {

	@Autowired
	private OfferService service;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testInsertOffer() {
		NewOfferRequest req = new NewOfferRequest();
		req.setCompanyUuid(NEW_OFFER_COMPANY_UUID_1);
		req.setDescription(NEW_OFFER_DESCRIPTION);
		req.setPosition(NEW_OFFER_POSITION);
		Set<String> prereq = new HashSet<String>();
		prereq.add(NEW_OFFER_PREREQUISITE);
		req.setPrerequisites(prereq);
		
		OfferDTO dto = service.insertOffer(req);
		assertEquals(NEW_OFFER_POSITION, dto.getPosition());
	}
	
	@Test
	@Transactional
	public void testFindAll() {
		Pageable pageable = PageRequest.of(PAGEABLE_PAGE, PAGEABLE_SIZE);
		Page<OfferDTO> found = service.findAll(pageable);
		
		assertEquals(1, found.getNumberOfElements());
	}
}
