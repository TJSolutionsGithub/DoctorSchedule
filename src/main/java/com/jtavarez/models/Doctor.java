package com.jtavarez.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import lombok.Data;

@Data //lombok
@Entity //for data activities
public class Doctor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "hospital_id")
  private Hospital hospital;

  @ManyToOne
  @JoinColumn(name = "specialty_id")
  private Specialty specialty;

  @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
  private DoctorSchedule schedule;

/*  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }*/

}