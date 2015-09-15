package com.pr.ents;

import com.pr.base.AbstractEntity;
import com.pr.base.Status;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * An entity used as a template for Bank Account instances. An account instance contains in its fields some data
 * specific to a real bank account.
 * Created by iuliana.cosmina on 12/27/14.
 */
@Entity
public class Account extends AbstractEntity {

    /**
     * Name of the bank
     */
    @Column
    @NotEmpty
    private String bank;

    /**
     * The numerical code for a bank account
     */
    @Column
    @NotEmpty
    private String iban;

    /**
     * Amount of money in the account
     */
    @Column
    private Double amount;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person person;

    public Account(){
    }

    public Account(String bank, String iban, Person person) {
        this.bank = bank;
        this.iban = iban;
        this.person = person;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // IDE generated methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;
        if (id != null && id.equals(account.id)) return true;
        if (bank != null ? !bank.equals(account.bank) : account.bank != null) return false;
        if (iban != null ? !iban.equals(account.iban) : account.iban != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (bank != null ? bank.hashCode() : 0);
        result = 31 * result + (iban != null ? iban.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Account[person='%s', bank='%s', iban='%s', amount='%,.2f', status='%s']", person.getFirstName() + " " +
                person.getLastName(), bank, iban, amount, status.toString());
    }
}
