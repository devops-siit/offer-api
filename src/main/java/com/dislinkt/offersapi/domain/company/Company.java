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
    
    @NotNull
    @Size(max = 128)
    @Column(unique = false)
    private String address;
    
    @NotNull
    @Size(max = 25)
    @Column(unique = false)
    private String phone;
    
    @NotNull
    @Size(max = 1024)
    @Column(unique = false)
    private String description;
}
