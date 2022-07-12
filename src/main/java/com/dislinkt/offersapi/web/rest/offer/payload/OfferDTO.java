package com.dislinkt.offersapi.web.rest.offer.payload;

import com.dislinkt.offersapi.web.rest.base.BaseDTO;
import com.dislinkt.offersapi.web.rest.company.payload.CompanyDTO;
import lombok.Data;

import java.util.Set;

@Data
public class OfferDTO extends BaseDTO {

    private String position;

    private String description;

    private Set<String> prerequisites;

    private CompanyDTO company;
}
