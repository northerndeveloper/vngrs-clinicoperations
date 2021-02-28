package com.vngrs.assignment.controller;

import com.vngrs.assignment.model.Doctor;
import com.vngrs.assignment.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author alperkopuz
 * Doctor Rest Controller class is used to get all doctors and add a new doctor
 */
@RestController
@AllArgsConstructor
public class DoctorController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    // Dependency Injection was added as constructor injection which is better by providing AllAargsConstructor of lombok
    private DoctorRepository doctorRepository;

    /**
     * Gets all the doctors
     *
     * @return
     */
    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {

        logger.info("Getting all doctors");
        return doctorRepository.findAll();
    }

    /**
     * Post doctor with the provided information from user
     *
     * @param doctor
     */
    @PostMapping("/doctors")
    public void addDoctor(@RequestBody @Valid Doctor doctor) {

        logger.info("Adding new doctor with  " + " doctor name " + doctor.getName() + " doctor surname " + doctor.getSurname()
                + "doctor citizenship number " + doctor.getCitizenshipNumber());

        doctorRepository.save(doctor);

        logger.info("doctor is added successfully");

    }

    /**
     * Delete all the doctors from the system
     */
    @DeleteMapping("/deletedoctors")
    public void deleteDoctors() {

        logger.info("Delete all doctors process has been removed started");

        doctorRepository.deleteAll();

        logger.info("Delete all doctors process has been completed successfully");
    }
}
