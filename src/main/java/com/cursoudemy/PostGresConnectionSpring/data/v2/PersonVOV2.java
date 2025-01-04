package com.cursoudemy.PostGresConnectionSpring.data.v2;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@JsonPropertyOrder({"id", "first_name", "last_name", "address", "gender"})
public class PersonVOV2 extends RepresentationModel<PersonVOV2> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1;

    @JsonProperty(value = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long key;

    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;
    private String address;
    private String gender;

    public PersonVOV2() {
    }

    public PersonVOV2(Long key, String firstName, String lastName, String address, String gender) {

        this.key = key;

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public boolean equals(@SuppressWarnings("null") Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonVOV2 that = (PersonVOV2) o;
        return Objects.equals(key, that.key) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, firstName, lastName, address, gender);
    }
}
