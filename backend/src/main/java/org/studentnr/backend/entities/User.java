package org.studentnr.backend.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/*Can't use 'User' as tablename so need to specify a new using @Table() */
@Entity
@Table(name="USERS")
public class User {

    @Id
    @NotBlank
    @Size(min=6, max=250)
    @Column(unique = true)
    @Email
    private String email;

    @NotBlank
    @Size(max = 125)
    private String firstname;

    @Size(max = 50)
    private String middleName;

    @NotBlank
    @Size(max = 125)
    private String surename;

    @NotBlank
    @Size(max = 250)
    private String address;

    @NotBlank
    @Size(max = 250)
    private String postalCode;

    @NotBlank
    @Size(max = 250)
    private String password;


    /* Cascade types:
              ALL
              PERSIST
              MERGE
              REMOVE
              REFRESH
              DETACH
      */
    /*
    From the JPA 2.0 spec, the defaults are like so:
     OneToMany: LAZY
     ManyToOne: EAGER
     ManyToMany: LAZY
     OneToOne: EAGER
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)  //TODO: what cascadetype should user be allowed to? Persist only?
    private List<Purchase> purchases;

    @NotNull
    private Boolean enabled;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;



    public User() {
        if (purchases == null){
            purchases = new ArrayList<>();
        }
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
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
        return surename;
    }

    public void setSurname(String surname) {
        this.surename = surname;
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
