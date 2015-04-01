package com.pr.ents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pr.base.AbstractEntity;
import com.pr.base.PncBuilder;
import com.pr.util.JsonDateSerializer;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * An entity used as a template for Identity Card instances. An identity card  instance contains in its fields some data
 * specific to a real identity card account.
 * Created by iuliana.cosmina on 12/27/14.
 */
@Entity
public class IdentityCard extends AbstractEntity {

    @JsonIgnore
    @OneToOne
    @PrimaryKeyJoinColumn
    private Person person;

    /**
     * Personal numerical code.Size 13.  Meaning:
     * - first digit: gender code
     * - next 6 digits : birthdate formatted as YYMMDD
     * - next 6 digits : hospital where you were born code
     */
    @Column(nullable = false)
    @Pattern(regexp = "\\d{13}")
    @NotEmpty
    private String pnc;

    /**
     * Series of the identity card. Two upper case letters specific to the district you were born into.
     */
    @Column(nullable = false)
    @Pattern(regexp = "\\w{2}")
    @NotEmpty
    private String series;

    /**
     * The number of the identity card. Six digits.
     */
    @Column(nullable = false)
    @Pattern(regexp = "\\d{6}")
    @NotEmpty
    private String number;

    @Column
    @NotNull
    @JsonSerialize(using=JsonDateSerializer.class)
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date emittedAt;

    @Column
    @NotNull
    @JsonSerialize(using=JsonDateSerializer.class)
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiresAt;
    
    @Column
    @NotEmpty
    private String address;

    //required by JPA
    public IdentityCard() {
        super();
    }

    /**
     * The PNC value is being computed from Person instance field values.
     *
     * @param person
     * @param series
     * @param number
     * @param emittedAt
     * @param expiresAt
     */
    public IdentityCard(Person person, String series, String number, Date emittedAt, Date expiresAt) {
        this.person = person;
        this.series = series;
        this.number = number;
        this.emittedAt = emittedAt;
        this.expiresAt = expiresAt;
        this.pnc = PncBuilder.build(person);
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // IDE generated methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdentityCard that = (IdentityCard) o;
        if (id != null && id.equals(that.id)) return true;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (pnc != null ? !pnc.equals(that.pnc) : that.pnc != null) return false;
        if (series != null ? !series.equals(that.series) : that.series != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (pnc != null ? pnc.hashCode() : 0);
        result = 31 * result + (series != null ? series.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("IdentityCard[person='%s', pnc='%s', series='%s', number='%s', emittedAt='%s', " +
                        "expiresAt='%s']", person.getFirstName() + " " + person.getLastName(), pnc, series, number,
                String.format("%1$tm %1$te,%1$tY", emittedAt, emittedAt, emittedAt), String.format("%1$tm %1$te,%1$tY", expiresAt, expiresAt,expiresAt));
    }
}
