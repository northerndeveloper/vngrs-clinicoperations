package com.vngrs.assignment.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author alperkopuz
 * Doctor Document required attributes to create a Doctor entity
 */
@Document
@Data
@Builder
public class Doctor {

    @Id
    private String doctorId;

    private String name;

    private String surname;

    private long citizenshipNumber;

    private Double hourlyRate;

}
