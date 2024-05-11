package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnrolllment;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false, foreignKey = @ForeignKey(name = "FK_ENROLL_STUDENT"))
    private Student student;

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private List<EnrrollmentDetail> details;

    @Column(nullable = false)
    private boolean state;
}
