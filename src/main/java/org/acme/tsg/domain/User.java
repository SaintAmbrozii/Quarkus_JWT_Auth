package org.acme.tsg.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class User {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
    @Column(name = "address")
    private String address;
    @Column(name = "contract")
    private String contract;
    @Column(unique = true,  length = 20)
    private String phone;

    private Boolean isAktive=false;

    private Boolean api=true;

    private Boolean isAggregated=true;


    private Long lastUpload;


    private String roles;

    private ZonedDateTime lastUploadDate;

}

