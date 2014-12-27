package com.pt.ents;

import com.pt.base.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by iuliana.cosmina on 12/27/14.
 */
@Entity
public class IdentityCard extends AbstractEntity {
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="PERSON_ID")
    private Person person;

    @Column(nullable = false)
    @Pattern(regexp="\\d{13}")
    @NotEmpty
    private String pnc;

    @Column(nullable = false)
    @Pattern(regexp="\\d{2}")
    @NotEmpty
    private String series;

    @Column(nullable = false)
    @Pattern(regexp="\\d{6}")
    @NotEmpty
    private String number;

    @Column
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date emittedAt;

    @Column
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiresAt;

    //TODO add constructors

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPnc() {
        return pnc;
    }

    public void setPnc(String pnc) {
        this.pnc = pnc;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getEmittedAt() {
        return emittedAt;
    }

    public void setEmittedAt(Date emittedAt) {
        this.emittedAt = emittedAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    //TODO add hashcode, equals, toString
}
