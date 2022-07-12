package com.dislinkt.offersapi.web.rest.offer;

import com.dislinkt.offersapi.service.offer.OfferService;
import com.dislinkt.offersapi.util.ReturnResponse;
import com.dislinkt.offersapi.web.rest.offer.payload.OfferDTO;
import com.dislinkt.offersapi.web.rest.offer.payload.request.NewOfferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/offers")
public class OfferResource {

    @Autowired
    private OfferService offerService;

    @PostMapping
    public ResponseEntity<OfferDTO> insertOffer(@RequestBody NewOfferRequest offerRequest) {
        return ReturnResponse.entityCreated(offerService.insertOffer(offerRequest));
    }

    @GetMapping
    public ResponseEntity<Page<OfferDTO>> findAll(Pageable pageable) {
        return ReturnResponse.entityGet(offerService.findAll(pageable));
    }
}
