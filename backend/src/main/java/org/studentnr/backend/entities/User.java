package org.studentnr.backend.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;


/*Can't use 'User' as tablename so need to specify a new using @Table() */
@Entity
@Table(name="USERS")
public class User {

    @Id
    @NotBlank @Size(min=10, max=128)
    //@Email //TODO:Try email validation (Size needed?)
    //@Column(unique = true) Do we need unique here or does the email validation add the unique?
    private String email;

    @NotBlank
    @Size(max = 124)
    private String firstname;

    //the middle name might be missing, but, if there is one, can't be too long
    @Size(max = 124)
    private String middleName;

    @NotBlank
    @Size(max = 124)
    private String surname;

    @NotBlank
    @Size(max = 255)
    private String address;

    @NotBlank
    @Size(max = 255)
    private String postalCode;

    @NotBlank
    @Size(min = 8, max = 255)
    private String password;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    @NotNull
    private Boolean enabled;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;



    public User() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String username) {
        this.firstname = username;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email_username) {
        this.email = email_username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

}
