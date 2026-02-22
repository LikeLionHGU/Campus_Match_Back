package com.pigs.holiday.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Univ extends AuditingFields {
    String name;
    String address;

    protected Univ(){}
    private Univ(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public static Univ of(String name, String address) {
        return new Univ(name, address);
    }
}
