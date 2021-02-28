package com.vngrs.assignment.repository;

import com.vngrs.assignment.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author alperkopuz
 * Patient Repository class to manage DB operations for Patient
 */
@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
}
