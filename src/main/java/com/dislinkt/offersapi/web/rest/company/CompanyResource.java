package com.dislinkt.offersapi.web.rest.company;

import com.dislinkt.offersapi.service.company.CompanyService;
import com.dislinkt.offersapi.util.ReturnResponse;
import com.dislinkt.offersapi.web.rest.company.payload.CompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyResource {

    @Autowired
    private CompanyService companyService;

    // only for agent
    @PostMapping
    private ResponseEntity<CompanyDTO> insertCompany(@RequestBody CompanyDTO companyDTO) {
        return ReturnResponse.entityCreated(companyService.insertCompany(companyDTO));
    }
}
