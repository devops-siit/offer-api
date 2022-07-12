package com.dislinkt.offersapi.service.offer;

import com.dislinkt.offersapi.domain.company.Company;
import com.dislinkt.offersapi.domain.offer.Offer;
import com.dislinkt.offersapi.repository.OfferRepository;
import com.dislinkt.offersapi.service.company.CompanyService;
import com.dislinkt.offersapi.web.rest.company.payload.CompanyDTO;
import com.dislinkt.offersapi.web.rest.offer.payload.OfferDTO;
import com.dislinkt.offersapi.web.rest.offer.payload.request.NewOfferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private CompanyService companyService;

    public OfferDTO insertOffer(NewOfferRequest offerRequest) {

        Company company = companyService.findOneByUuidOrElseThrowException(offerRequest.getCompanyUuid());

        Offer offer = new Offer();

        offer.setPosition(offerRequest.getPosition());
        offer.setDescription(offerRequest.getDescription());
        offer.setPrerequisites(offerRequest.getPrerequisites());
        offer.setCompany(company);

        offerRepository.save(offer);

        OfferDTO offerDTO = new OfferDTO();

        offerDTO.setUuid(offer.getUuid());
        offerDTO.setDescription(offer.getDescription());
        offerDTO.setPosition(offer.getPosition());
        offerDTO.setPrerequisites(offer.getPrerequisites());

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setUuid(offer.getCompany().getUuid());
        companyDTO.setName(offer.getCompany().getName());

        offerDTO.setCompany(companyDTO);

        return offerDTO;
    }

    public Page<OfferDTO> findAll(Pageable pageable) {

        Page<Offer> offers = offerRepository.findAll(pageable);

        return offers.map(offer -> {
            OfferDTO offerDTO = new OfferDTO();

            offerDTO.setUuid(offer.getUuid());
            offerDTO.setDescription(offer.getDescription());
            offerDTO.setPosition(offer.getPosition());
            offerDTO.setPrerequisites(offer.getPrerequisites());

            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setUuid(offer.getCompany().getUuid());
            companyDTO.setName(offer.getCompany().getName());

            offerDTO.setCompany(companyDTO);

            return offerDTO;
        });
    }
}
