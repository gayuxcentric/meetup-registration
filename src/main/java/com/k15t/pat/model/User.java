package com.k15t.pat.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.*;

/**
 * User entity to persist data to the database
 *
 * @author Gayathri
 */
@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Enter only Alphabets")
    @Length(min = 2)
    @NotNull
    private String name;

    @Length(min = 6, message = "Password should be minimum 6 characters")
    @NotNull
    private String password;

    /**
     * confirmPassword is not stored in the Database
     */
    @Transient
    private String confirmPassword;

    @Pattern(regexp = ".+@.+\\..+", message = "Enter valid email")
    @NotNull
    private String email;

    private String address;

    @Pattern(regexp = "^[0-9+]*$", message = "Enter valid Phone Number")
    @Length(max = 12)
    private String phone;

    private String city;

    private String country;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    /*
    To main entities in the database and perform soft delete functionality just by updating this column
    Deleted entities will have timestamp values instead of null
    */
    private Date deletedAt;

}
