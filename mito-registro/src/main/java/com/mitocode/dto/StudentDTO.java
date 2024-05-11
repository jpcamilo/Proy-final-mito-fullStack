package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

    private Integer id;

    @NotNull
    @Size(max = 50)
    private String names;

    @NotNull
    @Size(max = 50)
    private String lastname;

    @NotNull
    @Size(max = 9)
    private String dni;

    @NotNull
    private Integer age;



}
