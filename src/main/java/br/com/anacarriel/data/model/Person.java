package br.com.anacarriel.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name="person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the Contact.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "First name of person.",
            example = "Ana", required = true)
    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;

    @Schema(description = "Last name of person.",
            example = "Carriel", required = true)
    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;

    @Schema(description = "Adress of person.",
            example = "Rua major mariano", required = true)
    @Column(nullable = false, length = 100)
    private String address;

    @Schema(description = "Gender of person.",
            example = "female", required = true)
    @Column(nullable = false, length = 6)
    private String gender;

    @Schema(description = "Enabled",
            example = "true or false", required = false)
    @Column(nullable = false)
    private Boolean enabled;

    @Schema(description = "Birthday name of person.",
            example = "10-03-1996", required = true)
    @Column(nullable = false, length = 11)
    private Date birthDay;

    public Date getBirthDay() {
        return birthDay;
    }

    public Person() {
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) && Objects.equals(getFirstName(), person.getFirstName()) && Objects.equals(getLastName(), person.getLastName()) && Objects.equals(getAddress(), person.getAddress()) && Objects.equals(getGender(), person.getGender()) && Objects.equals(getEnabled(), person.getEnabled()) && Objects.equals(getBirthDay(), person.getBirthDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getAddress(), getGender(), getEnabled(), getBirthDay());
    }
}
