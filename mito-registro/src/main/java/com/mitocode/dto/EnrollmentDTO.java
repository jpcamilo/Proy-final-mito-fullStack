package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mitocode.model.EnrrollmentDetail;
import com.mitocode.model.Student;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollmentDTO {

    private Integer idEnrolllment;

    @NotNull
    private LocalDateTime datetime;

    @NotNull
    private StudentDTO student;

    @NotNull
    private boolean state;

    @NotNull
    @JsonManagedReference
    private List<EnrollmentDetailDTO> details;
}
