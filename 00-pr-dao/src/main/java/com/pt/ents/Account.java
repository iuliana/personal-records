package com.pt.ents;

import com.pt.base.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by iuliana.cosmina on 12/27/14.
 */
public class Account extends AbstractEntity {
    
    @Column
    @NotEmpty
    private String bank;
    
    @Column
    @NotEmpty
    private String iban;

    @Column(name = "CREDIT_CARD")
    private String creditCardNumber;
    
    @Column
    private Double amount;


    @ManyToOne
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person person;
    
    //TODO add constructors


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

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

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
        return "Account{" +
                "bank='" + bank + '\'' +
                ", iban='" + iban + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", amount=" + amount +
                ", person=" + person +
                '}';
    }
}
