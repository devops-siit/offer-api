package com.dislinkt.offersapi.repository;

import com.dislinkt.offersapi.domain.offer.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffersRepository extends JpaRepository<Offer, Long> {
}
