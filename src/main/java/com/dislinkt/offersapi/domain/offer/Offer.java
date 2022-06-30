package com.dislinkt.offersapi.domain.offer;

import com.dislinkt.offersapi.domain.base.BaseEntity;
import com.dislinkt.offersapi.domain.company.Company;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
public class Offer extends BaseEntity {

    @NotNull
    @Size(max = 128)
    private String position;

    @NotNull
    @Size(max = 1024)
    private String description;

    @ElementCollection
    @Column(name="prerequisite", nullable=false, length = 256)
    @CollectionTable(name = "offer_prerequisites",
            joinColumns = @JoinColumn(name = "prerequisite_id", referencedColumnName = "id"))
    private Set<String> prerequisites;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
