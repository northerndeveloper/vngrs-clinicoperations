package com.vngrs.assignment.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author alperkopuz
 * Appointment Document Class. Required attributes to create an appointment.
 */
@Document
@Data
@Builder
public class Appointment {

    @Id
    private String appointmentId;

    private Doctor doctor;

    private Patient patient;

    private Date appointmentDate;

    private int lengthOfAppointment;

}
