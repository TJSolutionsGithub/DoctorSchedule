package com.jtavarez.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.Data;


@Data
@Entity
public class DoctorSchedule {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String type;
  private LocalDateTime dateTime;

  @OneToOne
  @JoinColumn(name = "doctor_id")
  private Doctor doctor;

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

}