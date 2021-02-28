package com.vngrs.assignment.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author alperkopuz
 * Query class used for Posting Appointment to create a valid appointment
 */
@Data
@Builder
public class AppointmentQuery {

    @NotNull(message = "Doctor ID is mandatory")
    private String doctorId;

    @NotNull(message = "Patient ID is mandatory")
    private String patientId;

    @NotNull(message = "Appointment Date is mandatory")
    private Date appointmentDate;

    @Range(min=10, max=120)
    private int lengthOfAppointment;

}
