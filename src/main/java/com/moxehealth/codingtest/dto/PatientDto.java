/**
 *
 */
package com.moxehealth.codingtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

/**
 * @author Ahsan Raza
 */
@Data
@AllArgsConstructor
public class PatientDto {
    
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private int hospitalProviderId;
}
