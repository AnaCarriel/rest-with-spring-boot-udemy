package br.com.anacarriel.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id","address", "first_name", "last_name", "gender"})
public class PersonVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String address;

    @JsonIgnore
    private String gender;

    private Date birthDay;

    public Date getBirthDay() {
        return birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonVO)) return false;
        PersonVO personVO = (PersonVO) o;
        return Objects.equals(getId(), personVO.getId()) && Objects.equals(getFirstName(), personVO.getFirstName()) && Objects.equals(getLastName(), personVO.getLastName()) && Objects.equals(getAddress(), personVO.getAddress()) && Objects.equals(getGender(), personVO.getGender()) && Objects.equals(getBirthDay(), personVO.getBirthDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getAddress(), getGender(), getBirthDay());
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public PersonVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
