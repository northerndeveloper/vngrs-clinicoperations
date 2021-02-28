package com.vngrs.assignment.repository;

import com.vngrs.assignment.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author alperkopuz
 * Doctor Repository class to manage DB operations for Doctor
 */
@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String> {
}
