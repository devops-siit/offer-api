package com.dislinkt.offersapi.domain.company;

import com.dislinkt.offersapi.domain.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Company extends BaseEntity {

    @NotNull
    @Size(max = 128)
    @Column(unique = true)
    private String name;
}
