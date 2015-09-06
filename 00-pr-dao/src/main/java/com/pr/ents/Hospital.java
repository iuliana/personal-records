package com.pr.ents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pr.base.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * An entity used as a template for Hospital instances. A hospital instance contains in its fields some data
 * specific to a real hospital.
 * Created by iuliana.cosmina on 12/27/14.
 */
@Entity
public class Hospital extends AbstractEntity {

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "\\d{6}")
    @NotNull
    private String code;

    @Size(min = 2, max = 50)
    @Column(nullable = false)
    @NotNull
    private String name;

    @Size(max = 100)
    @Column
    private String address;

    @Size(max = 20)
    @Column(nullable = false)
    @NotNull
    private String location;

    @JsonIgnore
    @OneToMany(mappedBy = "hospital")
    private Set<Person> persons = new HashSet<>();

    //required by JPA
    public Hospital() {
    }

    /**
     * Creates a new Hospital instance. All arguments are required and must be not null.
     *
     * @param code
     * @param location
     * @param name
     */
    public Hospital(@NotEmpty String code, @NotEmpty String location, @NotEmpty String name) {
        this.code = code;
        this.location = location;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    protected void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public boolean addPerson(Person person) {
        person.setHospital(this);
        return persons.add(person);
    }

    // IDE generated methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hospital hospital = (Hospital) o;
        if (id != null && id.equals(hospital.id)) return true;
        if (address != null ? !address.equals(hospital.address) : hospital.address != null) return false;
        if (code != null ? !code.equals(hospital.code) : hospital.code != null) return false;
        if (location != null ? !location.equals(hospital.location) : hospital.location != null) return false;
        if (name != null ? !name.equals(hospital.name) : hospital.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Hospital[code=%s, name='%s', location='%s']", code, name, location);
    }
}
