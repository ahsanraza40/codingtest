package com.moxehealth.codingtest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the patient database table.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "patient")
@NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p")
public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    //bi-directional many-to-many association to HospitalProvider
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "patient_provider"
            , joinColumns = {
            @JoinColumn(name = "patient_id", nullable = false)
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "hospital_provider_id", nullable = false)
    })
    private List<HospitalProvider> hospitalProviders;
}