package com.example.demo.requests;


import javax.validation.constraints.*;
import java.util.List;

public class UserRequest {
    //@NotNull(message="Ce champ ne doit pas etre null !")
    //@Size(min=3, message="Ce champ doit avoir au moins 3 Caracteres")
    @NotBlank(message="Ce champ ne doit pas etre null !")
    private String firstName;

    @NotNull(message="Ce champ ne doit pas etre null !")
    @Size(min=3, message="Ce champ doit avoir au moins 3 Caracteres")
    private String lastName;

    @NotNull(message="Ce champ ne doit pas etre null !")
    @Email(message="Ce champ doit respecter le format email !")
    private String email;

    @NotNull(message="Ce champ ne doit pas etre null !")
    @Size(min=8, message="mot de passe doit avoir au moins 8 caracteres !")
    @Size(max=12, message="mot de passe doit avoir au max 12 caracteres !")
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
             message="ce mot de passe doit comporter des lettres en Majuscules et Miniscules et numeros")
    private String password;


    private Boolean admin;

    private List<AddressRequest> addresses;
    private ContactRequest contact;



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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AddressRequest> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressRequest> adresses) {
        this.addresses = adresses;
    }

    public ContactRequest getContact() {
        return contact;
    }

    public void setContact(ContactRequest contact) {
        this.contact = contact;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
