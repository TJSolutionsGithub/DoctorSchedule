package com.jtavarez;

import com.jtavarez.models.Doctor;
import com.jtavarez.models.DoctorSchedule;
import com.jtavarez.models.Hospital;
import com.jtavarez.models.Patient;
import com.jtavarez.models.Specialty;
import java.time.LocalDateTime;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
  public static void main(String[] args) {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    Session session = factory.openSession();
    Transaction transaction = session.beginTransaction();

    try {
      // Create hospital
      Hospital hospital = new Hospital();
      hospital.setName("City Hospital");
      hospital.setAddress("123 Main St");

      // Create speciality
      Specialty specialty = new Specialty();
      specialty.setName("Cardiology");

      // Create doctor
      Doctor doctor = new Doctor();
      doctor.setName("Dr. Smith");
      doctor.setHospital(hospital);
      doctor.setSpecialty(specialty);

      // Create schedule to doctor
      DoctorSchedule schedule = new DoctorSchedule();
      schedule.setType("Annual Visit");
      schedule.setDateTime(LocalDateTime.now());
      schedule.setDoctor(doctor);

      // Relation schedule - doctor
      doctor.setSchedule(schedule);

      // Create patient
      Patient patient = new Patient();
      patient.setName("John Doe");
      patient.setAddress("456 Oak St");

      // doctor to patient
      //doctor.getPatients().add(patient);
      patient.getDoctors().add(doctor);

      // Save entities
      session.save(hospital);
      session.save(specialty);
      session.save(doctor);
      session.save(patient);

      // Commit transaction
      transaction.commit();

      // Retrieve y Verify entities
      session = factory.openSession();
      transaction = session.beginTransaction();

      Doctor retrievedDoctor = session.get(Doctor.class, doctor.getId());
      System.out.println("Doctor: " + retrievedDoctor.getName() + ", Specialty: " + retrievedDoctor.getSpecialty().getName());
      System.out.println("Hospital: " + retrievedDoctor.getHospital().getName());
      System.out.println("Schedule: " + retrievedDoctor.getSchedule().getType() + ", Date/Time: " + retrievedDoctor.getSchedule().getDateTime());

/*
      for (Patient p : retrievedDoctor.getPatients()) {
        System.out.println("Patient: " + p.getName() + ", Address: " + p.getAddress());
      }
*/

      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    } finally {
      session.close();
      factory.close();
    }
  }
}