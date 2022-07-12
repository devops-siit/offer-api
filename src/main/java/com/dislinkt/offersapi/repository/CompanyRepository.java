package com.dislinkt.offersapi.repository;

import com.dislinkt.offersapi.domain.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findOneByName(String name);

    Optional<Company> findOneByUuid(String uuid);
}
