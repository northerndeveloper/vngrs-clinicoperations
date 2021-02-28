package com.vngrs.assignment.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author alperkopuz
 * Patient Document required attributes to create a Patient Entity
 */
@Document
@Data
@Builder
public class Patient {

    @Id
    private String patientId;

    private String name;

    private String surname;

    private long citizenshipNumber;

}
