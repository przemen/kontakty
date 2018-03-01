package pl.manka.app.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Set;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "Nazwisko jest wymagane")
    @Size(min = 2, message = "Nazwisko musi zawierać conajmniej 2 znaki")
    private String lastName;
    @NotEmpty(message = "Imię jest wymagane")
    @Size(min = 2, message = "Imię musi składać się z conajmniej 2 liter")
    private String firstName;

    @NotEmpty(message = "Adres jest wymagany")
    private String address;

    @OneToMany(mappedBy = "contact")
    private Set<PhoneNumber> numbers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<PhoneNumber> getNumbers() {
        return numbers;
    }

    public void setNumbers(Set<PhoneNumber> numbers) {
        this.numbers = numbers;
    }
}