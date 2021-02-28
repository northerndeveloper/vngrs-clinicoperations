package com.vngrs.assignment.repository;

import com.vngrs.assignment.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author alperkopuz
 * Appointment Repository class to manage DB operations for Appointment
 */
@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
}
