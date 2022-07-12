package com.dislinkt.offersapi.repository;

import com.dislinkt.offersapi.domain.offer.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Page<Offer> findAll(Pageable pageable);
}
