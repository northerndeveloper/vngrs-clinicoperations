package com.vngrs.assignment.controller;

import com.vngrs.assignment.model.Patient;
import com.vngrs.assignment.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author alperkopuz
 * Patient Rest Controller class is used to get all patients and add a new patient
 */
@RestController
@AllArgsConstructor
public class PatientController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    // Dependency Injection was added as constructor injection which is better by providing AllAargsConstructor of lombok
    public PatientRepository patientRepository;

    /**
     * Gets all the patients
     *
     * @return
     */
    @GetMapping("/patients")
    public List<Patient> getAllPatients() {

        logger.info("Getting all patients");
        return patientRepository.findAll();

    }

    /**
     * Post patient with the provided information from user
     *
     * @param patient
     */
    @PostMapping("/patients") //TODO add error exceptions here
    public void addPatient(@RequestBody @Valid Patient patient) {

        logger.info("Adding new patient with patient Id " + patient.getPatientId() +
                " ,name = " + patient.getName() +
                ", surname = " + patient.getSurname() +
                ", citizenship number " + patient.getCitizenshipNumber());

        patientRepository.save(patient);

        logger.info("Patient added successfully");
    }

    /**
     * Delete all patients from the system
     */
    @DeleteMapping("/deletepatients")
    public void deletePatients() {

        logger.info("Delete all patients process has been removed started");

        patientRepository.deleteAll();

        logger.info("Delete all patients process has been completed successfully");
    }
}
