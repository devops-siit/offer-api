package com.dislinkt.offersapi.web.rest.offer.payload.request;

import lombok.Data;

import java.util.Set;

@Data
public class NewOfferRequest {

    private String position;

    private String description;

    private Set<String> prerequisites;

    private String companyUuid;
}
