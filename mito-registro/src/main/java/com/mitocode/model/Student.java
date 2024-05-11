package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStudent;

    @Column(nullable = false, length = 50)
    private String names;

    @Column(nullable = false, length = 50)
    private String lastname;

    @Column(nullable = false, length = 9)
    private String dni;

    @Column(nullable = false)
    private Integer age;

}
