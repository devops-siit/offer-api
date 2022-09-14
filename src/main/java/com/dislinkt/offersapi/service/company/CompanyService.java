package com.dislinkt.offersapi.service.company;

import com.dislinkt.offersapi.domain.company.Company;
import com.dislinkt.offersapi.exception.types.EntityAlreadyExistsException;
import com.dislinkt.offersapi.exception.types.EntityNotFoundException;
import com.dislinkt.offersapi.repository.CompanyRepository;
import com.dislinkt.offersapi.web.rest.company.payload.CompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableBinding(Sink.class)
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @StreamListener(target = Sink.INPUT)
    public void insertCompany(CompanyDTO companyDTO) {

        Optional<Company> companyOrEmpty = companyRepository.findOneByName(companyDTO.getName());

        if (companyOrEmpty.isPresent()) {
            throw new EntityAlreadyExistsException("Company already exists");
        }

        Company company = new Company();

        company.setName(companyDTO.getName());
        company.setPhone(companyDTO.getPhone());
        company.setAddress(companyDTO.getAddress());
        company.setDescription(companyDTO.getDescription());
        company.setUuid(companyDTO.getUuid());

        companyRepository.save(company);
    }

    public Company findOneByUuidOrElseThrowException(String uuid) {
        return companyRepository.findOneByUuid(uuid).orElseThrow(() ->
                new EntityNotFoundException("Company not found"));
    }
}
