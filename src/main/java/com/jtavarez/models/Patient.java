package com.jtavarez.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String address;

  @ManyToMany
  @JoinTable(
      name = "doctor_patient",
      joinColumns = @JoinColumn(name = "patient_id"),
      inverseJoinColumns = @JoinColumn(name = "doctor_id")
  )
  private Set<Doctor> doctors = new HashSet<>();

}